<template>
  <div class="aboutUs-page">
    <div class="banner">
      <img src="../assets/v2/icon_aboutUS.png">
    </div>
    <div class="introduce items-box">
      <div class="title">
        公司简介
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <div class="text">
        <p>杭州逐一科技有限公司成立于2017年11月，坐落于美丽的富春江畔，毗邻320国道，是一家专门从事智慧城市之智慧停车建设的科技创新型公司。响应2015年8月3日国家发改委公布的《关于加强城市停车设施建设的指导意见》，公司始终专注城市智慧停车、停车场智能改造、物联网产品的设计开发，系统集成与运营服务。</p>
        <p>逐一科技以智慧停车为入口，深耕城市各类停车资源，将静态交通大数据与智慧城市服务深度结合，为城市管理者、停车经营管理方、车主和合作伙伴提供城市级静态交通综合解决方案、大数据应用和停车资产经营管理服务，致力于解决城市”停车难，乱停车”问题。</p>
        <p>公司拥有完善的自主研发体系。其中，研发团队是公司的主力军，占比高达50%，目前在全视频智能识别、物联网、大数据、云计算及人工智能等方面已经拥有了深厚的技术积累 。拥有强大技术力量的逐一科技能够依托城市交通顶层设计，开发以视频检测识别、物联网、云计算、移动互联网、大数据等技术为基础的城市智慧停车管理平台以及手机移动端应用，最终实现停车收费、取证、监管等功能于一体。</p>
        <p>由于对智慧城市领域的不断深入与对智能技术的不断钻研，逐一科技凭借移动计算、智能识别、数据融合等核心技术，在智慧停车项目的研发、建设和运营以及交通智能化、物流智能化等方向上不断发展。同时，秉承着勇于创新和务实的精神，公司在停车场智能改造、停车智能诱导、城市信息化应用等智慧领域也处于行业领先地位。</p>
        <p>逐一科技本着“让智能技术服务美好生活”的使命，带领强大的技术研发团队，以专业化技术研发和成熟的项目运作模式，正朝着智慧停车互联网+，智慧城市创新2.0建设迈进！</p>
      </div>
      <img src="../assets/v2/WechatIMG4.jpeg" class="introduce-img">
    </div>
    <div class="culture items-box">
      <div class="title">
        企业文化
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <div class="culture-box">
        <div class="duty">
          <img src="../assets/v2/icon_sm.png">
          <h3>使命</h3>
          <div>让智能技术服务美好生活</div>
        </div>
        <div class="wish">
          <img src="../assets/v2/icon_yj.png">
          <h3>愿景</h3>
          <div>成为全球领先的智慧城市构建服务商</div>
        </div>
        <div class="values">
          <img src="../assets/v2/icon_jzg.png">
          <h3>价值观</h3>
          <div>开放、服务、高效、创新、以人为尊、科技为本</div>
        </div>
      </div>
    </div>
    <div class="joinUs items-box">
      <div class="title">
        加入我们
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <div class="jobs fx">
        <Job v-for="(item, index) in showArr" :key="index" :item="item"></Job>
      </div>
    </div>
    <div class="map items-box">
      <div class="title">
        联系我们
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <div class="map-box">
        <div class="address">
          <h3>杭州逐一科技有限公司</h3>
          <p class="en-name">Hangzhou Zoeeasy Technology Co.,Ltd</p>
          <div class="add-line"></div>
          <p class="phone">服务热线: 4008-872-866</p>

          <p class="bom-item adds">地址:&nbsp;&nbsp;&nbsp;浙江省杭州市富阳区春秋北路591号二楼</p>
          <p class="bom-item mails">邮箱:&nbsp;&nbsp;&nbsp;service@zhuyitech.com</p>
          <p class="bom-item site">网址&nbsp;&nbsp;&nbsp;www.zoeeasy.com</p>
        </div>
        <div id="container" class="mymap"></div>
      </div>
    </div>
  </div>
