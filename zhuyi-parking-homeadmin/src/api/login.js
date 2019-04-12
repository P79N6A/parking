import request from '@/utils/request'

export function login(userName, password) {
  return request({
    url: '/portal/user/login',
    method: 'post',
    data: {
      userName,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/portal/user/currentUser',
    method: 'post',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/portal/user/exitLogin',
    method: 'post'
  })
}
