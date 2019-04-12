import request from '@/utils/request'

export function fetchBusiList(query) {
  return request({
    url: '/portal/admin/busi/getPageList',
    method: 'post',
    params: query
  })
}

export function fetchTypeList(data) {
  return request({
    url: '/portal/admin/busi/getBusinessTypeList',
    method: 'post',
    data
  })
}

export function getBusi(query) {
  return request({
    url: '/portal/admin/busi/getById',
    method: 'post',
    params: query
  })
}

export function remarkBusi(query) {
  return request({
    url: '/portal/admin/busi/remark',
    method: 'post',
    params: query
  })
}
