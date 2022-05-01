import request from '@/api'

//单日
export function getDaily(data) {
    return request({
        url: '/sales/getDaily',
        method: 'get',
        params: data
    })
}
// 单月
export function getMonthly(data) {
    return request({
        url: '/sales/getMonthly',
        method: 'get',
        params: data
    })
}
// 一年
export function getYear(data) {
    return request({
        url: '/sales/getYear',
        method: 'get',
        params: data
    })
}

export function getCommodityMarketingAmount(data) {
    return request({
        url: '/sales/getCommodityMarketingAmount',
        method: 'get',
        params: data
    })
}
export function getYearMarketingAmount(data) {
    return request({
        url: '/sales/getYearMarketingAmount',
        method: 'get',
        params: data
    })
}