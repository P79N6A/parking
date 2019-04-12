<template>
  <div class="author-box">
    <transition name="fade">
      <loadingImg v-if="loading"></loadingImg>
    </transition>
    <div class="user-info">
      <div class="user-logo">
        <img v-lazy="logo">
      </div>
      <div class="user-name">{{name}}</div>
    </div>
    <ul>
      <li class="cars" @click="toMyCars">
        <div class="icon"></div>
        <div class="list-title">我的车辆</div>
        <div class="arrow"></div>
      </li>
      <li class="bills" @click="toMyBills">
        <div class="icon"></div>
        <div class="list-title">我的账单</div>
        <div class="arrow"></div>
      </li>
      <li class="phone">
        <div class="icon"></div>
        <div class="list-title">手机号码</div>
        <div class="text">{{phone}}</div>
      </li>
    </ul>
    <div class="cover" v-if="cover">
      <div class="container">
        <div class="alert-title">提示</div>
        <div class="alert-info">
          您还没有绑定车辆，请先下载APP并绑定车辆。
        </div>
        <div class="btn cancel" @click="cover = !cover">取消</div>
        <div class="btn confirm" @click="toDownload">下载APP</div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from '@/utils/request'
import {login, wechatAppId} from '@/utils/api'
import msg from '@/utils/notification'
import loadingImg from '@/components/loading'
export default {
  components: {loadingImg},
  data () {
    return {
      loading: true,
      cover: false, // 遮罩
      title: '个人中心',
      logo: '' || this.$store.getters.userInfo.headimgurl, // 用户头像
      name: '' || this.$store.getters.userInfo.nickName, // 用户昵称
      phone: '' || this.$store.getters.userInfo.phoneNumber, // 用户手机号
      carCount: 0 || this.$store.getters.userInfo.carCount // 用户在app绑定车辆数目[实名认证用户]
    }
  },
  created () {
    document.title = this.title
    this.getCode()
    // this.appid()
    if (this.$store.getters.userInfo.accessToken) {
      this.loading = false
    }
  },
  methods: {
    getCode () {
      if (this.$route.query.code) {
        let tpl1 = {code: this.$route.query.code}
        axios({url: '/platform/security/getWechatInfoJsapi', method: 'post', params: tpl1}).then(res => {
          if (res.data.code === 1) {
            let data = res.data.data
            this.logo = data.headimgurl
            this.name = data.nickName
            let tpl2 = {account: data.unionid, accountType: '2'}
            this.$store.dispatch('saveUserInfo', data)
            // code获取成功，判断用户是否再app绑定微信
            this.checkWechatIsRegister(tpl2)
          } else {
            if (res.data.code === 10805 && this.$store.getters.userInfo.accessToken) {
              // 用户已经登入并从下个页面返回本页
            } else {
              msg('', '', '请在公众号："逐一科技"中操作!')
            }
          }
        })
      } else if (!this.$store.getters.userInfo.accessToken) {
        msg('', '', '请在公众号："逐一科技"中操作!')
      }
    },
    checkWechatIsRegister (json) {
      axios({url: '/platform/security/accountexist', method: 'post', params: json}).then(res => {
        if (res.data.code === 1) {
          let data = res.data.data
          if (data.registered) {
            // 注册过,自动登陆
            let loginTpl = {account: this.$store.getters.userInfo.unionid, terminateType: 3, loginType: 3}
            login(loginTpl).then(res => {
              if (res.data.code === 1) {
                this.loading = false
                this.carCount = res.data.data.carCount
                this.phone = res.data.data.phoneNumber
                let fullInfo = this.$store.getters.userInfo
                Object.assign(fullInfo, res.data.data)
                this.$store.dispatch('saveUserInfo', fullInfo)
              } else {
                msg('', '', res.data.message)
              }
            })
          } else {
            // 没有注册,跳转页面
            /** 用户注册 路由 */
            this.$router.replace({name: 'userPhone', query: {returnURL: 'home'}})
          }
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    checkCars () {
      if (this.carCount === 0) return false
      return true
    },
    toMyCars () {
      if (this.checkCars()) {
        /** 我的车辆 路由 */
        this.$router.push({name: 'userCar'})
      } else {
        this.cover = !this.cover
      }
    },
    toMyBills () {
      if (this.checkCars()) {
        /** 我的账单 路由 */
        this.$router.push({name: 'userBill'})
      } else {
        this.cover = !this.cover
      }
    },
    toDownload () {
      location.href = 'http://m.zhuyitech.com/download'
    },
    appid () {
      wechatAppId().then(res => {
        if (res.data.code === 1) {
          this.$store.dispatch('setAppId', res.data.data.appId)
        } else {
          msg('', '', res.data.message)
        }
      })
    }
  }
}
</script>
<style scoped lang="less">
.author-box {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fafafa;
  .user-info {
    width: 100%;
    height: 1.5rem;
    background: url("../assets/index/bg_me.png") no-repeat;
    background-size: 100% 100%;
    position: relative;
    .user-logo {
      width: .6rem;
      height: .6rem;
      border-radius: 50%;
      position: absolute;
      top: .3rem;
      left: 1.58rem;
      img {
        border-radius: 50%;
        width: 100%;
        height: 100%;
      }
    }
    .user-name {
      width: 100%;
      height: .26rem;
      line-height: .26rem;
      font-size: .18rem;
      position: absolute;
      top: 1.05rem;
      text-align: center;
      color: #fff;
    }
  }
  ul {
    width: 100%;
    background: #fff;
    li {
      height: .5rem;
      width: 3.6rem;
      margin-left: .15rem;
      line-height: .5rem;
      position: relative;
      border-bottom: 0.01rem solid #EFEFF4;
      .icon {
        width: .17rem;
        height: .17rem;
        background: url("../assets/index/icon_mycar.png") no-repeat;
        background-size: 100% 100%;
        position: absolute;
        top: .17rem;
        left: .15rem;
      }
      .list-title {
        width: 1rem;
        height: 100%;
        position: absolute;
        left: .52rem;
        text-align: left;
        font-size: .16rem;
        color: #222;
      }
      .arrow {
        width: .06rem;
        height: .11rem;
        background: url("../assets/index/icon_arrowgrey.png") no-repeat;
        background-size: 100% 100%;
        position: absolute;
        top: .2rem;
        right: .15rem;
      }
      .text {
        width: 1.5rem;
        height: 100%;
        position: absolute;
        right: .15rem;
        text-align: right;
        font-size: .16rem;
        color: #64C800;
      }
    }
    .bills {
      .icon {
        background: url("../assets/index/icon_bill.png") no-repeat;
        background-size: 100% 100%;
      }
    }
    .phone {
      border-bottom: none;
      .icon {
        background: url("../assets/index/icon_phonecode.png") no-repeat;
        background-size: 100% 100%;
      }
    }
  }
  .cover {
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, .5);
    position: absolute;
    top: 0;
    left: 0;
    z-index: 10;
    .container {
      width: 3rem;
      position: absolute;
      top:50%;
      left: 50%;
      transform: translate(-50%,-50%);
      -webkit-transform: translate(-50%,-50%);
      background: #fff;
      border-radius: .05rem;
    }
    .alert-title {
      position: absolute;
      top: .15rem;
      left: .15rem;
      width: .28rem;
      height: .2rem;
      line-height: .2rem;
      font-size: .14rem;
      color: #B4B4B4;
    }
    .alert-info {
      width: 2.74rem;
      line-height: .25rem;
      font-size: .16rem;
      color: #222;
      text-align: left;
      margin: .55rem .13rem;
    }
    .btn {
      position: absolute;
      bottom: .2rem;
      height: .2rem;
      line-height: .2rem;
    }
    .confirm {
      right: .16rem;
      color: #3296FA;
    }
    .cancel {
      right: 1.08rem;
      color: #B4B4B4;
    }
  }
}
</style>
