<template>
  <div class="not-found">
    <div class="main">
      <img src="../assets/imgs/img_404.png">
      <div class="text">抱歉，您访问的页面不存在</div>
      {{href}}
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      title: '页面出错啦',
      href: ''
    }
  },
  created () {
    document.title = this.title
    if (this.href.indexOf('https://open.weixin.qq.com/connect/oauth2/authorize') !== -1) {
      this.href = window.location.href
      location.href = this.href
    }
  }
}
</script>
<style lang="less" scoped>
  .not-found {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 99;
    background: #fff;
    .main {
      width: 100%;
      height: 3.3rem;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      text-align: center;
      img {
        width: 1.67rem;
        height: 2.41rem;
      }
      .text {
        color: #888;
        margin-top: 0.14rem;
        font-size: .14rem;
      }
      a {
        display: inline-block;
        width: 1.1rem;
        height: .35rem;
        line-height: .35rem;
        color: #3296FA;
        background: #fff;
        border-radius: .18rem;
        font-size: .12rem;
        border: solid #3296FA .01rem;
        margin-top: .26rem;
      }
    }
  }
</style>
