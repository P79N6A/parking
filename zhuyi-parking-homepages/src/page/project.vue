<template>
  <div class="home-page">
    <div id="banner">
      <img src="../assets/project/bg_banner10.png">
    </div>
    <div id="content">
      <div class="container">
        <div id="park">
          <div class="title">
            路边停车
          </div>
          <div class="division">
            <img src="../assets/index/img_line.png">
          </div>
          <div class="detail">
            <ul>
              <li class="fl" :class="{'active': parkImgStatus[0]}" @mouseover="handleParkMouseOver1st">
                <span class="item item-1"></span>
                <span class="des">解决方案</span>
              </li>
              <li class="fl" :class="{'active': parkImgStatus[1]}" @mouseover="handleParkMouseOver2ed">
                <span class="item item-2"></span>
                <span class="des">系统前端</span>
              </li>
              <li class="fl" :class="{'active': parkImgStatus[2]}" @mouseover="handleParkMouseOver3th">
                <span class="item item-3"></span>
                <span class="des">实现效果</span>
              </li>
            </ul>
            <div class="parkImg">
              <img src="../assets/project/img_soluttion.png" v-show="parkImgStatus[0]">
              <img src="../assets/project/img_system.png" v-show="parkImgStatus[1]">
              <img src="../assets/project/img_effect.png" v-show="parkImgStatus[2]">
            </div>
            <div>
              <img src="../assets/project/img_bigimg01.png">
            </div>
          </div>
        </div>
        <div id="parking">
          <div class="title">
            封闭式停车场
          </div>
          <div class="division">
            <img src="../assets/index/img_line.png">
          </div>
          <div class="detail">
            <ul>
              <li class="fl" :class="{'active': parkingImgStatus[0]}" @mouseover="handleParkingMouseOver1st">
                <span class="item item-1"></span>
                <span class="des">停车流程</span>
              </li>
              <li class="fl" :class="{'active': parkingImgStatus[1]}" @mouseover="handleParkingMouseOver2ed">
                <span class="item item-2"></span>
                <span class="des">系统前端</span>
              </li>
              <li class="fl" :class="{'active': parkingImgStatus[2]}" @mouseover="handleParkingMouseOver3th">
                <span class="item item-3"></span>
                <span class="des">实现效果</span>
              </li>
            </ul>
            <div class="parkImg">
              <img src="../assets/project/img_process.png" v-show="parkingImgStatus[0]">
              <img src="../assets/project/img_system02.png" v-show="parkingImgStatus[1]">
              <img src="../assets/project/img_effect02.png" v-show="parkingImgStatus[2]">
            </div>
            <div>
              <img src="../assets/project/img_bigimg02.png">
            </div>
          </div>
        </div>
        <div id="parklot">
          <div class="title">
            共享停车位
          </div>
          <div class="division">
            <img src="../assets/index/img_line.png">
          </div>
          <div class="parklotImg">
            <img src="../assets/project/img_cartext.png">
          </div>
          <div>
            <img src="../assets/project/img_bigimg03.png">
          </div>
        </div>
        <div id="advantage">
          <div class="title">
            方案优势
          </div>
          <div class="division">
            <img src="../assets/index/img_line.png">
          </div>
          <div class="advantageImg">
            <img src="../assets/project/img_advantage.png">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      parkImgStatus: [true, false, false],
      parkingImgStatus: [true, false, false]
    }
  },
  mounted() {},
  created() {},
  methods: {
    handleParkMouseOver1st() {
      this.parkImgStatus = [true, false, false]
    },
    handleParkMouseOver2ed() {
      this.parkImgStatus = [false, true, false]
    },
    handleParkMouseOver3th() {
      this.parkImgStatus = [false, false, true]
    },
    handleParkingMouseOver1st() {
      this.parkingImgStatus = [true, false, false]
    },
    handleParkingMouseOver2ed() {
      this.parkingImgStatus = [false, true, false]
    },
    handleParkingMouseOver3th() {
      this.parkingImgStatus = [false, false, true]
    }
  }
}
</script>
<style lang="less" scoped>
.home-page {
  width: 1920px;
  & > div {
    width: 1920px;
    height: 800px;
  }
  #banner {
    width: 1920px;
    height: 500px;
    img {
      height: 100%;
      width: 100%;
    }
  }
  #content {
    width: 1920px;
    height: 4160px;
    position: relative;
    background-color: #fff;
    .container {
      width: 1200px;
      position: absolute;
      left: 360px;
      .title {
        height: 50px;
        line-height: 50px;
        font-size: 30px;
        font-weight: bold;
        color: #3296fa;
      }
      .division {
        height: 20px;
        line-height: 20px;
        text-align: center;
      }
      #park {
        height: 1250px;
        box-sizing: border-box;
        padding-top: 60px;
        .detail {
          margin-top: 60px;
          .parkImg{
            margin-bottom: 60px;
          }
          .active{
            .item-1 {
              background: url("../assets/project/icon_soluttionb.png");
            }
            .item-2 {
              background: url("../assets/project/icon_systemb.png");
            }
            .item-3 {
              background: url("../assets/project/icon_effectb.png");
            }
            .des{
              color: #3296fa;
              border-bottom: solid 4px #3296fa;
            }
          }
          .item-1 {
            background: url("../assets/project/icon_soluttiong.png");
          }
          .item-2 {
            background: url("../assets/project/icon_systemg.png");
          }
          .item-3 {
            background: url("../assets/project/icon_effectg.png");
          }
        }
      }
      #parking {
        width: 1920px;
        height: 1290px;
        position: absolute;
        left: -360px;
        box-sizing: border-box;
        padding-top: 60px;
        background-color: #fafafa;
        ul{
          margin-left: 821px;
        }
        .detail {
          margin-top: 60px;
          .parkImg{
            margin-bottom: 60px;
          }
          .active{
            .item-1 {
              background: url("../assets/project/icon_processb.png");
            }
            .item-2 {
              background: url("../assets/project/icon_systemb.png");
            }
            .item-3 {
              background: url("../assets/project/icon_effectb.png");
            }
            .des{
              color: #3296fa;
              border-bottom: solid 4px #3296fa;
            }
          }
          .item-1 {
            background: url("../assets/project/icon_processg.png");
          }
          .item-2 {
            background: url("../assets/project/icon_systemg.png");
          }
          .item-3 {
            background: url("../assets/project/icon_effectg.png");
          }
        }
      }
      #parklot{
        height: 980px;
        position: absolute;
        top: 2540px;
        box-sizing: border-box;
        padding-top: 60px;
        .parklotImg{
          margin: 60px 0;
        }
      }
      #advantage{
        width: 1920px;
        position: absolute;
        top: 3520px;
        left: -360px;
        background-color: #fafafa;
        height: 640px;
        box-sizing: border-box;
        padding-top: 60px;
        .advantageImg{
          margin-top: 100px;
        }
      }
      ul {
        height: 97px;
        margin-left: 461px;
        margin-bottom: 64px;
        li {
          width: 66px;
          margin-right: 40px;
          .item {
            display: inline-block;
            width: 66px;
            height: 52px;
            background-position: center !important;
            background-repeat: no-repeat !important;
          }
          .des {
            display: inline-block;
            width: 66px;
            height: 45px;
            line-height: 45px;
            color: #b4b4b4;
            font-size: 16px;
          }
        }
      }
    }
  }
}
</style>
