<template>
  <div class="news-box"  id="scrollNews" ref="scrollNews">
    <div class="main">
      <div class="cont">
        <div class="breadcrumb">
          <span>位置 : </span>
          <router-link to="/" style="color: #b4b4b4;">首页</router-link>
          <span> > </span>
          <span style="color: #444;">资讯</span>
        </div>
        <div class="body">
          <Swiper :items="carouselArr" link=""></Swiper>
          <div class="title-bar">
            最新资讯
            <div class="news-type">
              资讯目录
              <div class="type-lest">
                <ul>
                  <!-- 资讯太少，暂时屏蔽分类筛选 ***勿删!!!*** -->
                  <!--<li :class="{'active': active[0]}" @click="handleClick1"><dl><dd class="bg1"></dd><dt>公告</dt></dl></li>
                  <li :class="{'active': active[1]}" @click="handleClick2"><dl><dd class="bg2"></dd><dt>问答</dt></dl></li>
                  <li :class="{'active': active[2]}" @click="handleClick3"><dl><dd class="bg3"></dd><dt>评测</dt></dl></li>
                  <li :class="{'active': active[3]}" @click="handleClick4"><dl><dd class="bg4"></dd><dt>资讯</dt></dl></li>
                  <li :class="{'active': active[4]}" @click="handleClick5"><dl><dd class="bg5"></dd><dt>活动</dt></dl></li>
                  <li :class="{'active': active[5]}" @click="handleClick6"><dl><dd class="bg6"></dd><dt>视频</dt></dl></li>-->

                  <li :class="{'active': active[0]}"><dl><dd class="bg1"></dd><dt>公告</dt></dl></li>
                  <li :class="{'active': active[1]}"><dl><dd class="bg2"></dd><dt>问答</dt></dl></li>
                  <li :class="{'active': active[2]}"><dl><dd class="bg3"></dd><dt>评测</dt></dl></li>
                  <li :class="{'active': active[3]}"><dl><dd class="bg4"></dd><dt>资讯</dt></dl></li>
                  <li :class="{'active': active[4]}"><dl><dd class="bg5"></dd><dt>活动</dt></dl></li>
                  <li :class="{'active': active[5]}"><dl><dd class="bg6"></dd><dt>视频</dt></dl></li>
                </ul>
              </div>
            </div>
          </div>
          <ul class="news-lest">
            <SingleNews v-for="(item, index) in showArr" :key="index" :item="item"></SingleNews>
          </ul>
          <div class="loading-bar">
            <template v-if="hasMore && loadingStatus === false">
              <div class="load-more" @click="handleLoadMore"></div>
            </template>
            <template v-if="hasMore && loadingStatus">
              <div class="loading"></div>
            </template>
          </div>
        </div>
      </div>
      <ToTop v-if="toTop" :dom="dom"></ToTop>
      <Footer></Footer>
    </div>
  </div>
