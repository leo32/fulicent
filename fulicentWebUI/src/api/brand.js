import request from '@/utils/request'
export function getBrandList() {
    return request({
      url: '/brand',
      method: 'get'
    })
  }