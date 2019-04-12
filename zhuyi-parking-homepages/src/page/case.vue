<template>
  <div class="case-page">
    <div class="banner">
      <img src="../assets/case/banner_case.png" alt="">
    </div>
    <div class="content">
      <ul class="type-bar">
        <li style="margin-left: 360px;" :class="{'active': currentType[0]}" @click="handleChangeType0">商业综合体</li>
        <li :class="{'active': currentType[1]}" @click="handleChangeType1">商业写字楼</li>
        <li :class="{'active': currentType[2]}" @click="handleChangeType2">大型住宅区</li>
        <li :class="{'active': currentType[3]}" @click="handleChangeType3">学校及医院</li>
        <li :class="{'active': currentType[4]}" @click="handleChangeType4">旅游、酒店及单位</li>
      </ul>
      <div class="case-list">
        <SinglePark v-for="(item, index) in showArr" :key="index" :item="item"></SinglePark>
      </div>
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
</template>
<script>
import SinglePark from '@/components/singleParking'
import axios from '@/util/request'
export default {
  components: { SinglePark },
  data() {
    return {
      currentType: [true, false, false, false, false],
      loadingStatus: false,
      hasMore: true,
      pageNo: 1,
      type: 1,
      showArr: []
    }
  },
  created() {
    this.handleChangeType0()
  },
  methods: {
    // -案例- 类型切换
    handleChangeType0() {
      this.pageNo = 1
      this.type = 1
      this.initData()
      this.currentType = [true, false, false, false, false]
    },
    handleChangeType1() {
      this.pageNo = 1
      this.type = 2
      this.initData()
      this.currentType = [false, true, false, false, false]
    },
    handleChangeType2() {
      this.pageNo = 1
      this.type = 3
      this.initData()
      this.currentType = [false, false, true, false, false]
    },
    handleChangeType3() {
      this.pageNo = 1
      this.type = 4
      this.initData()
      this.currentType = [false, false, false, true, false]
    },
    handleChangeType4() {
      this.pageNo = 1
      this.type = 5
      this.initData()
      this.currentType = [false, false, false, false, true]
    },
    // 读取案例
    initData() {
      this.loadingStatus = true
      let data = {
        type: this.type,
        pageSize: 9,
        pageNo: this.pageNo
      }
      axios({ url: '/portal/front/parkcase/getPageList', method: 'post', data })
        .then(res => {
          let data = res.data
          if (data.code === 1) {
            if (data.pageNo === 1) {
              this.showArr = data.items
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
  .case-page {
    min-height: 800px;
    .banner {
      width: 100%;
      height: 500px;
      img {
        width: 100%;
      }
    }
    .content {
      background: #fff;
      min-height: 1200px;
      ul {
        width: 100%;
        height: 180px;
        li {
          float: left;
          height: 60px;
          width: 220px;
          line-height: 60px;
          cursor: pointer;
          background: #fafafa;
          border-radius: 30px;
          color: #B4B4B4;
          font-size: 20px;
          margin-top: 60px;
        }
        li:not(:last-child) {
          margin-right: 25px;
        }
        li:hover, li.active {
          background: #3296FA;
          color: #fff;
        }
      }
      .case-list {
        width: 1200px;
        display: inline-block;
        text-align: left;
        & > div:nth-child(3n -1) {
          margin: 0 30px 30px;
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
  }
</style>
