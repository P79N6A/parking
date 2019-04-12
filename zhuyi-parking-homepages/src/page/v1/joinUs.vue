<template>
  <div class="joinUs-page">
    <div class="banner">
      <img src="../../assets/joinUs/join.png">
    </div>
    <div class="jobs fx">
      <Job v-for="(item, index) in showArr" :key="index" :item="item"></Job>
    </div>
  </div>
</template>
<script>
import Job from '@/components/singleJob'
import axios from '@/util/request'
export default {
  components: { Job },
  data() {
    return {
      showArr: []
    }
  },
  created() {
    this.initData()
  },
  methods: {
    initData() {
      let data = {
        pageSize: 40,
        pageNo: 1
      }
      axios({ url: '/portal/front/employ/getPageList', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            this.showArr = data.items
          }
        })
    }
  }
}
</script>
<style lang="less" scoped>
  .joinUs-page {
    .banner {
      width: 1920px;
      height: 500px;
      img {
        width: 100%;
        height: 500px;
      }
    }
    .jobs {
      width: 100%;
      min-height: 800px;
      padding-bottom: 120px;
      background: #fff;
      font-size: 32px;
      color: #fff;
      & > div {
        float: left;
      }
      & > div:nth-child(2n-1) {
        margin: 17px 40px 17px 358px;
      }
      & > div:nth-child(2n) {
        margin: 17px 360px 0 0;
      }
    }
  }
</style>
