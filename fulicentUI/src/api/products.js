import cml from "chameleon-api";
export function getProducts(params) {
    return cml.request({
      url: 'http://localhost:8080/products',
      method: 'get',
      data: params
    })
  }