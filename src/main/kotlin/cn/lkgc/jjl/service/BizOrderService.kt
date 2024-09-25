package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizorder.*
import cn.lkgc.jjl.entity.BizOrder
import cn.lkgc.jjl.framework.pojo.PageResult

interface BizOrderService {
    fun create(reqVO: BizOrderFrontCreateReqVO)
    fun frontPage(reqVO: BizOrderFrontPageReqVO): PageResult<BizOrderFrontRespVO>
    fun getValidOrder(id: Int): BizOrder
    fun review(reqVO: BizOrderReviewReqVO)
    fun cancel(id: Int)
    fun pay(id: Int)
    fun finish(id: Int)
    fun updateRate(reqVO: BizOrderRateUpdateReqVO)
    fun adminPage(reqVO: BizOrderAdminPageReqVO): PageResult<BizOrderAdminRespVO>
}