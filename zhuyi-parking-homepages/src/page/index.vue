<template>
  <div class="home-page">
    <div id="banner">
      <el-carousel height="660px" arrow="never">
        <el-carousel-item v-for="item in carouselItem" :key="item.key">
            <img :src=item.url alt="">
        </el-carousel-item>
      </el-carousel>
      <!-- <img src="../assets/index/banner_banner01.png"> -->
    </div>
    <div id="intro">
        “任意停车”以城市级为基点，通过互联网与高端智能技术，汇集各分散的停车网点数据，进行集中式管理，解决城市停车难题。
    </div>
    <div id="project">
      <div class="title">
        <span>解决方案</span>
        <p>为路侧停车位、商超停车场、单位内部停车场、私宅小区停车位等提供一站式、专业的解决方案</p>
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <ul class="fx">
        <li class="fl">
          <span class="item item-1"></span>
          <!-- <img src="../assets/index/img_connectg.png"> -->
        </li>
        <li class="fl">
          <span class="item item-2"></span>
          <!-- <img src="../assets/index/img_integrateg.png"> -->
        </li>
        <li class="fl">
          <span class="item item-3"></span>
          <!-- <img src="../assets/index/img_shareg.png"> -->
        </li>
      </ul>
    </div>
    <div id="product" class="tb">
      <div class="title">
        APP服务
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <img src="../assets/index/img_service.png">
    </div>
    <div id="information">
      <div class="title">
        资讯
      </div>
      <div class="division" style="margin-bottom: 90px;">
        <img src="../assets/home/icon_division.png">
      </div>
      <Swiper :items="carouselJson" :link="'/news'"></Swiper>
    </div>
  </div>
</template>
<script>
import axios from '@/util/request'
import Swiper from '@/components/swiper'
export default {
  components: { Swiper },
  data() {
    return {
      carouselItem: [
        { key: 0, url: require('../assets/index/banner_banner01.png') },
        { key: 1, url: require('../assets/index/banner_banner02.png') },
        { key: 2, url: require('../assets/index/bg_banner03.png') }
      ],
      carouselJson: [
        { url: 'http://c.hiphotos.baidu.com/image/pic/item/48540923dd54564e08122c8dbfde9c82d1584f0b.jpg', desc: '百度图片1' },
        { url: 'http://a.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327f216788d9d8fa0ec09fac791.jpg', desc: '百度图片2' },
        { url: 'http://d.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c605fc07a5781b1cb1349547734.jpg', desc: '百度图片3' },
      ]
    }
  },
  mounted() {},
  created() {
    this.initData()
  },
  methods: {
    // 获取轮播资讯
    initData() {
      let data = {
        pageSize: 4,
        pageNo: 1
      }
      axios({ url: '/portal/front/news/getPageList', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            // 接口数据目前没有图片
            this.carouselJson = data.items
          }
        })
    }
  }
}
</script>
<style lang="less" scoped>
.home-page {
  position: relative;
  min-height: 2000px;
  width: 1920px;
  & > div {
    width: 1920px;
    height: 800px;
  }
  .division {
    height: 20px;
    line-height: 20px;
    text-align: center;
  }
  #banner {
    height: 660px;
    .el-carousel{
      width: 1920px;
      height: 660px;
      left: 0;
      top: 0;
    }
  }
  #intro{
    height: 80px;
    line-height: 80px;
    color: #fff;
    background-color: #444;
    font-size: 16px;
    text-align: center;
  }
  #product {
    box-sizing: border-box;
    height: 790px;
    padding-top: 60px;
    background-color: #fafafa;
    &:before {
      content: '';
      display: table;
    }
    .title {
      height: 50px;
      line-height: 50px;
      font-size: 30px;
      font-weight: bold;
      color: #3296FA;
    }
    & > img {
      margin-top: 100px;
    }
  }
  #project {
    box-sizing: border-box;
    height: 730px;
    padding-top: 60px;
    background: #fff;
    position: relative;
    &:before {
      content: '';
      display: table;
    }
    .title {
      text-align: center;
      span{
        display: inline-block;
        height: 50px;
        line-height: 50px;
        font-size: 30px;
        font-weight: bold;
        color: #3296FA;
      }
      p{
        height: 40px;
        line-height: 40px;
        font-size: 16px;
        color: #444;
      }
    }
    ul {
      position: absolute;
      top: 270px;
      left: 360px;
      width: 1200px;
      li {
        width: 280px;
        height: 360px;
        .item{
          display: inline-block;
          width: 280px;
          height: 360px;
          background-position: center;
          background-repeat: no-repeat;
        }
        // img{
        //   width: 100%;
        //   height: 100%;
        // }
      }
      li:not(:last-child) {
        margin-right: 180px;
      }
    }
    .item-1 {
      background: url("../assets/index/img_connectg.png");
    }
    li:hover .item-1 {
      background: url("../assets/index/img_connectb.png") center no-repeat;
    }
    .item-2 {
      background: url("../assets/index/img_integrateg.png");
    }
    li:hover .item-2 {
      background: url("../assets/index/img_integrateb.png") center no-repeat;
    }
    .item-3 {
      background: url("../assets/index/img_shareg.png");
    }
    li:hover .item-3 {
      background: url("../assets/index/img_shareb.png") center no-repeat;
    }
  }
  #information {
    box-sizing: border-box;
    padding-top: 60px;
    background: #fff;
    height: 880px;
    &:before {
      content: '';
      display: table;
    }
    .title {
      height: 50px;
      line-height: 50px;
      font-size: 30px;
      font-weight: bold;
      color: #3296FA;
    }
  }
}
.el-carousel {
  width: 1200px;
  height: 400px;
  position: relative;
  left: 360px;
  top: 72px;
  a {
    display: block;
    width: 100%;
    height: 100%;
  }
}
.el-carousel__item h3 {
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

.el-carousel__item img {
  width: 100%;
  height: 100%;
}
</style>
