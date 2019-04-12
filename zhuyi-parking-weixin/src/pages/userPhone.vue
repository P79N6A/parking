<template>
  <div class="user-phone-box">
    <transition name="fade">
      <loadingImg v-if="loading"></loadingImg>
    </transition>
    <div class="logo"></div>
    <div class="app-name">任意停车</div>
    <label class="user-phone">
      <input type="text" maxlength="11" placeholder="请输入手机号码" v-model.trim="userPhone">
      <div class="clear-box" v-if="userPhone"  @click="userPhone = ''"></div>
    </label>
    <label class="user-code">
      <input type="text" maxlength="6" placeholder="请输入验证码" v-model.trim="userCode">
      <div class="clear-box"  v-if="userCode" @click="userCode = ''"></div>
      <div class="get-code" :class="{'disabled': codeDisabled}" @click="getCode">{{statusText}}</div>
    </label>
    <div class="text">
      <span>点击提交即表示您已阅读并同意</span>
      <router-link :to="{name: 'userProtocol'}" style="color: #3296FA;">《用户使用协议》</router-link>
    </div>
    <div class="btn" :class="{'disabled': btnStatus}" @click="submitUserInfo">提 交</div>
  </div>
</template>
<script>
import axios from '@/utils/request'
import msg from '@/utils/notification'
import {wechatRegister, login} from '@/utils/api'
import loadingImg from '@/components/loading'
export default {
  components: {loadingImg},
  data () {
    return {
      loading: false,
      title: '绑定手机',
      statusText: '获取验证码',
      userPhone: '',
      userCode: '',
      register: {
        phone: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/
      },
      codeDisabled: false // 验证码按钮
    }
  },
  created () {
    document.title = this.title
  },
  methods: {
    getCode () {
      if (this.codeDisabled) return
      if (this.checkPhone()) {
        // 调用接口判断手机号是否被绑定过,通过验证时发送验证码
        this.getCodeApi()
      }
    },
    showStatusText () {
      let clock = 60
      const timer = setInterval(() => {
        if (clock <= 1) {
          clearInterval(timer)
          this.codeDisabled = false
          this.statusText = '获取验证码'
        } else {
          clock--
          this.codeDisabled = true
          this.statusText = clock + 'S后获取'
        }
      }, 1000)
    },
    submitUserInfo () {
      if (this.userPhone.length && this.userCode.length) {
        // 提交注册
        if (this.checkPhone()) {
          // 成功时,执行微信注册接口
          let tpl = {
            phoneNumber: this.userPhone,
            verifyCode: this.userCode,
            smsRequestId: JSON.parse(localStorage.getItem('requestId'))[this.userPhone]
          }
          axios({url: '/platform/vcode/check', method: 'post', params: tpl}).then(res => {
            if (res.data.code === 1 && res.data.data.valid) {
              let tpl2 = {}
              Object.assign(tpl2, this.$store.getters.userInfo, tpl)
              tpl2.headImgUrl = tpl2.headimgurl
              wechatRegister(tpl2).then(res => {
                if (res.data.code === 1) {
                  this.loading = true
                  // 执行登入，并返回前一个页面
                  let loginTpl = {account: tpl2.unionid, terminateType: 3, loginType: 3}
                  login(loginTpl).then(res => {
                    if (res.data.code === 1) {
                      let fullInfo = this.$store.getters.userInfo
                      Object.assign(fullInfo, res.data.data)
                      this.$store.dispatch('saveUserInfo', fullInfo)
                      localStorage.removeItem('requestId')
                      this.$router.replace({name: this.$route.query.returnURL})
                    }
                  })
                } else {
                  msg('', '', res.data.message)
                }
              })
            } else {
              msg('', '', res.data.message)
            }
          })
        }
      }
    },
    checkPhone () {
      if (this.register.phone.exec(this.userPhone) !== null) {
        return true
      } else {
        return false
      }
    },
    getCodeApi () {
      let tpl = {phoneNumber: this.userPhone, verifyType: 1}
      let cachePhone = this.userPhone // 防止贱人没收到短信就删号码
      axios({url: '/platform/security/jsapiSendCode', method: 'post', params: tpl}).then(res => {
        let code = res.data.code
        if (code === 1) {
          msg('success', '发送短信验证码提示', '验证码已发送')
          this.showStatusText()
          this.saverequestId(cachePhone, res.data.data.requestId)
        } else if (code === 10501) {
          msg('', '发送短信验证码警告', '此手机号已被其他微信绑定,请绑定其它的手机号')
        } else {
          msg('', '发送短信验证码警告', res.data.message)
        }
      })
    },
    saverequestId (phone, str) {
      let obj = JSON.parse(localStorage.getItem('requestId')) || {}
      obj[phone] = str
      localStorage.setItem('requestId', JSON.stringify(obj))
    }
  },
  computed: {
    btnStatus () {
      if (this.userPhone.length && this.userCode.length) {
        return false
      } else {
        return true
      }
    }
  }
}
</script>
<style scoped lang="less">
.user-phone-box {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fff;
  overflow-y: auto;
  .logo {
    width: .8rem;
    height: .8rem;
    background: url("../assets/userPhone/icon_logo.png") no-repeat;
    background-size: 100% 100%;
    margin: .5rem 1.48rem .09rem;
  }
  .app-name {
    height: .24rem;
    line-height: .24rem;
    font-size: .18rem;
    color: #222;
    font-weight: bold;
  }
  label {
    display: block;
    width: 3.1rem;
    height: .5rem;
    background: #fafafa;
    border-radius: .25rem;
    position: relative;
    &::before {
      content: '';
      display: block;
      position: absolute;
      width: .23rem;
      height: .23rem;
      top: .14rem;
      left: .22rem;
    }
    .clear-box {
      width: .18rem;
      height: .18rem;
      background: url("../assets/userPhone/icon_delete.png") no-repeat;
      background-size: 100% 100%;
      position: absolute;
      top: .16rem;
    }
  }
  .user-phone {
    margin: .3rem .33rem 0;
    .clear-box {
      right: .1rem;
    }
    &::before {
      background: url("../assets/userPhone/icon_iphone.png") no-repeat;
      background-size: 100% 100%;
    }
    input {
      width: 100%;
      height: 100%;
      padding-left: .6rem;
      box-sizing: border-box;
      outline: none;
      border: none;
      background: #fafafa;
      border-radius: .25rem;
    }
  }
  .user-code {
    .clear-box {
      right: 1rem;
    }
    .get-code {
      width: .8rem;
      height: .3rem;
      line-height: .3rem;
      position: absolute;
      top: .1rem;
      left: 2.2rem;
      background: #3296FA;
      color: #fff;
      border-radius: .15rem;
      font-size: .12rem;
      &.disabled {
        background: #ddd;
      }
    }
    &::before {
      background: url("../assets/userPhone/icon_securitycode.png") no-repeat;
      background-size: 100% 100%;
    }
    margin: .2rem .33rem 0;
    input {
      width: 100%;
      height: 100%;
      padding-left: .6rem;
      box-sizing: border-box;
      outline: none;
      border: none;
      background: #fafafa;
      border-radius: .25rem;
    }
  }
  .text {
    height: .45rem;
    line-height: .45rem;
    margin-top: .15rem;
    font-size: .14rem;
  }
  .btn {
    width: 3.1rem;
    height: .5rem;
    line-height: .5rem;
    margin: .15rem .33rem 0;
    background: #3296FA;
    color: #fff;
    border-radius: .25rem;
    font-size: .16rem;
  }
  .disabled {
    background: #ddd;
  }
}
</style>
