<template>
  <div class="swiper-box">
    <swiper :options="swiperOptionTop" class="gallery-top" ref="swiperTop">
      <swiper-slide v-for="(item, index) in items" :key="index">
        <router-link class="link-box" :to="link || '/information/' + item.id">
          <img v-lazy="item.imageUrl">
          <h3>{{ item.title }}</h3>
        </router-link>
      </swiper-slide>
    </swiper>
    <div class="gallery-thumbs" v-if="items.length > 1">
      <div v-for="(item, index) in items" :key="index" :class="{'active': statusArr[index]}" @click="handleClick(index)">
        <img v-lazy="item.imageUrl">
      </div>
    </div>
  </div>
</template>
<script>
import { swiper, swiperSlide } from 'vue-awesome-swiper'
export default {
  components: { swiper, swiperSlide },
  name: 'swiperBox',
  props: ['items', 'link'],
  data () {
    return {
      url: '',
      activeIndex: 0,
      statusArr: [true, false, false, false],
      swiperOptionTop: {
        spaceBetween: 10,
        autoplay: 3000,
        autoplayDisableOnInteraction: false,
        paginationClickable: true,
        onSlideChangeEnd: swiper => {
          this.activeIndex = swiper.realIndex
          this.statusArr = [false, false, false, false]
          this.statusArr[swiper.realIndex] = true
        }
      }
    }
  },
  created () {
  },
  computed: {
    swiper () {
      return this.$refs.swiperTop.swiper
    }
  },
  mounted () {

  },
  methods: {
    handleClick (idx) {
      this.statusArr = [false, false, false, false]
      this.statusArr[idx] = true
      this.swiper.slideTo(idx, 0, false)
    }
  }
}
</script>
<style lang="less" scoped>
  .swiper-box{
    width: 100%;
    height: 100%;
    .swiper-slide {
      background-size: cover;
      background-position: center;
    }
    .gallery-top {
      height: 400px;
      width: 1200px;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .gallery-thumbs {
      height: 120px;
      width: 1200px;
      box-sizing: border-box;
      display: inline-block;
      text-align: center;
      div {
        display: inline-block;
        width: 290px;
        height: 150px;
        opacity: .3;
        margin-top: 12px;
        &.active {
          opacity: 1;
        }
        &:hover {
          cursor: pointer;
        }
        &:not(:last-child) {
          margin-right: 12px;
        }
      }
      img {
        width: 100%;
        height: 100%;
      }
    }
    .link-box {
      position: relative;
      h3 {
        display: inline-block;
        width: 1200px;
        height: 60px;
        position: absolute;
        left: 0;
        bottom: 0;
        text-align: left;
        color: #fff;
        line-height: 60px;
        font-size: 20px;
        padding-left: 40px;
        background: rgba(0, 0, 0, .3);
      }
    }
    .gallery-thumbs .swiper-slide {
      width: 25%;
      height: 100%;
      opacity: 0.4;
    }
    .gallery-thumbs .swiper-slide-active {
      opacity: 1;
    }
  }
</style>
