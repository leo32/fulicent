import request from '@/utils/request'
export function getBrandList(params) {
    return request({
      url: '/api/brand',
      method: 'get',
      params
    })
  }