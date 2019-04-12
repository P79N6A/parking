<template>
  <div class="home-page">
    <div id="banner">
      <img src="../assets/app/banner_banner04.png">
    </div>
    <div id="content">
      <div class="app">
        <div class="app_l">
          <img src="../assets/app/iphone_bound.png" v-show="imgStatus[0]">
          <img src="../assets/app/iphone_search.png" v-show="imgStatus[1]">
          <img src="../assets/app/iphone_appointment.png" v-show="imgStatus[2]">
          <img src="../assets/app/iphone_navigate.png" v-show="imgStatus[3]">
          <img src="../assets/app/iphone_pay.png" v-show="imgStatus[4]">
          <img src="../assets/app/iphone_other.png" v-show="imgStatus[5]">
        </div>
        <div class="app_r">
          <ul>
            <li class="fl" :class="{'active': imgStatus[0]}" @mouseover="handleMouseOver1st">
              <span class="item item-1"></span>
              <span class="des">绑定车牌</span>
            </li>
            <li class="fl" :class="{'active': imgStatus[1]}" @mouseover="handleMouseOver2ed">
              <span class="item item-2"></span>
              <span class="des">车位查询</span>
            </li>
            <li class="fl" :class="{'active': imgStatus[2]}" @mouseover="handleMouseOver3th">
              <span class="item item-3"></span>
              <span class="des">预约车位</span>
            </li>
            <li class="fl" :class="{'active': imgStatus[3]}" @mouseover="handleMouseOver4th">
              <span class="item item-4"></span>
              <span class="des">一键导航</span>
            </li>
            <li class="fl" :class="{'active': imgStatus[4]}" @mouseover="handleMouseOver5th">
              <span class="item item-5"></span>
              <span class="des">无感支付</span>
            </li>
            <li class="fl" :class="{'active': imgStatus[5]}" @mouseover="handleMouseOver6th">
              <span class="item item-6"></span>
              <span class="des">其他服务</span>
            </li>
          </ul>
          <p class="detail">
            <span v-show="imgStatus[0]">绑定车牌，实现人工智能，<br>无感支付</span>
            <span v-show="imgStatus[1]">附近搜索，<br>快速知道空余车位</span>
            <span v-show="imgStatus[2]">车位预约，无需分秒必争，<br>即到即停</span>
            <span v-show="imgStatus[3]">复杂停车场不再绕，<br>一线直达</span>
            <span v-show="imgStatus[4]">付款不停车，<br>账单提醒拒绝遗漏</span>
            <span v-show="imgStatus[5]">违章查询、车辆买卖、车品商城，<br>更多车主服务正在上线</span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      imgStatus: [true, false, false, false, false, false],
    }
  },
  mounted() {},
  created() {},
  methods: {
    handleMouseOver1st() {
      this.imgStatus = [true, false, false, false, false, false]
    },
    handleMouseOver2ed() {
      this.imgStatus = [false, true, false, false, false, false]
    },
    handleMouseOver3th() {
      this.imgStatus = [false, false, true, false, false, false]
    },
    handleMouseOver4th() {
      this.imgStatus = [false, false, false, true, false, false]
    },
    handleMouseOver5th() {
      this.imgStatus = [false, false, false, false, true, false]
    },
    handleMouseOver6th() {
      this.imgStatus = [false, false, false, false, false, true]
    }
  }
}
</script>
<style lang="less" scoped>
.home-page {
  position: relative;
  width: 1920px;
  & > div {
    width: 1920px;
    height: 655px;
  }
  #banner {
    width: 1920px;
    height: 500px;
    img{
      height: 100%;
      width: 100%;
    }
  }
  #content{
    width: 1920px;
    position: relative;
    background-color: #fff;
    .app{
      width: 1200px;
      position: absolute;
      top: 100px;
      left: 360px;
      .app_l{
        width: 230px;
        height: 455px;
        position: absolute;
        left: 154px;
        // background: url("../assets/app/iphone_bound.png") center no-repeat;
      }
      .app_r{
        position: absolute;
        left: 454px;
        ul{
          height: 97px;
          margin-top: 20px;
          li{
            width: 66px;
            margin-right: 40px;
            .item{
              display: inline-block;
              width: 66px;
              height: 52px;
              background-position: center !important;
              background-repeat: no-repeat !important;
            }
            .des{
              display: inline-block;
              width: 66px;
              height: 45px;
              line-height: 45px;
              color: #b4b4b4;
              font-size: 16px;
            }
          }
        }
        p{
          margin-top: 60px;
          padding: 20px 0;
          color: #222;
          font-size: 40px;
          line-height: 60px;
          text-align: left;
        }
        .active{
          .item-1 {
            background: url("../assets/app/icon_boundb.png");
          }
          .item-2 {
            background: url("../assets/app/icon_searchb.png");
          }
          .item-3 {
            background: url("../assets/app/icon_appointmentb.png");
          }
          .item-4 {
            background: url("../assets/app/icon_navb.png");
          }
          .item-5 {
            background: url("../assets/app/icon_payb.png");
          }
          .item-6 {
            background: url("../assets/app/icon_otherb.png");
          }
          .des{
            color: #3296FA;
            border-bottom: solid 4px #3296FA;
          }
        }
        .item-1 {
          background: url("../assets/app/icon_boundg.png");
        }
        .item-2 {
          background: url("../assets/app/icon_searchg.png");
        }
        .item-3 {
          background: url("../assets/app/icon_appointmentg.png");
        }
        .item-4 {
          background: url("../assets/app/icon_navg.png");
        }
        .item-5 {
          background: url("../assets/app/icon_payg.png");
        }
        .item-6 {
          background: url("../assets/app/icon_otherg.png");
        }
      }
    }
  }
}
</style>
