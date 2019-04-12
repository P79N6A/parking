<template>
  <div class="user-bill-box" ref="orderDOM">
    <transition name="fade">
      <loadingImg v-if="loading"></loadingImg>
    </transition>
    <div class="choosed-pleat">
      <div class="choosed" @click="choosedPlateShow = !choosedPlateShow">
        {{plateList[choosedPlate].fullPlateNumber}}
      </div>
      <ul class="pleat-list" v-if="choosedPlateShow">
        <li v-for="(item, idx) in plateList" :key="idx"
            @click="getPleatTypeList(idx)"
            :class="{'active': choosedPlate === idx}"
        >
          {{item.fullPlateNumber}}
        </li>
      </ul>
    </div>
    <ul class="order-type">
      <li :class="{'active': queryTpl.payStatus === 0}" @click="getFirstPage(0)">全部订单</li>
      <li :class="{'active': queryTpl.payStatus === 1}" @click="getFirstPage(1)">未支付</li>
      <li :class="{'active': queryTpl.payStatus === 2}" @click="getFirstPage(2)">已支付</li>
    </ul>
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
import axios from '@/utils/request'
import {billQueryList} from '@/utils/api'
import singleBill from '@/components/singleBill'
import loadingImg from '@/components/loading'
import msg from '@/utils/notification'
export default {
  components: {singleBill, loadingImg},
  data () {
    return {
      loading: true,
      title: '用户账单',
      plateList: [{fullPlateNumber: ''}], // 绑定的车牌
      orderList: [], // 绑定的车牌的账单
      choosedPlate: 0 || sessionStorage.getItem('choosedPlateIndex') * 1, // 车牌号切换
      totalPages: 0, // 总页数
      queryTpl: {
        payStatus: 0, // 支付状态切换
        pageNo: 1,
        pageSize: 6
      },
      choosedPlateShow: false // 车牌号切换面板隐藏
    }
  },
  created () {
    document.title = this.title
    this.getplateList()
  },
  methods: {
    getplateList () {
      axios({url: '/platform/mycar/userPlateNumberList', method: 'post'}).then(res => {
        if (res.data.code === 1) {
          this.plateList = res.data.items
          this.getData()
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    getFirstPage (n) {
      this.queryTpl.payStatus = n
      this.totalPages = 0
      this.queryTpl.pageNo = 1
      this.$refs.orderDOM.scrollTop = 0
      this.loading = true
      this.getData()
    },
    getPleatTypeList (n) {
      this.choosedPlateShow = false
      this.choosedPlate = n
      sessionStorage.setItem('choosedPlateIndex', n)
      this.totalPages = 0
      this.queryTpl.pageNo = 1
      this.$refs.orderDOM.scrollTop = 0
      this.getData()
    },
    getData () {
      let tpl = this.queryTpl
      Object.assign(tpl, {
        plateNumber: this.plateList[this.choosedPlate].fullPlateNumber,
        plateColor: this.plateList[this.choosedPlate].plateColor
      })
      billQueryList(tpl).then(res => {
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
        let tpl = this.queryTpl
        Object.assign(tpl, {plateNumber: this.plateList[this.choosedPlate].fullPlateNumber})
        billQueryList(tpl).then(res => {
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
@defaultBule: #3296FA;
.user-bill-box {
  color: #222;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fff;
  overflow-y: auto;
  overflow-x: hidden;
  .choosed-pleat {
    background: #fafafa;
    height: .4rem;
    width: 100%;
    position: fixed;
    z-index: 50;
    font-size: .18rem;
    &::after {
      content: '';
      position: absolute;
      width: .11rem;
      height: .11rem;
      background: url("../assets/userBillList/icon_arrowgrey.png") no-repeat;
      background-size: 100% 100%;
      top: .15rem;
      right: 1.27rem;
    }
    .choosed {
      height: .4rem;
      line-height: .4rem;
    }
  }
  .pleat-list {
    position: absolute;
    top: .4rem;
    width: 100%;
    background: #fff;
    padding: .08rem;
    box-sizing: border-box;
    z-index: 1;
    box-shadow: .01rem .04rem .06rem rgba(55, 119, 215, .15),-.01rem -.04rem .06rem rgba(55, 119, 215, .15);
    li {
      height: .48rem;
      line-height: .48rem;
      border-bottom: 1px solid #eee;
      font-size: .18rem;
      color: #888;
    }
    li.active {
      color: @defaultBule;
    }
    li:last-child {
      border-bottom: none;
    }
  }
  .order-type {
    width: 100%;
    height: .4rem;
    border-bottom: 1px solid #eee;
    position: fixed;
    background: #fff;
    z-index: 40;
    top: .4rem;
    li {
      float: left;
      width: 1.25rem;
      height: .4rem;
      line-height: .4rem;
      position: relative;
      &:after {
        content: '';
        display: block;
        width: .6rem;
        height: .02rem;
        background: transparent;
        position: absolute;
        bottom: 0;
        left: .34rem;
      }
    }
    li.active {
      color: @defaultBule;
      &::after {
        background: @defaultBule;
      }
    }
  }
  .order-list-null {
    position: absolute;
    top: .8rem;
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
      content: '您暂无任何账单';
      display: block;
      width: 100%;
      height: .36rem;
      line-height: .36rem;
      font-size: .18rem;
    }
  }
  .order-list {
    position: absolute;
    top: .8rem;
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
