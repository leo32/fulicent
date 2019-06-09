import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/cookies'

// create axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api base_url
  timeout: 50000
})

// request interceptors
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['token'] = getToken() // add token to header
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptors
service.interceptors.response.use(
  response => {
    /* const res = response.data
     if (res.status > 300 && res.code < 200) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })

      return Promise.reject('error')
    } else {
      return response.data
    }*/
    return response.data
  },
  error => {
    console.log(error) // for debug
    const res = (error.response && error.response.data) || undefined
    Message({
      message: res ? res.status.Hint : error.message,
      type: 'error',
      duration: 5 * 1000
    })
    if (res && res.status && res.status.ResponseStatus === '401401') {
      MessageBox.confirm(res.status.Hint, 'Logout', {
        confirmButtonText: 'Login Again',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        store.dispatch('FedLogOut').then(() => {
          location.reload()
        })
      })
    }
    return Promise.reject(error)
  }
)

export default service