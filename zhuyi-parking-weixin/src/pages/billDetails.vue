<template>
  <div class="bill-details-box">
    <div class="pay-status-img" v-if="data.payStatus === 3"></div>
    <table class="main-box">
      <tr class="car">
        <td class="left">
          <div class="icon"></div>
        </td>
        <td class="right">
          {{data.plateNumber}}&nbsp;&nbsp;&nbsp;{{data.parkingTime}}
        </td>
      </tr>
      <tr class="park">
        <td class="left">
          <div class="icon"></div>
        </td>
        <td class="right">
          <p>{{data.parkingName}}</p>
          <p class="address">{{data.parkingAddress}}</p>
        </td>
      </tr>
      <tr class="time">
        <td class="left">
          <div class="icon"></div>
        </td>
        <td class="right">
          <p class="appoint-time" :class="{'only': onlyOneTime}" v-if="data.appointTime">预约时间&nbsp;&nbsp;&nbsp;{{data.appointTime}}</p>
          <p class="start-time" :class="{'only': onlyOneTime}" v-if="data.startTime">入场时间&nbsp;&nbsp;&nbsp;{{data.startTime}}</p>
          <p class="end-time" :class="{'only': onlyOneTime}" v-if="data.endTime">出场时间&nbsp;&nbsp;&nbsp;{{data.endTime}}</p>
        </td>
      </tr>
      <tr class="pay-time">
        <td class="left">
          <div class="icon"></div>
        </td>
        <td class="right">
          <p class="parking-length" :class="{'only': onlyOneLength}" v-if="data.parkingLength">
            停车时长&nbsp;&nbsp;&nbsp;
            <span :class="{'yellow': !data.endTime}">{{data.parkingLength}}</span>
          </p>
          <p class="free-length" :class="{'only': onlyOneLength}" v-if="data.freeLength">限免时长&nbsp;&nbsp;&nbsp;{{data.freeLength}}</p>
          <p class="charge-length" :class="{'only': onlyOneLength}" v-if="data.chargeLength">收费时长&nbsp;&nbsp;&nbsp;{{data.chargeLength}}</p>
        </td>
      </tr>
      <tr class="coast" v-if="data.payStatus === 3">
        <td class="left">
          <div class="icon"></div>
        </td>
        <td class="right">
          <template v-if="data.limitFree">
            <p class="appoint-fee" :class="{'only': onlyOneCoast}" v-if="data.appointFee">服务费用&nbsp;&nbsp;&nbsp;{{data.appointFee}}元</p>
            <p class="payable-amount" :class="{'only': onlyOneCoast}" v-if="data.actualPayAmount">实际支付&nbsp;&nbsp;&nbsp;{{data.actualPayAmount}}元</p>
          </template>
          <template v-else>
            <p class="appoint-fee" :class="{'only': onlyOneCoast}" v-if="data.appointFee">服务费用&nbsp;&nbsp;&nbsp;{{data.appointFee}}元</p>
            <p class="payable-amount" :class="{'only': onlyOneCoast}" v-if="data.payableAmount">停车费用&nbsp;&nbsp;&nbsp;{{data.payableAmount}}元</p>
          </template>
        </td>
      </tr>
    </table>
    <div class="charge-description" v-html="data.chargeDescription"></div>
    <Swiper :items="data.images" v-if="data.images.length > 1"></Swiper>
    <div class="img-view" v-if="data.images.length === 1"
         :style="'background: url(' + data.images[0].url + ') no-repeat;background-size: 100% 100%;'"
    >
    </div>
    <div class="pay-bar fx" v-if="data.payStatus !== 3">
      <div class="pay-price fl">
        <div class="p-l">费用合计（ 元 ）</div>
        <div class="p-r">{{data.payableAmount}}</div>
      </div>
      <div class="pay-btn fl" :class="{'can-not-pay': !data.payable}" @click.stop="goToPay(data.orderNo)">立即支付</div>
    </div>
  </div>
