<template>
  <div class='login-wrap'>
    <div class="layout">
      <div class="login-title">环猫后台管理系统</div>
      <div class="login-box">
        <div class="form-item">
          <el-input placeholder="请输入手机号" v-model.lazy="phone" @blur="validatorPhone"></el-input>
          <span v-if="phoneError" class="login-tip">请输入正确的手机号！</span>
        </div>
        <div class="form-item">
          <el-input placeholder="请输入登录密码" v-model="pwd" type="password" @blur="validatorPwd"></el-input>
          <span v-if="pwdError" class="login-tip">请输入正确的密码！</span>
        </div>
        <div class="form-item">
          <span v-if="emptyFlag" class="login-tip">用户名或密码不能为空！</span>
          <div class="login-btn" @click="login">登录</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Utils} from '../../common/util.js'
let utils = new Utils()
export default {
  name: 'Login',
  data () {
    return {
      phone: '',
      phoneError: false,
      pwd: '',
      pwdError: false,
      emptyFlag: false
    }
  },
  methods: {
    //验证手机号
    validatorPhone: function(){
      this.phoneError = !/^[1][3,4,5,7,8][0-9]{9}$/.test(this.phone)
      if(this.phoneError){
        this.emptyFlag = false
      }
    },
    //验证密码非空
    validatorPwd: function(){
      this.pwdError = !/^[a-zA-Z_0-9]{6,9}$/.test(this.pwd)
      if(this.pwdError){
        this.emptyFlag = false
      }
    },
    //ajax 登录
    login: function(){
      if( this.phone === '' || this.pwd === '' ){
        this.emptyFlag = true
      }else{
        this.emptyFlag = false
        let data = {
          phone: this.phone,
          pwd: this.pwd
        }
        utils.get('product/getbusinesstypedata.json', {id: 28}).then(res => {
          console.log(res)
          if( res.success ){
            console.log(res.success)
            this.$router.push( 'home' )
          }
        })
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../../style/login.less" lang="less" scoped>
</style>
