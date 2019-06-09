import Vue from 'vue'
import Router from 'vue-router'
import Index from '../pages/index/index'
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
        }]
}
);