</template>
<script>
import Swiper from '@/components/swiper'
import axios from '@/utils/request'
import {payment} from '@/utils/api'
import msg from '@/utils/notification'
export default {
  components: { Swiper },
  data () {
    return {
      title: '账单详情',
      data: {}
    }
  },
  created () {
    document.title = this.title
    this.init()
  },
  methods: {
    init () {
      let tpl = {orderNo: this.$route.params.id}
      axios({url: '/platform/order/detail', method: 'post', params: tpl}).then(res => {
        if (res.data.code === 1) {
          this.data = res.data.data
        } else {
          msg('', '', res.data.message)
        }
      })
    },
    goToPay (id) {
      if (!this.data.payable) {
        return false
      }
      let tpl = {
        orderNo: id,
        payWay: 2,
        paymentAmount: this.data.payableAmount,
        openId: this.$store.getters.userInfo.openId,
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
    onlyOneTime () {
      let res = 0
      if (this.data.startTime) {
        res += 1
      }
      if (this.data.endTime) {
        res += 1
      }
      if (this.data.appointTime) {
        res += 1
      }
      if (res > 1) {
        return false
      } else {
        return true
      }
    },
    onlyOneLength () {
      let res = 0
      if (this.data.parkingLength) {
        res += 1
      }
      if (this.data.freeLength) {
        res += 1
      }
      if (this.data.chargeLength) {
        res += 1
      }
      if (res > 1) {
        return false
      } else {
        return true
      }
    },
    onlyOneCoast () {
      let res = 0
      if (this.data.appointFee) {
        res += 1
      }
      if (this.data.payableAmount) {
        res += 1
      }
      if (res > 1) {
        return false
      } else {
        return true
      }
    }
  }
}
</script>
<style scoped lang="less">
@defaultBule: #3296FA;
.bt {
  background: #fafafa;
}
.bill-details-box {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fafafa;
  overflow-y: auto;
  .pay-status-img {
    width: 100%;
    height: 1.1rem;
    background: #fff;
    &::before {
      content: '';
      display: block;
      position: absolute;
      width: .66rem;
      height: .66rem;
      top: .13rem;
      left: 1.55rem;
      background: url("../assets/billDetails/success.png") no-repeat;
      background-size: 100% 100%;
    }
    &::after {
      content: '账单已完成';
      display: block;
      position: absolute;
      width: 1rem;
      height: .18rem;
      top: .8rem;
      left: 1.38rem;
      font-size: .16rem;
      line-height: .18rem;
      color: #222;
    }
  }
  table, tr, td {
    border-spacing: 0;
    border: none;
    padding: 0;
  }
  table {
    padding-right: .15rem;
  }
  td {
    vertical-align: middle;
    min-height: .3rem;
  }
  .main-box {
    background: url("../assets/billDetails/icon_banner.png") no-repeat;
    background-size: 100% 100%;
    width: 100%;
    .left {
      width: .52rem;
      position: relative;
      .icon {
        position: absolute;
        top:50%;
        left: 50%;
        transform: translate(-50%,-50%);
      }
    }
    .right {
      width: 3.08rem;
      text-align: left;
      font-size: .14rem;
      color: #fff;
      min-height: .3rem;
      padding: .025rem 0;
    }
    .icon {
      display: inline-block;
      width: .25rem;
      height: .25rem;
    }
    .car {
      .icon {
        background: url("../assets/billDetails/icon_car.png") no-repeat;
        background-size: 100% 100%;
      }
      .right {
        height: .3rem;
      }
    }
    .pay-time {
      .icon {
        background: url("../assets/billDetails/icon_payTime.png") no-repeat;
        background-size: 100% 100%;
      }
      p {
        line-height: .2rem;
      }
      .only {
        height: .3rem;
        line-height: .3rem;
      }
    }
    .time {
      .icon {
        background: url("../assets/billDetails/icon_time.png") no-repeat;
        background-size: 100% 100%;
      }
      p {
        line-height: .2rem;
      }
      .only {
        height: .3rem;
        line-height: .3rem;
      }
    }
    .coast {
      .icon {
        background: url("../assets/billDetails/icon_coast.png") no-repeat;
        background-size: 100% 100%;
      }
      p {
        line-height: .2rem;
      }
      .only {
        height: .3rem;
        line-height: .3rem;
      }
    }
    .park {
      .icon {
        background: url("../assets/billDetails/icon_park.png") no-repeat;
        background-size: 100% 100%;
      }
      p {
        line-height: .2rem;
      }
      .address {
        font-size: .12rem;
      }
    }
    .yellow {
      color: #FAC828;
    }
  }
  .charge-description {
    margin: .18rem 0;
    display: inline-block;
    width: 3.4rem;
    text-align: left;
  }
  .img-view {
    width: 3.45rem;
    height: 1.4rem;
    display: inline-block;
    background-size: 100% 100%;
  }
  .pay-bar {
    position: fixed;
    bottom: 0;
    z-index: 10;
    width: 100%;
    height: .55rem;
    .pay-price {
      width: 2.25rem;
      height: .55rem;
      display: inline-block;
      background: #fff;
      div {
        display: inline-block;
        position: absolute;
      }
      .p-l {
        width: 1.28rem;
        height: .14rem;
        line-height: .14rem;
        bottom: .18rem;
        left: .01rem;
        font-size: .12rem;
      }
      .p-r {
        width: .97rem;
        height: .23rem;
        line-height: .23rem;
        bottom: .16rem;
        left: 1.29rem;
        font-size: .23rem;
        text-align: left;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
    .pay-btn {
      width: 1.5rem;
      height: .55rem;
      line-height: .55rem;
      display: inline-block;
      background: @defaultBule;
      color: #fff;
    }
    .can-not-pay {
      background: #CCE6FF;
    }
  }
}
</style>
