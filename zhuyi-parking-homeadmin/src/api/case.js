import request from '@/utils/request'

export function fetchCaseList(query) {
  return request({
    url: '/portal/admin/parkcase/getPageList',
    method: 'post',
    params: query
  })
}

export function fetchTypeList(data) {
  return request({
    url: '/portal/admin/parkcase/getParkingTypeList',
    method: 'post',
    data
  })
}

export function createCase(data) {
  return request({
    url: '/portal/admin/parkcase/add',
    method: 'post',
    data
  })
}

export function getCase(query) {
  return request({
    url: '/portal/admin/parkcase/getById',
    method: 'post',
    params: query
  })
}

export function updateCase(data) {
  return request({
    url: '/portal/admin/parkcase/update',
    method: 'post',
    data
  })
}

export function deleteCase(query) {
  return request({
    url: '/portal/admin/parkcase/delete',
    method: 'post',
    params: query
  })
}

export function stickCase(query) {
  return request({
    url: '/portal/admin/parkcase/stick',
    method: 'post',
    params: query
  })
}

export function soldOutCase(query) {
  return request({
    url: '/portal/admin/parkcase/soldOut',
    method: 'post',
    params: query
  })
}
