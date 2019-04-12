<template>
  <div class="home-page">
    <div id="banner">
      <img src="../../assets/home/banner_home.png">
    </div>
    <div id="ourApp" class="tb">
      <div class="title">
        <span style="color: #444;">任意停车APP / </span>
        <span style="color: #3296FA">APPLICATION</span>
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <ul class="fx">
        <li class="fl" :class="{'active': appImgStatus[0]}" @mouseover="handleMouseOver1st">
          <img src="../../assets/home/img_app_nearby.png">
        </li>
        <li class="fl md" :class="{'active': appImgStatus[1]}" @mouseover="handleMouseOver2ed">
          <img src="../../assets/home/img_app_home.png">
        </li>
        <li class="fl" :class="{'active': appImgStatus[2]}" @mouseover="handleMouseOver3th">
          <img src="../../assets/home/img_app_order.png">
        </li>
      </ul>
    </div>
    <div id="product" class="tb">
      <div class="title">
        产品 / PRODUCT
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <img src="../../assets/home/img_product.png">
    </div>
    <div id="case" class="tb">
      <div class="title">
        <span style="color: #444;">案例 / </span>
        <span style="color: #3296FA">CASE</span>
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <ul class="fx">
        <li class="fl" :class="{'active': caseImgStatus[0]}" @mouseover="handleCaseMouseOver1st">
          <div class="cover">
            <img src="../../assets/home/img_business.png">
            <div class="name">商业综合体</div>
          </div>
        </li>
        <li class="fl" :class="{'active': caseImgStatus[1]}" @mouseover="handleCaseMouseOver2ed">
          <div class="cover">
            <img src="../../assets/home/img_office.png">
            <div class="name">商业写字楼</div>
          </div>
        </li>
        <li class="fl" :class="{'active': caseImgStatus[2]}" @mouseover="handleCaseMouseOver3th">
          <div class="cover">
            <img src="../../assets/home/img_residence.png">
            <div class="name">大型住宅区</div>
          </div>
        </li>
        <li class="fl" :class="{'active': caseImgStatus[3]}" @mouseover="handleCaseMouseOver4th">
          <div class="cover">
            <img src="../../assets/home/img_school.png">
            <div class="name">学院及医院</div>
          </div>
        </li>
        <li class="fl" :class="{'active': caseImgStatus[4]}" @mouseover="handleCaseMouseOver5th">
          <div class="cover">
            <img src="../../assets/home/img_hotel.png">
            <div class="name">客运、旅游及酒店</div>
          </div>
        </li>
      </ul>
    </div>
    <div id="cooperation">
      <div class="title">
        合作 / COOPERATION
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <div class="area china-map">
        <div class="map"></div>
        <div class="sub-title">商业合作（ 全国性 ）</div>
      </div>
      <div class="area zhejiang-map">
        <div class="map"></div>
        <div class="sub-title">广告投放（ 区域性 ）</div>
      </div>
    </div>
    <div id="aboutUs">
      <div class="title">
        <span style="color: #444;">我们 / </span>
        <span style="color: #3296FA">ABOUT US</span>
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <ul class="fx">
        <li class="fl">
          <div class="icon item-1"></div>
          <div class="item-title">技术创新战略</div>
          <div class="item-desc">我公司致力于城市智慧停车的建设与运营，方便车主出行，节省大量时间和精力，让出行更便捷，让停车更智慧！</div>
        </li>
        <li class="fl">
          <div class="icon item-2"></div>
          <div class="item-title">在线实时监控</div>
          <div class="item-desc">监控硬件设备关联后台数据实现实时监控，做到人、车、泊位三方统一化管理，方便管理人员实时掌控最新数据情况</div>
        </li>
        <li class="fl">
          <div class="icon item-3"></div>
          <div class="item-title">开放数据接口</div>
          <div class="item-desc">支持微信小程序、支付宝小程序等第三方平台接入；同时也支持接入其他第三方接口数据</div>
        </li>
        <li class="fl">
          <div class="icon item-4"></div>
          <div class="item-title">降低运营成本</div>
          <div class="item-desc">开放电子支付及车费追缴制度，改善收费漏洞，最大程度减少停车费损失；优化人力资源，降低人工成本</div>
        </li>
      </ul>
    </div>
    <div id="information">
      <div class="title">
        <span style="color: #444;">资讯 / </span>
        <span style="color: #3296FA">INFORMATION</span>
      </div>
      <div class="division">
        <img src="../../assets/home/icon_division.png">
      </div>
      <Swiper :items="carouselJson" :link="'/news'"></Swiper>
    </div>
    <div class="aside" :style="'left:' + asideOffsetLeft + 'px;'">
      <ul>
        <li :class="{'active': aside[0]}" @click="turnToDom('banner')">
          BANNER
        </li>
        <li :class="{'active': aside[1]}" @click="turnToDom('ourApp')">
          APP
        </li>
        <li :class="{'active': aside[2]}" @click="turnToDom('product')">
          产品
        </li>
        <li :class="{'active': aside[3]}" @click="turnToDom('case')">
          案例
        </li>
        <li :class="{'active': aside[4]}" @click="turnToDom('cooperation')">
          合作
        </li>
        <li :class="{'active': aside[5]}" @click="turnToDom('aboutUs')">
          我们
        </li>
        <li :class="{'active': aside[6]}" @click="turnToDom('information')">
          资讯
        </li>
      </ul>
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
      asideOffsetLeft: 0,
      appImgStatus: [false, true, false],
      caseImgStatus: [false, false, true, false, false],
      aside: [true, false, false, false, false, false, false],
      carouselJson: [
        { url: 'http://c.hiphotos.baidu.com/image/pic/item/48540923dd54564e08122c8dbfde9c82d1584f0b.jpg', desc: '百度图片1' },
        { url: 'http://a.hiphotos.baidu.com/image/pic/item/10dfa9ec8a136327f216788d9d8fa0ec09fac791.jpg', desc: '百度图片2' },
        { url: 'http://d.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c605fc07a5781b1cb1349547734.jpg', desc: '百度图片3' },
      ]
    }
  },
  mounted() {
    window.addEventListener('scroll', this.watchScroll)
  },
  created() {
    this.asideOffsetLeft = (document.body.clientWidth - 1920) / 2 + 1480
    this.initData()
  },
  methods: {
    // 导航监听和滚动
    watchScroll () {
      let top = document.body.scrollTop || document.documentElement.scrollTop
      if (top >= 4400) {
        this.aside = [false, false, false, false, false, false, true]
      } else if (top >= 3600) {
        this.aside = [false, false, false, false, false, true, false]
      } else if (top >= 2800) {
        this.aside = [false, false, false, false, true, false, false]
      } else if (top >= 2000) {
        this.aside = [false, false, false, true, false, false, false]
      } else if (top >= 1200) {
        this.aside = [false, false, true, false, false, false, false]
      } else if (top >= 400) {
        this.aside = [false, true, false, false, false, false, false]
      } else {
        this.aside = [true, false, false, false, false, false, false]
      }
    },
    turnToDom(domId) {
      let domArr = ['banner', 'ourApp', 'product', 'case', 'cooperation', 'aboutUs', 'information']
      let idx = domArr.indexOf(domId)
      this.aside = [false, false, false, false, false, false, false]
      this.aside[idx] = true
      if (document.body.scrollTop) {
        document.body.scrollTop = idx * 800
      } else {
        document.documentElement.scrollTop = idx * 800
      }
    },
    // 修改 -app- 栏目3个样式
    handleMouseOver1st() {
      this.appImgStatus = [true, false, false]
    },
    handleMouseOver2ed() {
      this.appImgStatus = [false, true, false]
    },
    handleMouseOver3th() {
      this.appImgStatus = [false, false, true]
    },
    // 修改 -案例- 栏目5个样式
    handleCaseMouseOver1st() {
      this.caseImgStatus = [true, false, false, false, false]
    },
    handleCaseMouseOver2ed() {
      this.caseImgStatus = [false, true, false, false, false]
    },
    handleCaseMouseOver3th() {
      this.caseImgStatus = [false, false, true, false, false]
    },
    handleCaseMouseOver4th() {
      this.caseImgStatus = [false, false, false, true, false]
    },
    handleCaseMouseOver5th() {
      this.caseImgStatus = [false, false, false, false, true]
    },
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
  #banner {
    img {
      width: 1920px;
      height: 800px;
    }
  }
  #ourApp {
    background: #fafafa;
    .title {
      font-size:30px;
      font-weight: bold;
      margin-top: 58px;
    }
    .division {
      margin-top: 18px;
      height: 10px;
    }
    ul {
      display: inline-block;
      height: 590px;
      width: 1140px;
      margin-top: 60px;
      li {
        width: 330px;
        height: 590px;
        position: relative;
        img {
          width: 180px;
          height: 320px;
          position: absolute;
          top:50%;
          left: 50%;
          transform: translate(-50%,-50%);
          /*box-shadow: 5px 5px 12px #3296FA;*/
          box-shadow: 5px 5px 12px rgb(147, 201, 255);
        }
      }
      li.md {
        margin: 0 75px;
      }
      li.active > img {
        animation:app 1.5s forwards;
      }
    }
  }
  #product {
    background: #3296FA;
    &:before {
      content: '';
      display: table;
    }
    .title {
      font-size: 30px;
      font-weight: bold;
      color: #fff;
      margin-top: 58px;
    }
    .division {
      margin-top: 18px;
      height: 10px;
    }
    & > img {
      margin-top: 100px;
    }
  }
  #case {
    background: #fff;
    .title {
      font-size:30px;
      font-weight: bold;
      margin-top: 58px;
    }
    .division {
      margin-top: 18px;
      height: 10px;
    }
    ul {
      display: inline-block;
      height: 590px;
      width: 1124px;
      margin-top: 220px;
      li {
        width: 140px;
        height: 140px;
        position: relative;
        .cover {
          width: 160px;
          height: 160px;
          position: relative;
          img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
          }
        }
        .name {
          width: 100%;
          height: 24px;
          position: absolute;
          left: 0;
          bottom: -30px;
        }
      }
      li:not(:last-child) {
        margin-right: 106px;
      }
      li.active > .cover {
        animation:case 1s forwards;
        &:before {
          content: '';
          position: absolute;
          width: 100%;
          height: 100%;
          z-index: 1;
          display: inline-block;
          border-radius: 50%;
          box-shadow: -2px -2px 12px rgb(147, 201, 255);
        }
        img {
          box-shadow: 2px 2px 12px rgb(147, 201, 255);
        }
      }
    }
  }
  #cooperation {
    &:before {
      content: '';
      display: table;
    }
    position: relative;
    background: url("../../assets/home/bg_coperation.png");
    .title {
      font-size:30px;
      font-weight: bold;
      margin-top: 58px;
      color: #fff;
    }
    .division {
      margin-top: 18px;
      height: 10px;
    }
    .area {
      position: absolute;
      top: 193px;
      left: 502px;
      width: 400px;
      height: 500px;
      background: #000;
      opacity: .2;
      .map {
        position: absolute;
        top: 50px;
        left: 30px;
        width: 330px;
        height: 340px;
      }
      .sub-title {
        color: #fff;
        position: absolute;
        left: 114px;
        bottom: 68px;
      }
      &:hover {
        opacity: .6;
      }
    }
    .china-map {
      .map {
        background: url("../../assets/home/map_china_grey.png");
      }
      &:hover > .map {
        background: url("../../assets/home/map_china_white.png");
      }
    }
    .zhejiang-map {
      left: 1022px;
      .map {
        background: url("../../assets/home/map_hangzhou_grey.png");
      }
      &:hover > .map {
        background: url("../../assets/home/map_hangzhou_white.png");
      }
    }
  }
  #aboutUs {
    background: #fafafa;
    position: relative;
    &:before {
      content: '';
      display: table;
    }
    .title {
      font-size: 30px;
      font-weight: bold;
      color: #fff;
      margin-top: 58px;
    }
    .division {
      margin-top: 18px;
      height: 10px;
    }
    ul {
      position: absolute;
      top: 254px;
      left: 359px;
      width: 1201px;
      li {
        width: 280px;
        height: 420px;
        background: #fff;
        position: relative;
        &:hover {
          background: #46A0FA;
        }
      }
      li:not(:last-child) {
        margin-right: 27px;
      }
      .icon {
        width: 75px;
        height: 80px;
        position: absolute;
        top: 88px;
        left: 102px;
      }
      .item-title {
        color: #222;
        font-size: 20px;
        font-weight: bold;
        position: absolute;
        top: 261px;
        left: 80px;
      }
      .item-desc {
        color: #B4B4B4;
        font-size: 14px;
        width: 240px;
        height: 60px;
        position: absolute;
        top: 320px;
        left: 20px;
        text-align: left;
      }
      li:hover .item-title, li:hover .item-desc{
        color: #fff;
      }
    }
    .item-1 {
      background: url("../../assets/home/icon_strategy_black.png");
    }
    li:hover .item-1{
      background: url("../../assets/home/icon_strategy_white.png");
    }
    .item-2 {
      background: url("../../assets/home/icon_monitoring_black.png");
    }
    li:hover .item-2{
      background: url("../../assets/home/icon_monitoring_white.png");
    }
    .item-3 {
      background: url("../../assets/home/icon_date_black.png");
    }
    li:hover .item-3{
      background: url("../../assets/home/icon_date_white.png");
    }
    .item-4 {
      width: 80px!important;
      background: url("../../assets/home/icon_cost_black.png");
    }
    li:hover .item-4{
      background: url("../../assets/home/icon_cost_white.png");
    }
  }
  #information {
    background: #fff;
    &:before {
      content: '';
      display: table;
    }
    .title {
      font-size: 30px;
      font-weight: bold;
      color: #fff;
      margin-top: 58px;
    }
    .division {
      margin-top: 18px;
      height: 10px;
      margin-bottom: 72px;
    }
  }
  .aside {
    width: 80px;
    height: 270px;
    position: fixed;
    z-index: 200;
    top: 140px;
    li {
      height: 30px;
      background: #eee;
      line-height: 30px;
      font-size: 14px;
      cursor: pointer;
    }
    li:not(:last-child) {
      margin-bottom: 10px;
    }
    li.active {
      background: #222;
      color: #fff;
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

@keyframes app
{
  from {
    width: 180px;
    height: 320px;
  }
  to {
    width: 330px;
    height: 590px;
  }
}
@keyframes case
{
  from {
    transform: scale(1);
  }
  to {
    transform: scale(1.5);
  }
}
</style>
