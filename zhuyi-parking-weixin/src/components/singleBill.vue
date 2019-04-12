<template>
  <div class="single-bill" :class="{'finished': bill.payStatus === 3 && bill.status === 2 && bill.payStatus}" @click="goToDetails(bill.orderNo)">
    <div class="parking-name">{{bill.parkingName}}</div>
    <div class="parking-status" v-if="bill.status === 1">停车中</div>
    <div class="total-price fx">
      <span class="price fl">{{bill.payableAmount}}</span>
      <span class="units fl">（元）</span>
    </div>
    <div class="start-stop-time">
      <span class="start-time">{{bill.startTime}}</span>
      <span> 至 </span>
      <span class="stop-time">{{formetTime}}</span>
    </div>
    <div class="parking-time">
      <div class="left fl"><span>停车时长：</span><span :class="{'yellow': !bill.endTime}">{{bill.parkingLength}}</span></div>
      <template v-if="bill.payStatus !== 3">
        <div class="right pay" :class="{'can-not-pay': !bill.payable}" @click.stop="goToPay(bill.orderNo)">立即支付</div>
      </template>
      <template v-else-if="bill.status === 1"><div class="right paied">已支付</div></template>
      <template v-else-if="bill.status === 2"><div class="right finished">已完成</div></template>
    </div>
  </div>
</template>
<script>
import {parseTime, payment} from '@/utils/api'
import msg from '@/utils/notification'
export default {
  name: 'singleBill',
  props: ['bill', 'isQuick'],
  created () {
  },
  methods: {
    goToDetails (id) {
      if (this.$props.isQuick) {
        this.goToPay(id)
      } else {
        /** 账单详情 路由 */
        this.$router.push({name: 'billDetails', params: {id: id}})
      }
    },
    goToPay (id) {
      if (!this.bill.payable) {
        return false
      }
      let tpl = {
        orderNo: id,
        payWay: 2,
        paymentAmount: this.bill.payableAmount,
        openId: this.$props.isQuick ? localStorage.getItem('userId') : this.$store.getters.userInfo.openId,
        payType: 'JSAPI'
      }
      payment(tpl).then(res => {
        if (res.data.code === 1 && res.data.data) {
          this.$store.dispatch('GetPerPayInfo', res.data.data)
          /** 账单支付 路由 */
          this.$router.push({name: 'prePay'})
        } else {
          msg('', '', res.data.message)
        }
      })
    }
  },
  computed: {
    formetTime () {
      if (this.bill.endTime) {
        return this.bill.endTime
      } else {
        return '今'
        // return parseTime(+(new Date()), '{y}-{m}-{d} {h}:{i}')
      }
    }
  }
}
</script>
<style scoped lang="less">
.single-bill {
  width: 3.55rem;
  height: 1.8rem;
  background: url("../assets/userBillList/billbg1.png") no-repeat;
  background-size: 100% 100%;
  position: relative;
  margin: .1rem .1rem 0;
  border-radius: .1rem;
  text-align: left;
  box-shadow: .01rem .04rem .06rem rgba(55, 119, 215, .15),-.01rem -.04rem .06rem rgba(55, 119, 215, .15);
  &.finished {
    background: url("../assets/userBillList/billbg2.png") no-repeat;
    background-size: 100% 100%;
  }
  .parking-name {
    height: .4rem;
    line-height: .4rem;
    width: 2.3rem;
    position: absolute;
    left: .36rem;
    font-size: .14rem;
    color: rgba(255, 255, 255, .6);
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .parking-status {
    width: .62rem;
    height: .4rem;
    line-height: .4rem;
    position: absolute;
    right: .16rem;
    text-align: right;
    color: rgba(255, 255, 255, .6);
    &::before {
      content: '';
      display: block;
      width: .15rem;
      height: .15rem;
      position: absolute;
      top: .13rem;
      background: url("../assets/userBillList/icon_parking.png") no-repeat;
      background-size: 100% 100%;
    }
  }
  .total-price {
    width: 3.25rem;
    height: .5rem;
    position: absolute;
    left: .13rem;
    top: .49rem;
    span {
      height: 100%;
      line-height: .5rem;
      color: #fff;
    }
    .price {
      font-size: .4rem;
    }
    .units {
      line-height: .7rem;
      letter-spacing: .03rem;
    }
  }
  .start-stop-time {
    width: 3.25rem;
    height: .2rem;
    position: absolute;
    top: .99rem;
    left: .13rem;
    color: rgba(255, 255, 255, .6);
  }
  .parking-time {
    width: 100%;
    height: .5rem;
    line-height: .5rem;
    color: #b4b4b4;
    font-size: .14rem;
    position: absolute;
    bottom: 0;
    .left {
      height: 100%;
      line-height: .5rem;
      margin-left: .15rem;
    }
    .right {
      width: .8rem;
      height: .3rem;
      position: absolute;
      right: .15rem;
      top: .1rem;
      line-height: .3rem;
      text-align: center;
      z-index: 5;
    }
    .pay {
      background: #3296FA;
      color: #fff;
      border-radius: .15rem;
    }
    .can-not-pay {
      background: #CCE6FF;
    }
    .paied {
      text-align: right;
      color: #3296FA;
    }
    .finished {
      text-align: right;
      color: #2DBB55;
    }
  }
  .yellow {
    color: #FAC828;
  }
}
</style>
