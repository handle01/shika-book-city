// 导入用来创建路由和确定路由模式的两个方法
import storage from '@/util/storage'

import {
    createRouter,
    createWebHistory
} from 'vue-router'

/**
 * 定义路由信息
 *
 */
const routes = [{
    name: 'login',
    path: '/login',
    component: () => import('@/pages/login')
}, {
    name: 'main',
    path: '/main',
    // alias: "/",
    component: () => import('@/pages/main'),
    children: [
        {
            name: 'order',
            path: '/order',
            component: () => import('@/pages/order'),
            children: [
                {
                    name: 'send',
                    path: '/send',
                    component: () => import('@/pages/order/send.vue')
                },
                {
                    name: 'expressage',
                    path: '/expressage',
                    component: () => import('@/pages/order/expressage.vue')
                }, {
                    name: 'signed',
                    path: '/signed',
                    component: () => import('@/pages/order/signed.vue')
                },
            ]
        },
        {
            name: 'activity',
            path: '/activity',
            component: () => import('@/pages/activity'),
            children: [
                {
                    name: 'isDie',
                    path: '/isDie',
                    alias: "/",
                    component: () => import('@/pages/activity/isDie.vue')
                },
                {
                    name: 'noDie',
                    path: '/noDie',
                    component: () => import('@/pages/activity/noDie.vue')
                }, {
                    name: 'publish',
                    path: '/publish',
                    component: () => import('@/pages/activity/publish.vue')
                },
                {
                    name: 'noPublish',
                    path: '/noPublish',
                    component: () => import('@/pages/activity/noPublish.vue')
                }

            ],
        },
        {
            name: 'config',
            path: '/config',
            component: () => import('@/pages/config')
        },
        {
            name: 'book',
            path: '/book',
            component: () => import('@/pages/book'),
            children: [
                {
                    name: 'isAdded',
                    path: '/isAdded',
                    alias: "/",
                    component: () => import('@/pages/book/isAdded.vue')
                },
                {
                    name: 'noAdded',
                    path: '/noAdded',
                    component: () => import('@/pages/book/noAdded.vue')
                }

            ],
        },
        {
            name: 'bookComment',
            path: '/bookComment',
            component: () => import('@/pages/bookComment') 
        },
        {
            name: 'comment',
            path: '/comment',
            component: () => import('@/pages/bookComment/comment.vue') 
        },
        {
            name: 'dailySales',
            path: '/dailySales',
            component: () => import('@/pages/bookSales/dailySales.vue'),
        },
        {
            name: 'monthlySales',
            path: '/monthlySales',
            component: () => import('@/pages/bookSales/monthlySales.vue'),
        },
        {
            name: 'yearSales',
            path: '/yearSales',
            component: () => import('@/pages/bookSales/yearSales.vue'),
        },
        {
            name: 'commodityMarketingAmount',
            path: '/commodityMarketingAmount',
            component: () => import('@/pages/bookSales/commodityMarketingAmount.vue'),
        },
        {
            name: 'yearMarketingAmount',
            path: '/yearMarketingAmount',
            component: () => import('@/pages/bookSales/yearMarketingAmount.vue'),
        },
        
    ]
},
{
    name: 'register',
    path: '/register',
    component: () => import('@/pages/register')
},


]

// 创建路由实例并传递 `routes` 配置
// 我们在这里使用 html5 的路由模式，url中不带有#，部署项目的时候需要注意。
const router = createRouter({
    history: createWebHistory(),
    routes,
})


// // 全局的路由守卫；验证是否登录
router.beforeEach((to) => {

    if (to.name === "login" || to.name === "register") {
        return true;
    }

    if (storage.getSessionString("token") == null || storage.getSessionString("token") == '') {
        router.push({ name: "login" })
        return false
    }
    return true;
})

// 讲路由实例导出
export default router