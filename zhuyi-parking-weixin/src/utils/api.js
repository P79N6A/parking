import axios from '@/utils/request'
// 获取省份简称列表
export function getPrefixList () {
  return axios({url: '/platform/mycar/getPrefixList', method: 'post'})
}
// 获取省份车牌首字母
export function getPrefixLetter (data) {
  return axios({url: '/platform/mycar/getPrefixLetter', method: 'post', data})
}
// 获取车牌颜色
export function getPlateColor () {
  return axios({url: '/platform/mycar/userPlateColor', method: 'post'})
}
// 登入
export function login (data) {
  return axios({url: '/v2/platform/security/login', method: 'post', data})
}
// 微信注册
export function wechatRegister (data) {
  return axios({url: '/platform/security/jsapiRegister', method: 'post', data})
}
// 获取微信公众号appid
export function wechatAppId () {
  return axios({url: '/platform/trade/jsapiconfig', method: 'post'})
}
// 账单列表
export function billQueryList (data) {
  return axios({url: '/platform/order/listpage', method: 'post', params: data})
}
// 账单快速查询列表
export function scanQueryList (data) {
  return axios({url: '/platform/jsapi/listpage', method: 'post', params: data})
}
// 支付1-停车账单支付
export function payment (data) {
  return axios({url: '/platform/trade/orderpayment', method: 'post', data})
}
// 时间格式转换
export function parseTime (time, cFormat) {
  if (arguments.length === 0) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (('' + time).length === 10) time = parseInt(time) * 1000
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const timeStr = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return timeStr
}
