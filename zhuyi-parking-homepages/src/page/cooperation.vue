<template>
  <div class="cooperation-page">
    <div class="banner">
      <img src="../assets/v2/icon_hzBanner.png">
    </div>
    <div class="mode-box">
      <div class="traditional">
        <img src="../assets/v2/icon_mode.png" class="mode-img">
        <div class="info-list">
          <h3>传统模式 <span>TRADITIONAL MODE</span></h3>
          <p class="fst">人工成本高</p>
          <p>收费漏洞多</p>
          <p>出入口拥堵</p>
          <p>车位易闲置</p>
          <p>用户体验感差</p>
        </div>
      </div>
      <div class="zoeeasy">
        <div class="info-list">
          <h3>任意停车 <span>ZOEEASY PARKING</span></h3>
          <p class="fst">运营成本低</p>
          <p>通行速度快</p>
          <p>无感支付快速便捷</p>
          <p>导航等特色功能多体验感好</p>
          <p>利用平台导流转化率高</p>
        </div>
        <img src="../assets/v2/ryparking.png" class="mode-img">
      </div>
    </div>
    <div class="business">
      <div class="title">
        广告投放
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <div class="cont-area">
        <div class="area1">
          <img src="../assets/v2/icon_city.png">
          <div class="info">为停车场创造商业合作机会</div>
          <div class="sub-info">为国内停车行业创造商业价值</div>
          <div class="icon">市场合作</div>
        </div>
        <div class="area2">
          <img src="../assets/v2/hz.png">
          <div class="info">欢迎停车管理商或经营者垂询</div>
          <div class="sub-info">一站式运营管理体系支持</div>
          <div class="icon">合作招商</div>
        </div>
      </div>
    </div>
    <div class="ad">
      <div class="title">
        广告投放
      </div>
      <div class="division">
        <img src="../assets/index/img_line.png">
      </div>
      <img src="../assets/v2/adBox.png" class="ad-img">
    </div>
    <div class="customer-box">
      <div class="v2-add-box">
        <div class="left">
          <img src="../assets/cooperation/icon_company.png">
          <div style="margin-top: 18px;"><i class="icon iconfont">&#xe647;</i> &nbsp;浙江省杭州市富阳区春秋北路591号二楼</div>
          <div><i class="icon iconfont">&#xe60a;</i> &nbsp;4008-872-866</div>
          <div><i class="icon iconfont">&#xe68b;</i> &nbsp;service@zhuyitech.com</div>
        </div>
        <div class="right">
          <div class="taps">
            <div class="tap-left" :class="{'active': currentType[0]}" @click="handleClick0">
              商业合作
            </div>
            <div class="tap-right" :class="{'active': currentType[1]}" @click="handleClick1">
              广告投放
            </div>
          </div>
          <div class="inputs">
            <label>
              <div class="customer-name">
                <span>姓名</span>
                <span style="color: red;">*</span>
              </div>
              <input type="text" maxlength="10" v-model.trim="customerName">
            </label>
            <label>
              <div class="customer-phone">
                <span>电话</span>
                <span style="color: red;">*</span>
              </div>
              <input type="text" maxlength="20" v-model.trim="customerPhone">
            </label>
            <label>
              <div class="customer-email">
                <span>邮箱</span>
              </div>
              <input type="text" maxlength="60" v-model.trim="customerEmail">
            </label>
            <label>
              <div class="customer-co">
                <span>公司名称</span>
              </div>
              <input type="text" maxlength="20" v-model.trim="customerCo">
            </label>
            <div class="sub-btn" @click="handleSubmit">
              <!--<div class="sub-btn" @click="checkRegest">-->
              提交
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from '@/util/request'
import msg from '@/util/notification'
export default {
  data() {
    return {
      currentType: [true, false],
      type: 1,
      customerName: '',
      customerPhone: '',
      customerEmail: '',
      customerCo: '',
      regest: {
        phone: /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
        email: /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/
      }
    }
  },
  methods: {
    handleClick0() {
      this.currentType = [true, false]
      this.type = 1
    },
    handleClick1() {
      this.currentType = [false, true]
      this.type = 2
    },
    handleSubmit() {
      let data = {
        type: this.type,
        name: this.customerName,
        telephone: this.customerPhone,
        mail: this.customerEmail,
        company: this.customerCo
      }
      // 加入正则验证，并且提交成功清空列表
      if (!this.checkRegest()) return
      axios({ url: '/portal/front/busi/add', method: 'post', data })
        .then(res => {
          if (res.data.code === 1) {
            this.customerName = ''
            this.customerPhone = ''
            this.customerEmail = ''
            this.customerCo = ''
            msg('success', '提示', '提交成功~')
          } else {
            msg('error', '', res.data.message)
          }
        })
    },
    checkRegest() {
      if (this.customerName === '' || this.customerPhone === '') {
        msg('', '', '名称和联系方式必填')
        return false
      } else {
        if (this.regest.phone.exec(this.customerPhone) !== null) {
          // console.log('正则通过')
        } else {
          msg('', '', '请输入正确的联系方式')
          return false
        }
        if (this.regest.email.exec(this.customerEmail) !== null || this.customerEmail === '') {
          // console.log('正则通过')
        } else {
          msg('', '', '邮箱格式不对')
          return false
        }
        return true
      }
    }
  }
}
</script>
<style lang="less" scoped>
  .cooperation-page {
    min-height: 1200px;
    .banner {
      width: 1920px;
      height: 500px;
      img {
        width: 100%;
        height: 500px;
      }
    }
    .mode-box {
      width: 1920px;
      height: 918px;
      position: relative;
      background: #fff;
      & > div {
        div,img {
          position: absolute;
          text-align: left;
          font-size: 16px;
          h3 {
            font-size: 36px;
            color: #3D3D5A;
            font-weight: normal;
            span {
              font-size: 18px;
              font-weight: normal;
            }
          }
          p {
            line-height: 36px;
            color: #6F7998;
            padding-left: 2px;
            &.fst {
              margin-top: 18px;
            }
          }
        }
      }
      .traditional {
        .mode-img {
          width: 388px;
          height: 388px;
          top: 84px;
          left: 440px;
        }
        .info-list {
          top: 146px;
          left: 1093px;
        }
      }
      .zoeeasy {
        .mode-img {
          width: 631px;
          height: 321px;
          top: 515px;
          left: 860px;
        }
        .info-list {
          top: 567px;
          left: 343px;
        }
      }
    }
    .business {
      width: 100%;
      height: 930px;
      background: #fafafa;
      position: relative;
      padding-top: 30px;
      box-sizing: border-box;
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
      .cont-area {
        & > div {
          div,img {
            position: absolute;
          }
        }
        .icon {
          width: 140px;
          height: 40px;
          line-height: 40px;
          background: #3296FA;
          border-radius: 20px;
          color: #fff;
          font-size: 20px;
        }
        .area1 {
          img {
            width: 580px;
            height: 340px;
            top: 176px;
            left: 344px;
          }
          .info {
            font-size: 28px;
            /*font-weight: bold;*/
            top: 350px;
            left: 1000px;
            color: #222;
          }
          .sub-info {
            top: 400px;
            left: 1000px;
            font-size: 20px;
            color: #666;
          }
          .icon {
            top: 453px;
            left: 1000px;
          }
        }
        .area2 {
          img {
            width: 580px;
            height: 340px;
            top: 543px;
            left: 971px;
          }
          .info {
            font-size: 28px;
            /*font-weight: bold;*/
            top: 706px;
            left: 531px;
            color: #222;
          }
          .sub-info {
            top: 756px;
            left: 675px;
            font-size: 20px;
            color: #666;
          }
          .icon {
            top: 808px;
            left: 754px;
          }
        }
      }
    }
    .ad {
      width: 1920px;
      height: 614px;
      background: #fff;
      padding-top: 42px;
      box-sizing: border-box;
      position: relative;
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
      .ad-img {
        width: 1200px;
        height: 370px;
        position: absolute;
        left: 360px;
        top: 186px;
      }
    }
    .customer-box {
      width: 100%;
      height: 565px;
      background: #fff;
      position: relative;
      .v2-add-box {
        position: absolute;
        left: 360px;
        width: 1200px;
        height: 500px;
        /*border: 1px solid rgba(98,149,226,0.2);*/
        box-shadow: 1px 1px 2px 0 rgba(98, 149, 226, .2), -1px -1px 2px 0 rgba(98, 149, 226, .2);
        border-radius: 3px;
        & > div {
          width: 500px;
          height: 380px;
          position: absolute;
          top: 26px;
          .iconfont {
            color: #aab4be;
          }
        }
        .left {
          left: 44px;
          img {
            width: 530px;
            height: 320px;
          }
          div {
            font-size: 14px;
            text-align: left;
            padding: 6px 0;
            color: #222;
          }
        }
        .right {
          left: 660px;
          .taps {
            width: 476px;
            height: 53px;
            margin-left: 12px;
            /*border-bottom: 1px solid #eee;*/
            div {
              height: 52px;
              width: 120px;
              position: absolute;
              top: 0;
              border-bottom: 2px solid transparent;

              font-size: 18px;
              line-height:54px;
              color: #888;
              &:hover {
                border-bottom: 2px solid #3296FA;
                cursor: pointer;
                &:after {
                  content: '';
                  display: block;
                  width: 0;
                  height: 0;
                  border-style: solid;
                  border-width: 0 9px 9px;
                  border-color: transparent transparent #3296FA;
                  position: absolute;
                  bottom: 0;
                  left: 52px;
                }
              }
              &.active {
                border-bottom: 2px solid #3296FA;
                color: #3296FA;
                &:after {
                  content: '';
                  display: block;
                  width: 0;
                  height: 0;
                  border-style: solid;
                  border-width: 0 9px 9px;
                  border-color: transparent transparent #3296FA;
                  position: absolute;
                  bottom: 0;
                  left: 52px;
                }
              }
            }
            .tap-left {
              left: 82px;
            }
            .tap-right {
              left: 275px;
            }
          }
          .inputs {
            width: 383px;
            height: 320px;
            display: inline-block;
            label {
              display: block;
              width: 100%;
              height: 66px;
              line-height: 66px;
              border-bottom: 1px solid #eee;
              &:first-child {
                margin-top: 20px;
              }
              div {
                display: inline-block;
                float: left;
                font-size: 16px;
                color: #666;
              }
              input {
                float: right;
                border-radius: 4px;
                /*border:1px solid #eee;*/
                outline: none;
                border: none;
                width: 300px;
                height: 60px;
                padding-left: 12px;
              }
            }
            .sub-btn {
              width: 160px;
              height: 50px;
              display: inline-block;
              margin-top: 38px;
              background: #3296FA;
              color: #fff;
              font-size: 16px;
              line-height: 50px;
              border-radius: 6px;
              &:hover {
                cursor: pointer;
              }
            }
          }
        }
      }

    }
  }
</style>
