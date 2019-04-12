import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        { 
            path: '/home',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    path: '/',
                    component: resolve => require(['../components/page/index.vue'], resolve)
                },
                {
                    path: '/basetable',
                    component: resolve => require(['../components/page/basetable.vue'], resolve)
                },
                {
                    path: '/upload',
                    component: resolve => require(['../components/page/upload.vue'], resolve)
                },
                {
                    path: '/editor',
                    component: resolve => require(['../components/page/editer.vue'], resolve)
                },
                {
                    path: '/baseform',
                    component: resolve => require(['../components/page/baseform.vue'], resolve)
                },
                {
                    path: '/layer',
                    component: resolve => require(['../components/page/layer.vue'], resolve)
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
    ]
})

