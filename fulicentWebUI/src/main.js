import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import store from './store/index'
import promise from 'es6-promise'
import MetaInfo from 'vue-meta-info'

Vue.use(ElementUI)
Vue.use(MetaInfo)
promise.polyfill()
new Vue({
  el: '#app',
  store,
  router,
   /* 这句非常重要，否则预渲染将不会启动 */
   mounted () {
    document.dispatchEvent(new Event('render-event'))
   },
  render: h => h(App)
})
