// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueLazyload from 'vue-lazyload'
import axios from 'axios'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import vueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'
Vue.use(vueAwesomeSwiper)

Vue.config.productionTip = false
const lazyPath = require('./assets/lazy-load.png')
Vue.use(Element)
Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: lazyPath,
  loading: lazyPath,
  attempt: 1
})

// axios.defaults.baseURL = baseUrl // 生产环境
axios.defaults.headers = {
  'Acces-Control-Allow-Origin': '*',
  'Content-Type': 'application/json'
}
// axios.defaults.data = {
//   clientId: 'xxx',
//   secret: '13c5b701c1ef49c0b032ce41b9bec5c2',
//   uuid: 'web'
// }
axios.interceptors.response.use(res => {
  console.log(res.data)
  return res.data
}, err => {
  return Promise.reject(err)
})
Vue.prototype.axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
