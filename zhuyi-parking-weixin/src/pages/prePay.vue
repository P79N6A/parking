<template>
  <div class="per-pay">
    <div class="payment-amount">{{paymentAmount}}</div>
    <div class="text">费用合计 （元）</div>
    <div class="btn" @click="toPay">确认支付</div>
  </div>
</template>
<script>
import {onBridgeReady} from '@/utils/wxPay'
export default {
  data () {
    return {
      paymentAmount: '' || this.$store.getters.prePay.paymentAmount
    }
  },
  methods: {
    toPay () {
      let tpl = this.$store.getters.prePay
      if (this.paymentAmount) {
        onBridgeReady(tpl)
      }
    }
  }
}
</script>
<style scoped lang="less">
@defaultBule: #3296FA;
.per-pay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fff;
  .payment-amount {
    height: .48rem;
    line-height: .48rem;
    margin-top: .56rem;
    font-size: .6rem;
    color: @defaultBule;
  }
  .text {
    height: .36rem;
    line-height: .36rem;
    margin-top: .08rem;
  }
  .btn {
    width: 3.4rem;
    height: .5rem;
    line-height: .5rem;
    margin-top: .65rem;
    background: @defaultBule;
    color: #fff;
    display: inline-block;
    border-radius: .25rem;
  }
}
</style>
