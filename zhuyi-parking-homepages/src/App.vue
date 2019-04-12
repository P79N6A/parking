<template>
  <div id="app">
    <div class="herder">
      <div class="top-bar fx">
        <div class="logo fl">
          <!-- <img src="./assets/icon_logo.png" class="fl"> -->
          <img src="./assets/img_logo.svg" class="fl">
        </div>
        <NavBar class="fr"></NavBar>
      </div>
    </div>
    <div class="main-box">
      <router-view/>
    </div>
    <!-- <ToTop v-if="show"></ToTop> -->
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from '@/components/navbar'
import Footer from '@/components/footer'
// import ToTop from '@/components/toTop'
export default {
  name: 'App',
  components: { NavBar, Footer },
  data() {
    return {
      show: false
    }
  },
  mounted() {
    window.addEventListener('scroll', this.watchScroll)
  },
  methods: {
    watchScroll() {
      let top = document.body.scrollTop || document.documentElement.scrollTop
      // console.log(top)
      if (top >= 800) {
        this.show = true
      } else {
        this.show = false
      }
    }
  }
}
</script>

<style lang="less">
#app {
  font-family: MicrosoftYaHei;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background: #c5c5c5;
}
@font-face {
  font-family: 'iconfont';
  src: url('../static/styles/iconfont.eot');
  src: url('../static/styles/iconfont.eot?#iefix') format('embedded-opentype'),
  url('../static/styles/iconfont.woff') format('woff'),
  url('../static/styles/iconfont.ttf') format('truetype'),
  url('../static/styles/iconfont.svg#iconfont') format('svg');
}
.iconfont{
  font-family:"iconfont" !important;
  font-size:16px;font-style:normal;
  -webkit-font-smoothing: antialiased;
  -webkit-text-stroke-width: 0.2px;
  -moz-osx-font-smoothing: grayscale;
}
.herder {
  position: fixed;
  width: 100%;
  height: 80px;
  text-align: center;
  background: #fff;
  font-weight: 600;
  z-index: 100;
  .top-bar {
    width: 1220px;
    min-width: 1220px;
    display: inline-block;
    .logo {
      height: 80px;
      width: 420px;
      display: inline-block;
      line-height: 80px;
      img {
        width: 151px;
        height: 46px;
        margin-top: 17px;
      }
    }
  }
}
.main-box {
  width: 1920px;
  min-width: 1920px;
  padding-top: 80px;
  display: inline-block;
  position: relative;
}
html{
  width: 100%;
  height: 100%;
}
body{
  margin: 0;
  padding: 0;
  background: #fff;
  width: 100%;
  height: 100%;
  font-size: 14px;
}

.hide{
  display: none;
}

ul,li,dl,dd,dt{
  list-style: none;
  padding: 0;
  margin: 0;
}

a,a:visited{
  text-decoration: none;
  color: #000;
}

p {
  margin: 0;
}

h1,h2,h3,h4,h5,h6{
  margin: 0;
}

.fl {float: left;}

.fr {float: right;}

.fx{
  &:after{
    content: '';
    display: block;
    clear: both;
  }
}

.tb{
  &:before{
    content: '';
    display: table;
  }
}

.el-menu--horizontal .el-menu-item{
  border-bottom: none;
}

.el-menu--horizontal>.el-menu-item:hover, .el-menu--horizontal>.el-submenu.is-active .el-submenu__title, .el-menu--horizontal>.el-submenu:hover .el-submenu__title{
  border-bottom: none;
}

.el-menu-item{
  border-bottom: none !important;
}
</style>
