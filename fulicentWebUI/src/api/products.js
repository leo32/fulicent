import request from '@/utils/request'
export function getProducts(params) {
    return request({
      url: '/api/products',
      method: 'get',
      params
    })
  }

  export function getProduct(id) {
    return request({
      url: '/api/products/'+id,
      method: 'get'
    })
  }

  export function getAdProducts(params) {
    return request({
      url: '/api/products/ad',
      method: 'get',
      params
    })
  }