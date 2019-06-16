import Vue from 'vue'
import Router from 'vue-router'
import Index from '../pages/index/index'
import Top from '../pages/top/top'
import Brand from '../pages/brand/brand'
import Recommend from '../pages/recommend/recommend'
import My from '../pages/my/my'
import Detail from '../pages/detail/detail'
Vue.use(Router)
export default new Router({
    //mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: Index,
            meta: {
                title: 'FuliCent'
            }
        },
        {
            path: '/',
            component: Index,
            meta: {
                title: '福利省钱网'
            }
        }, {
            path: '/top',
            component: Top,
            meta: { title: '超级人气' }
        }, {
            path: '/brand',
            component: Brand,
            meta: { title: '品牌优惠券' }
        }, {
            path: '/recommend',
            component: Recommend,
            meta: { title: '特卖精选' }
        }, {
            path: '/my',
            component: My,
            meta: { title: '我的足迹' }
        }, {
            path: '/detail/:id',
            component: Detail,
            meta: { title: '产品详情' }
        }]
}
);