</template>
<script>
import Job from '@/components/singleJob'
import axios from '@/util/request'
import AMap from 'AMap' // 在页面中引入高德地图
import AMapUI from 'AMapUI' // 在页面中引入高德地图
export default {
  components: { Job },
  data() {
    return {
      showArr: []
    }
  },
  created() {
    this.initData()
  },
  mounted () {
    this.loadmap() // 加载地图和相关组件
  },
  methods: {
    initData() {
      let data = {
        pageSize: 40,
        pageNo: 1
      }
      axios({ url: '/portal/front/employ/getPageList', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            this.showArr = data.items
          }
        })
    },
    loadmap () {
      const map = new AMap.Map('container', {
        zoom: 8
      })
      // 1
      AMapUI.loadUI(['control/BasicControl'], function (BasicControl) {
        map.addControl(new BasicControl.Zoom({
          theme: 'dark',
          // showZoomNum: true,
          position: 'bl'
        }))
      })

      // 2
      AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
        const marker = new SimpleMarker({
          showPositionPoint: true,
          iconStyle: 'red',
          map: map,
          position: ['119.939766', '30.068919'],
          iconLabel: {
            innerHTML: 'our',
            style: {
              color: '#fff'
            }
          }
        })
        map.setCenter(marker.getPosition())
      })
      // 3
    }
  }
}
</script>
<style lang="less" scoped>
  .aboutUs-page {
    min-height: 1200px;
    color: #222;
    .banner {
      width: 1920px;
      height: 500px;
      img {
        width: 100%;
        height: 500px;
      }
    }
    .title {
      height: 50px;
      line-height: 50px;
      font-size: 30px;
      font-weight: bold;
      color: #3296fa;
      margin-top: 50px;
    }
    .division {
      height: 20px;
      line-height: 20px;
      text-align: center;
    }
    .items-box {
      &:before {
        content: '';
        display: table;
      }
      position: relative;
    }
    .introduce {
      width: 1920px;
      height: 736px;
      background: #fff;
      .text {
        width: 510px;
        height: 477px;
        font-size: 14px;
        text-align: left;
        text-indent: 28px;
        position: absolute;
        top: 188px;
        left: 360px;
        p {
          margin-top: 4px;
          text-align: justify;
        }
      }
      .introduce-img {
        width: 640px;
        height: 500px;
        position: absolute;
        top: 188px;
        left: 920px;
      }
    }
    .culture {
      width: 1920px;
      height: 648px;
      background: #fff;
      color: #353535;
      .culture-box {
        width: 1200px;
        height: 400px;
        position: absolute;
        left: 360px;
        bottom: 60px;
        display: flex;
        display: -webkit-flex;
        flex-direction: row;
        & > div {
          -webkit-flex: 1;
          position: relative;
          img {
            width: 150px;
            height: 150px;
            position: absolute;
            top: 65px;
            left: 125px;
          }
          h3 {
            position: absolute;
            top: 246px;
            width: 100%;
            font-size: 26px;
            letter-spacing: 4px;
          }
          div {
            position: absolute;
            top: 292px;
            left: 66px;
            width: 268px;
            font-size: 20px;
            letter-spacing: 2px;
          }
        }
      }
    }
    .joinUs {
      background: #f7f7f7;
    }
    .map {
      width: 1920px;
      height: 746px;
      position: relative;
      background: #fff;
      .map-box {
        height: 460px;
        width: 1200px;
        position: absolute;
        top: 177px;
        left: 360px;
        display: flex;
        display: -webkit-flex;
        flex-direction: row;
      }
      .mymap{
        width: 788px;
        height: 460px;
      }
      .address {
        height: 460px;
        -webkit-flex: 1;
        background: #1743AE;
        color: #fff;
        h3 {
          margin-top: 111px;
          font-size: 26px;
        }
        p {
          font-size: 16px;
        }
        .en-name {
          font-size: 14px;
          margin-top: 7px;
        }
        .add-line {
          width: 265px;
          height: 1px;
          background: #fff;
          margin-top: 6px;
          margin-left: 73px;
        }
        .phone {
          color: #FFD925;
          font-size: 22px;
          margin-top: 8px;
        }
        .bom-item {
          position: absolute;
          width: 276px;
          text-align: left;
          left: 73px;
          &.adds {
            top: 278px;
          }
          &.mails {
            top: 333px;
          }
          &.site {
            top: 368px;
          }
        }
      }
    }
    .jobs {
      width: 100%;
      min-height: 800px;
      padding-bottom: 80px;
      font-size: 32px;
      color: #fff;
      & > div {
        float: left;
      }
      & > div:nth-child(2n-1) {
        margin: 17px 40px 17px 358px;
      }
      & > div:nth-child(2n) {
        margin: 17px 360px 0 0;
      }
    }
  }
</style>
