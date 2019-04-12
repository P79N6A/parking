<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  created () {
    if (navigator.userAgent.indexOf('MicroMessenger') === -1) {
      /** 404 路由 */
      this.$router.replace({name: 'notFound'})
    }
  }
}
</script>

<style lang="less">
  #app {
    font-family: "PingFang SC", "Microsoft YaHei", 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    width: 100%;
    -webkit-overflow-scrolling: touch;
  }
  html {
    width: 100%;
    height: 100%;
    font-size: 100px;
    font-size: calc(100vw / 3.75);
  }

  body {
    margin: 0;
    padding: 0;
    background: #FFF;
    width: 100%;
    height: 100%;
    font-size: 14px;
    font-family: "PingFang SC", "Microsoft YaHei", Arial, "Helvetica Neue Light", "Source Han Sans CN", sans-serif;
  }

  .hide {
    display: none;
  }

  ul,
  li,
  dl,
  dd,
  dt {
    list-style: none;
    padding: 0;
    margin: 0;
  }

  a,
  a:visited {
    text-decoration: none;
    color: #000;
  }

  p {
    margin: 0;
  }

  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    margin: 0;
  }

  .fl { float: left; }

  .fr { float: right; }

  .fx {
    &::after {
      content: '';
      display: block;
      clear: both;
    }
  }

  .tb {
    &::before {
      content: '';
      display: table;
    }
  }
  .rotate {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    height: 100%;
    width: 100%;
    background: #3296FA;
    z-index: 99;
    .tip {
      color: #FFF;
      font-size: .25rem;
      width: 80%;
      height: 20%;
      text-align: center;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
  }
</style>
