<template>
  <div class="swiper-box">
    <swiper :options="swiperOptionTop" class="gallery-top" ref="swiperTop">
      <swiper-slide v-for="(item, index) in items" :key="index">
        <img :src="item.url">
      </swiper-slide>
      <div class="swiper-pagination"  slot="pagination"></div>
    </swiper>
  </div>
</template>
<script>
import { swiper, swiperSlide } from 'vue-awesome-swiper'
export default {
  components: { swiper, swiperSlide },
  name: 'swiperBox',
  props: ['items'],
  data () {
    return {
      url: '',
      swiperOptionTop: {
        spaceBetween: 10,
        autoplay: 3000,
        loop: true,
        pagination: '.swiper-pagination',
        autoplayDisableOnInteraction: false
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
  mounted () {},
  methods: {}
}
</script>
<style lang="less" scoped>
  .swiper-box{
    width: 3.45rem;
    height: 1.4rem;
    display: inline-block;
    position: relative;
    margin-bottom: .2rem;
    .swiper-slide {
      background-size: cover;
      background-position: center;
    }
    .gallery-top {
      height: 1.4rem;
      width: 100%;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .link-box {
      display: block;
      position: relative;
      width: 100%;
      height: 100%;
      h3 {
        display: inline-block;
        width: 100%;
        height: .3rem;
        position: absolute;
        left: 0;
        bottom: 0;
        color: #fff;
        line-height: .3rem;
        background: rgba(0, 0, 0, .5);
        font-size: .12rem;
      }
    }
    .swiper-pagination {
      height: .28rem;
      bottom: 0;
    }
  }
</style>
