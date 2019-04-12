import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
Vue.use(Router)

const download = () => import('../page/download.vue')
export default new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    // {
    //   path: '/',
    //   name: 'home',
    //   component: res => require(['@/page/v1/home'], res)
    // },
    {
      path: '/',
      name: 'index',
      component: res => require(['@/page/index'], res)
    },
    // {
    //   path: '/product',
    //   name: 'product',
    //   component: res => require(['@/page/v1/product'], res)
    // },
    {
      path: '/project',
      name: 'project',
      component: res => require(['@/page/project'], res)
    },
    {
      path: '/app',
      name: 'app',
      component: res => require(['@/page/app'], res)
    },
    {
      path: '/case',
      name: 'case',
      component: res => require(['@/page/case'], res)
    },
    {
      path: '/cooperation',
      name: 'cooperation',
      component: res => require(['@/page/cooperation'], res)
    },
    {
      path: '/aboutUs',
      name: 'aboutUs',
      component: res => require(['@/page/aboutUs'], res)
    },
    // {
    //   path: '/joinUs',
    //   name: 'joinUs',
    //   component: res => require(['@/page/v1/joinUs'], res)
    // },
    {
      path: '/news',
      name: 'news',
      component: res => require(['@/page/news'], res)
    },
    {
      path: '/information/:id',
      name: 'information',
      component: res => require(['@/page/information'], res)
    },
    {
      path: '*',
      name: 'notFound',
      component: res => require(['@/page/notFound'], res)
    },
    {
      path: '/notFound',
      name: 'notFound',
      component: res => require(['@/page/notFound'], res)
    },
    { path: '/download', name: 'download', component: download },
  ]
})
