// import axios from '@/utils/request'
const user = {
  state: {
    userInfo: JSON.parse(sessionStorage.getItem('userInfo')) || {},
    appId: ''
  },
  mutations: {
    Save_UserInfo: (state, obj) => {
      state.userInfo = obj
      sessionStorage.userInfo = JSON.stringify(obj)
    },
    Set_appId: (state, appid) => {
      state.appId = appid
    }
  },
  actions: {
    // 用户登录
    saveUserInfo ({commit}, obj) {
      commit('Save_UserInfo', obj)
    },
    setAppId ({commit}, appid) {
      commit('Set_appId', appid)
    }
  }
}

export default user
