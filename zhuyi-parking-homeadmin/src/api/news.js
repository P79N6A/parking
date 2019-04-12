import request from '@/utils/request'

export function fetchNewsList(query) {
  return request({
    url: '/portal/admin/news/getPageList',
    method: 'post',
    params: query
  })
}

export function fetchTypeList(data) {
  return request({
    url: '/portal/admin/news/getNewsTypeList',
    method: 'post',
    data
  })
}

export function createNews(data) {
  return request({
    url: '/portal/admin/news/add',
    method: 'post',
    data
  })
}

export function getNews(query) {
  return request({
    url: '/portal/admin/news/getById',
    method: 'post',
    params: query
  })
}

export function updateNews(data) {
  return request({
    url: '/portal/admin/news/update',
    method: 'post',
    data
  })
}

export function deleteNews(query) {
  return request({
    url: '/portal/admin/news/delete',
    method: 'post',
    params: query
  })
}

export function stickNews(query) {
  return request({
    url: '/portal/admin/news/stick',
    method: 'post',
    params: query
  })
}

export function soldOutNews(query) {
  return request({
    url: '/portal/admin/news/soldOut',
    method: 'post',
    params: query
  })
}
