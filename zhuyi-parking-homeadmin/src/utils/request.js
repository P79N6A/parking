import axios from 'axios'
import qs from 'qs'
import isObject from 'isobject'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 5000, // 请求超时时间
  // `transformRequest` allows changes to the request data before it is sent to the server
  // This is only applicable for request methods 'PUT', 'POST', and 'PATCH'
  // The last function in the array must return a string or an instance of Buffer, ArrayBuffer,
  // FormData or Stream
  // You may modify the headers object.
  transformRequest: [function(data, headers) {
    // Do whatever you want to transform the data
    var param = function(obj) {
      var query = ''
      var name, value, fullSubName, subName, subValue, innerObj, i
      for (name in obj) {
        value = obj[name]
        if (value instanceof Array) {
          for (i = 0; i < value.length; ++i) {
            subValue = value[i]
            fullSubName = name + '[]'
            innerObj = {}
            innerObj[fullSubName] = subValue
            query += param(innerObj) + '&'
          }
        } else if (value instanceof Object) {
          for (subName in value) {
            subValue = value[subName]
            fullSubName = subName
            innerObj = {}
            innerObj[fullSubName] = subValue
            query += param(innerObj) + '&'
          }
        } else if (value !== undefined && value !== null) {
          query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&'
        }
      }
      return query.length ? query.substr(0, query.length - 1) : query
    }
    return isObject(data) && String(data) !== '[object File]' ? param(data) : data
  }]
  // `transformResponse` allows changes to the response data to be made before
  // it is passed to then/catch
  // transformResponse: [function(data) {
  //   // Do whatever you want to transform the data
  //   return data
  // }]
})

if (service.defaults.headers.get) {
  service.defaults.headers.get = {}
}
service.defaults.withCredentials = true
service.defaults.headers = {
  'Acces-Control-Allow-Origin': '*',
  'Content-Type': 'application/json'
}
// 在实例已创建后修改默认值
// service.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
// `headers` are custom headers to be sent
// request拦截器
service.interceptors.request.use(config => {
  // Do something before request is sent
  if (config.method === 'post') {
    config.data = qs.stringify(config.data, { arrayFormat: 'indices', allowDots: true })
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8'
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// response拦截器
service.interceptors.response.use(
  response => response,
  /**
   * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
   * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
   */
  // const res = response.data;
  //     if (res.code !== 20000) {
  //       Message({
  //         message: res.message,
  //         type: 'error',
  //         duration: 5 * 1000
  //       });
  //       // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
  //       if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
  //         MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
  //           confirmButtonText: '重新登录',
  //           cancelButtonText: '取消',
  //           type: 'warning'
  //         }).then(() => {
  //           store.dispatch('FedLogOut').then(() => {
  //             location.reload();// 为了重新实例化vue-router对象 避免bug
  //           });
  //         })
  //       }
  //       return Promise.reject('error');
  //     } else {
  //       return response.data;
  //     }
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  })

export default service
