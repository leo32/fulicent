import cml from "chameleon-api";
export function getCategoryList() {
    return cml.request({
      url: 'http://localhost:8080/category',
      method: 'get'
    })
  }