import request from '@/utils/request'
export function getBrandList(params) {
    return request({
      url: '/brand',
      method: 'get',
      params
    })
  }