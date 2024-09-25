package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.bizorder.BizOrderFrontRespVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderRateUpdateReqVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderReviewReqVO
import cn.lkgc.jjl.entity.BizOrder
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BizOrderConvert {
    fun convert(bean: BizOrder): BizOrderFrontRespVO
    fun convert(bean: BizOrderReviewReqVO): BizOrder
    fun convert(reqVO: BizOrderRateUpdateReqVO): BizOrder
}