import request from '@/utils/request'

export function listSeckills(query) {
  return request({
    url: '/seckill/list',
    method: 'get',
    params: query
  })
}

export function initSecKill() {
  return request({
    url: 'seckill/init',
    method: 'get'
  })
}