</template>
<script>
import SingleNews from '@/components/singleNews'
import Footer from '@/components/footer'
import axios from '@/util/request'
import ToTop from '@/components/toTop/other.vue'
import Swiper from '@/components/swiper'
export default {
  components: { SingleNews, Footer, ToTop, Swiper },
  data() {
    return {
      loadingStatus: false,
      hasMore: true,
      pageNo: 1,
      type: '',
      showArr: [],
      carouselArr: [],
      active: [false, false, false, false, false, false],
      toTop: false,
      dom: ''
    }
  },
  created() {
    this.initData()
  },
  mounted() {
    this.dom = document.getElementById('scrollNews')
    this.$refs.scrollNews.addEventListener('scroll', this.watchScroll)
  },
  methods: {
    watchScroll() {
      let top = this.$refs.scrollNews.scrollTop
      if (top >= 600) {
        this.toTop = true
      } else {
        this.toTop = false
      }
    },
    handleClick1() {
      this.pageNo = 1
      this.type = 1
      this.active = [true, false, false, false, false, false]
      this.initData()
    },
    handleClick2() {
      this.pageNo = 1
      this.type = 2
      this.active = [false, true, false, false, false, false]
      this.initData()
    },
    handleClick3() {
      this.pageNo = 1
      this.type = 3
      this.active = [false, false, true, false, false, false]
      this.initData()
    },
    handleClick4() {
      this.pageNo = 1
      this.type = 4
      this.active = [false, false, false, true, false, false]
      this.initData()
    },
    handleClick5() {
      this.pageNo = 1
      this.type = 5
      this.active = [false, false, false, false, true, false]
      this.initData()
    },
    handleClick6() {
      this.pageNo = 1
      this.type = 6
      this.active = [false, false, false, false, false, true]
      this.initData()
    },
    // 读取资讯
    initData() {
      this.loadingStatus = true
      let data = {
        type: this.type,
        pageNo: this.pageNo,
        pageSize: 10,
      }
      axios({ url: '/portal/front/news/getPageList', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            if (data.pageNo === 1) {
              this.showArr = data.items
              if (this.carouselArr.length === 0) this.carouselArr = this.showArr.slice(0, 4)
              // 判断是不是只有一页
              if (data.totalCount > data.pageSize) {
                this.hasMore = true
              } else {
                this.hasMore = false
              }
            } else {
              this.showArr = this.showArr.concat(data.items)
              // 判断是不是最后一页
              if (this.pageNo < data.totalCount / data.pageSize) {
                this.hasMore = true
              } else {
                this.hasMore = false
              }
            }
            this.loadingStatus = false
          }
        })
    },
    handleLoadMore() {
      this.pageNo++
      this.initData()
    }
  }
}
</script>
<style lang="less" scoped>
  .news-box {
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
      .title-bar {
        font-size: 20px;
        color: #444;
        text-align: left;
        position: relative;
        height: 70px;
        line-height: 70px;
      }
      .news-type {
        display: inline-block;
        position: absolute;
        top: 0;
        right: 0;
        width: 290px;
        height: 305px;
      }
      .type-lest {
        width: 100%;
        height: 235px;
        background: #fafafa;
        li {
          width: 50px;
          height: 80px;
          float: left;
          margin-top: 30px;
          &:nth-child(3n-2) {
            margin-left: 24px;
            margin-right: 47px;
          }
          &:nth-child(3n-1) {
            margin-right: 46px;
          }
          &:nth-child(3n) {
            margin-right: 23px;
          }
          &:hover {
            cursor: pointer;
          }
        }
        dd {
          width: 50px;
          height: 50px;
        }
        dt {
          width: 50px;
          height: 30px;
          font-size: 16px;
          line-height: 30px;
          text-align: center;
          color: #222;
        }
        .bg1 {background: url("../assets/news/news_announcement.png");}
        .bg2 {background: url("../assets/news/news_answer.png");}
        .bg3 {background: url("../assets/news/news_test.png");}
        .bg4 {background: url("../assets/news/news_news.png");}
        .bg5 {background: url("../assets/news/news_activity.png");}
        .bg6 {background: url("../assets/news/news_video.png");}
        & li.active dt{
          color: #3296FA;
        }
      }

      .news-lest {
        width: 897px;
        min-height: 1030px;
        li {
          width: 897px;
          height: 380px;
        }
      }
    }
    .loading-bar {
      width: 100%;
      height: 230px;
      text-align: center;
      & > div {
        display: inline-block;
        width: 140px;
        height: 60px;
        margin-top: 70px;
        &:hover {
          cursor: pointer;
        }
      }
      .load-more {
        background: url("../assets/case/icon_load.png")
      }
      .loading {
        background: url("../assets/case/icon_loading.png")
      }
    }
  }
  .el-carousel {
    width: 1200px;
    height: 400px;
    position: relative;
    left: 0;
    top: 0;
    a {
      display: block;
      width: 100%;
      height: 100%;
    }
  }
  .el-carousel__item h3 {
    display: inline-block;
    width: 1200px;
    height: 60px;
    position: absolute;
    left: 0;
    bottom: 0;
    text-align: left;
    color: #fff;
    line-height: 60px;
    font-size: 20px;
    padding-left: 40px;
    background: rgba(0, 0, 0, .3);
  }

  .el-carousel__item img {
    width: 100%;
    height: 100%;
  }
</style>
