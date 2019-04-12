// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import VueLazyload from 'vue-lazyload'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './utils/np'
import vueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'

Vue.use(vueAwesomeSwiper)

const lazyPath = require('./assets/lazy-load.png')
Vue.use(Element)
Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: lazyPath,
  loading: lazyPath,
  attempt: 1
})
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
