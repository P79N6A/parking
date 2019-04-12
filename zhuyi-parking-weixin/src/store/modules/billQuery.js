// import axios from '@/utils/request'
const billQuery = {
  state: {
    plateNumber: '',
    prePay: {},
    returnURL: ''
  },
  mutations: {
    query_plate_number: (state, plateNumber) => {
      state.plateNumber = plateNumber
    },
    prev_pay: (state, obj) => {
      state.prePay = obj
    },
    get_return_url: (state, url) => {
      state.returnURL = url
    }
  },
  actions: {
    // 用户登录
    QueryPlateNumber ({commit}, plateNumber) {
      commit('query_plate_number', plateNumber)
    },
    // 获取账单支付信息
    GetPerPayInfo ({commit}, obj) {
      commit('prev_pay', obj)
    },
    // 获取账单支付的返回页面: 用户账单列表 | 账单查询列表
    GetReturnURL ({commit}, url) {
      commit('get_return_url', url)
    }
  }
}

export default billQuery
