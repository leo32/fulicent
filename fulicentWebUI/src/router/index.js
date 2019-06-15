import Vue from 'vue'
import Router from 'vue-router'
import Index from '../pages/index/index'
import Top from '../pages/top/top'
import Brand from '../pages/brand/brand'
import Recommend from '../pages/recommend/recommend'
import Detail from '../pages/detail/detail'
Vue.use(Router)
export default new Router({
    mode: 'history',
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
            path: '/index',
            component: Index,
            meta: {
                title: 'FuliCent'
            }
        }, {
            path: '/top',
            component: Top,
            meta: { title: 'top' }
        }, {
            path: '/brand',
            component: Brand,
            meta: { title: 'brand' }
        }, {
            path: '/recommend',
            component: Recommend,
            meta: { title: 'recommend' }
        }, {
            path: '/detail/:id',
            component: Detail,
            meta: { title: 'detail' }
        }]
}
);