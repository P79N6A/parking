import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  // { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/dashboard',
  //   name: 'Dashboard',
  //   hidden: true,
  //   children: [{
  //     path: 'dashboard',
  //     component: () => import('@/views/dashboard/index')
  //   }]
  // },
  {
    path: '',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/news',
    component: Layout,
    // redirect: '/news',
    // name: 'News',
    children: [
      {
        path: 'index',
        name: 'News',
        component: () => import('@/views/news/index'),
        meta: { title: '新闻资讯' }
      }
    ]
  },

  // {
  //   path: '/news',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'News',
  //       component: () => import('@/views/news/index'),
  //       meta: { title: '新闻资讯' }
  //     }
  //   ]
  // },
  {
    path: '/case',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Case',
        component: () => import('@/views/case/index'),
        meta: { title: '车场案例' }
      }
    ]
  },
  {
    path: '/cooperation',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Cooperation',
        component: () => import('@/views/cooperation/index'),
        meta: { title: '企业合作' }
      }
    ]
  },
  {
    path: '/recruitment',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Recruitment',
        component: () => import('@/views/recruitment/index'),
        meta: { title: '招聘信息' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

