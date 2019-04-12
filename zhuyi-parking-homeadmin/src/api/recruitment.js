import request from '@/utils/request'

export function fetchEmployList(query) {
  return request({
    url: '/portal/admin/employ/getPageList',
    method: 'post',
    params: query
  })
}

export function createEmploy(data) {
  return request({
    url: '/portal/admin/employ/add',
    method: 'post',
    data
  })
}

export function getEmploy(query) {
  return request({
    url: '/portal/admin/employ/getById',
    method: 'post',
    params: query
  })
}

export function updateEmploy(data) {
  return request({
    url: '/portal/admin/employ/update',
    method: 'post',
    data
  })
}

export function deleteEmploy(query) {
  return request({
    url: '/portal/admin/employ/delete',
    method: 'post',
    params: query
  })
}

export function stickEmploy(query) {
  return request({
    url: '/portal/admin/employ/stick',
    method: 'post',
    params: query
  })
}

export function soldOutEmploy(query) {
  return request({
    url: '/portal/admin/employ/soldOut',
    method: 'post',
    params: query
  })
}

export function getDepartment(query) {
  return request({
    url: '/portal/admin/employ/getDepartmentList',
    method: 'post',
    params: query
  })
}
