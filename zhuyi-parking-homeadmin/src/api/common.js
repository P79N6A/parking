import request from '@/utils/request'

export function getStatusList(data) {
  return request({
    url: '/portal/public/getStatusList',
    method: 'post',
    data
  })
}
