package cn.lkgc.jjl.controller.vo.bizmerchant

import cn.lkgc.jjl.framework.pojo.PageParam

data class BizMerchantFrontPageReqVO(
    override var current: Long = 1,
    override var pageSize: Long = 5
): PageParam(current, pageSize)
