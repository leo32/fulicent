import request from '@/utils/request'
export function getProducts(params) {
    return request({
      url: '/products',
      method: 'get',
      params
    })
  }

  export function getProduct(id) {
    return request({
      url: '/products/'+id,
      method: 'get'
    })
  }

  export function getAdProducts(params) {
    return request({
      url: '/products/ad',
      method: 'get',
      params
    })
  }