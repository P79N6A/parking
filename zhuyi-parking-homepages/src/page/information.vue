<template>
  <div class="info-box" ref="info" id="scrollInfo">
    <div class="main">
      <div class="cont">
        <div class="breadcrumb">
          <span>位置 : </span>
          <router-link to="/" style="color: #b4b4b4;">首页</router-link>
          <span> > </span>
          <router-link to="/news" style="color: #b4b4b4;">资讯</router-link>
          <span> > </span>
          <span style="color: #444;">详情</span>
        </div>
        <div class="body">
          <h1>{{title}}</h1>
          <h3>{{time}}</h3>
          <div class="line"></div>
          <div class="text" v-html="content"></div>
        </div>
      </div>
      <ToTop v-if="toTop" :dom="dom"></ToTop>
      <Footer></Footer>
    </div>
  </div>
</template>
<script>
import Footer from '@/components/footer'
import axios from '@/util/request'
import ToTop from '@/components/toTop/other.vue'
export default {
  components: { Footer, ToTop },
  data() {
    return {
      title: '',
      content: '',
      time: '',
      toTop: false,
      dom: ''
    }
  },
  created() {
    this.initData()
  },
  mounted() {
    this.dom = document.getElementById('scrollInfo')
    this.$refs.info.addEventListener('scroll', this.watchScroll)
  },
  methods: {
    watchScroll() {
      let top = this.$refs.info.scrollTop
      if (top >= 600) {
        this.toTop = true
      } else {
        this.toTop = false
      }
    },
    initData() {
      let data = {
        id: this.$route.params.id
      }
      axios({ url: '/portal/front/news/getById', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            this.title = data.data.title
            this.content = data.data.content
            this.time = data.data.creationTime
          } else {
            this.$router.replace({ path: '/notFound' })
          }
        })
    },
  }
}
</script>
<style lang="less" scoped>
  .info-box {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 101;
    background: #c5c5c5;
    overflow-y: auto;
    .main {
      display: inline-block;
      width: 1920px;
      min-height: 1200px;
      background: #fff;
    }
    .cont {
      width: 1200px;
      display: inline-block;
    }
    .breadcrumb {
      width: 100%;
      height: 100px;
      line-height: 100px;
      text-align: left;
      color: #b4b4b4;
    }
    .body {
      min-height: 1100px;
    }
    h1 {
      padding: 10px;
    }
    h3 {
      padding: 4px;
      font-weight: normal;
      color: #b4b4b4;
    }
    .line {
      width: 100%;
      height: 1px;
      background: #eee;
      margin: 20px 0;
    }
    .text {
      text-align: left;
      text-indent: 28px;
      line-height: 28px;
      margin-bottom: 20px;
    }
  }
</style>
