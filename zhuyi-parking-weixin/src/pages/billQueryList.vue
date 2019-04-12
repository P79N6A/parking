<template>
  <div class="bill-query-list-box" ref="orderDOM">
    <transition name="fade">
      <loadingImg v-if="loading"></loadingImg>
    </transition>
    <ul class="order-list" v-if="orderList.length">
      <li v-for="(item, idx) in orderList" :key="idx">
        <singleBill :bill="item"></singleBill>
      </li>
      <div class="last-page" v-if="queryTpl.pageNo === totalPages">我也是有底线的</div>
    </ul>
    <div class="order-list-null" v-else></div>
  </div>
</template>
<script>
import {billQueryList} from '@/utils/api'
import singleBill from '@/components/singleBill'
import msg from '@/utils/notification'
import loadingImg from '@/components/loading'
export default {
  components: {singleBill, loadingImg},
  data () {
    return {
      loading: true,
      title: '账单列表',
      totalPages: 0, // 总页数
      queryTpl: {
        plateColor: sessionStorage.getItem('queryPlateColor') * 1,
        plateNumber: '',
        payStatus: 1, // 1:未支付
        pageNo: 1,
        pageSize: 6
      },
      orderList: []
    }
  },
  created () {
    document.title = this.title
    this.getData()
  },
  methods: {
    getData () {
      this.queryTpl.plateNumber = this.$store.getters.plateNumber
      billQueryList(this.queryTpl).then(res => {
        if (res.data.code === 1) {
          this.loading = false
          this.orderList = res.data.items
          this.totalPages = Math.ceil(res.data.totalCount / this.queryTpl.pageSize)
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    handleLoadMore () {
      let rootUnit = window.innerWidth / 3.75
      let top = this.$refs.orderDOM.scrollTop
      let prevLoad = ((this.queryTpl.pageNo - 1) * 1.9 * this.queryTpl.pageSize + 1.9) * rootUnit
      if (top >= prevLoad && this.queryTpl.pageNo < this.totalPages) {
        this.queryTpl.pageNo++
        billQueryList(this.queryTpl).then(res => {
          if (res.data.code === 1) {
            this.orderList = this.orderList.concat(res.data.items)
          } else {
            msg('', '', res.data.message)
          }
        })
      }
    }
  },
  mounted () {
    this.$refs.orderDOM.addEventListener('scroll', this.handleLoadMore)
  }
}
</script>
<style scoped lang="less">
.bill-query-list-box {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: #fafafa;
  overflow-y: auto;
  .order-list-null {
    position: absolute;
    top: 0;
    width: 100%;
    &::before {
      content: '';
      display: block;
      background: url("../assets/userBillList/icon_none.png") no-repeat;
      background-size: 100% 100%;
      margin: .55rem 1.48rem .2rem;
      width: .8rem;
      height: .8rem;
    }
    &::after {
      content: '此车牌暂无未支付账单';
      display: block;
      width: 100%;
      height: .36rem;
      line-height: .36rem;
      font-size: .18rem;
    }
  }
  .order-list {
    position: absolute;
    top: 0;
    overflow-y: auto;
    li {
      &::before {
        content: '';
        display: table;
      }
    }
  }
  .last-page {
    width: 100%;
    height: .38rem;
    line-height: .38rem;
    font-size: .14rem;
    color: #b4b4b4;
    position: relative;
    &::before {
      content: '';
      display: block;
      position: absolute;
      top: .18rem;
      left: .1rem;
      width: 1.05rem;
      height: .01rem;
      background: #b4b4b4;
    }
    &::after {
      content: '';
      display: block;
      position: absolute;
      top: .18rem;
      left: 2.6rem;
      width: 1.05rem;
      height: .01rem;
      background: #b4b4b4;
    }
  }
}
</style>
