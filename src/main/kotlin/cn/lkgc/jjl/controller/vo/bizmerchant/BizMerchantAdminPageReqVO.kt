package cn.lkgc.jjl.controller.vo.bizmerchant

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizMerchantAdminPageReqVO(
    val merchantName: String? = null,
    val telephone: String? = null,
    override var current: Long = 1L,
    override var pageSize: Long = 5L
): PageParam(current, pageSize)