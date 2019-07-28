import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import store from './store/index'
import promise from 'es6-promise'

Vue.use(ElementUI)
promise.polyfill()
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
