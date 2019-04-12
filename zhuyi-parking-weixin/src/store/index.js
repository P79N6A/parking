import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import billQuery from './modules/billQuery'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
    billQuery
  },
  getters
})

export default store
