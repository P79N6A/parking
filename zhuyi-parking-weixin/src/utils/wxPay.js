import axios from '@/utils/request'
export function onBridgeReady (obj) {
  WeixinJSBridge.invoke(
    'getBrandWCPayRequest', {
      'appId': obj.appId, // 公众号名称，由商户传入
      'timeStamp': obj.timeStamp, // 时间戳，自1970年以来的秒数
      'nonceStr': obj.nonceStr, // 随机串
      'package': 'prepay_id=' + obj.prepayId,
      'signType': 'MD5', // 微信签名方式：
      'paySign': obj.paySign, // 微信签名
      jsApiList: ['checkJsApi', 'startRecord', 'stopRecord', 'translateVoice', 'scanQRCode', 'openCard']
    },
    function (r) {
      /** if (r.err_msg === 'get_brand_wcpay_request:ok') {
        使用以上方式判断前端返回,微信团队郑重提示：
        r.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
      } */
      let tpl = {
        orderNo: obj.orderNo,
        payWay: 5,
        payAmount: obj.paymentAmount
      }
      axios({url: '/platform/trade/orderpayconfirm', method: 'post', params: tpl}).then(res => {
        if (res.data.code === 1 && res.data.data.succeed) {
          // 支付成功
          history.go(-1)
        } else {
          alert('支付失败！')
        }
      })
    })
}
