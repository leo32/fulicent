import request from '@/utils/request'

export function getUsers(params) {
    return request({
      url: '/api/user',
      method: 'get',
      params
    })
  }

export function saveUsers(data) {
    return request({
      url: '/api/user/save',
      method: 'post',
      data: data
    })
  }

  export function putUsers(id,data) {
    return request({
      url: '/api/user/' + id,
      method: 'put',
      data: data
    })
  }