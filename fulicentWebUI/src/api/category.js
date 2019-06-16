import request from '@/utils/request'
export function getCategoryList() {
    return request({
      url: '/api/category',
      method: 'get'
    })
  }