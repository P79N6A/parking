import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  mode: 'history',
  fallback: false,
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/mp/', // 个人中心
      name: 'home',
      component: res => require(['@/pages/index'], res)
    },
    {
      path: '/',
      redirect: '/mp/'
    },
    {
      path: '/scanIn/:id', // 扫码前置页
      name: 'scanIn',
      component: res => require(['@/pages/scanIn'], res)
    },
    {
      path: '/mp/userPhone', // 手机绑定
      name: 'userPhone',
      component: res => require(['@/pages/userPhone'], res)
    },
    {
      path: '/mp/userBill', // 账单
      name: 'userBill',
      component: res => require(['@/pages/userBill'], res)
    },
    {
      path: '/mp/userCar', // 车辆绑定
      name: 'userCar',
      component: res => require(['@/pages/userCar'], res)
    },
    {
      path: '/mp/billDetails/:id', // 账单详情
      name: 'billDetails',
      component: res => require(['@/pages/billDetails'], res)
    },
    {
      path: '/mp/billQuery', // 账单快速查询
      name: 'billQuery',
      component: res => require(['@/pages/billQuery'], res)
    },
    {
      path: '/mp/billQueryList', // 账单快速查询列表
      name: 'billQueryList',
      component: res => require(['@/pages/billQueryList'], res)
    },
    {
      path: '/mp/scanQuery', // 账单扫码快速查询
      name: 'scanQuery',
      component: res => require(['@/pages/scanToPay'], res)
    },
    {
      path: '/mp/scanQueryList', // 账单扫码查询列表
      name: 'scanQueryList',
      component: res => require(['@/pages/scanQueryList'], res)
    },
    {
      path: '/mp/pay', // 微信预支付,   ===!!===  /mp/pay 为固定值，不要改  ===!!===
      name: 'prePay',
      component: res => require(['@/pages/prePay'], res)
    },
    {
      path: '/mp/userProtocol', // 用户协议
      name: 'userProtocol',
      component: res => require(['@/pages/userProtocol'], res)
    },
    {
      path: '/mp/adCooperation', // 广告合作
      name: 'adCooperation',
      component: res => require(['@/pages/staticPages/ad-partners'], res)
    },
    {
      path: '/mp/parkingCooperation', // 合作招商-停车场
      name: 'parkingCooperation',
      component: res => require(['@/pages/staticPages/parking-partners'], res)
    },
    {
      path: '/mp/personalCooperation', // 市场合作-共享车位
      name: 'personalCooperation',
      component: res => require(['@/pages/staticPages/personal-partners'], res)
    },
    // {
    //   path: '*',
    //   name: 'notFound',
    //   component: res => require(['@/pages/notFound'], res)
    // },
    {
      path: '/mp/notFound',
      name: 'notFound',
      component: res => require(['@/pages/notFound'], res)
    }
  ]
})
