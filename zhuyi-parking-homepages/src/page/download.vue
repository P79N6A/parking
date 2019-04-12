<template>
  <div class="download-box">
    <img src="../assets/download/img_bg.png">
    <div class="page"></div>
    <div class="logo"></div>
    <div class="download-btn ios" @click="handleClickIOS"></div>
    <div class="download-btn android" @click="handleClickAndroid"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  methods: {
    handleClickIOS () {
      location.href = 'https://itunes.apple.com/cn/app/linkmore/id1354892484?mt=8'
    },
    handleClickAndroid () {
      location.href = 'http://www.zhuyitech.com/renyitingche.apk'
    }
  },
  created () {
    document.title = '任意停车-App下载'
  }
}
</script>
<style lang="less" scoped>
  .download-box{
    position: relative;
    // min-height: 2000px;
    width: 1920px;
    img{
      width: 100%;
      height: 800px;
    }
    div{
      position: absolute;
      background-size: 100% 100%;
    }
    .page{
      width: 597px;
      height: 494px;
      background: url("../assets/download/img_page.png");
      top: 174px;
      left: 359px;
    }
    .logo{
      width: 498px;
      height: 153px;
      background: url("../assets/download/img_logo.png");
      top: 254px;
      left: 1043px;
    }
    .download-btn{
      width: 207px;
      height: 66px;
      top: 467px;
      left: 1043px;
      background: url("../assets/download/down_iphpne.png");
      &.android{
        left: 1290px;
        background: url("../assets/download/down_android.png");
      }
    }
  }
</style>
