package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantAdminRespVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantAdminUpdateReqVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantFrontRespVO
import cn.lkgc.jjl.entity.BizMerchant
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BizMerchantConvert {
    fun convert(reqVO: BizMerchant): BizMerchantAdminRespVO
    fun convert(reqVO: BizMerchantAdminUpdateReqVO): BizMerchant
    fun convert(reqVO: BizMerchantAdminCreateReqVO): BizMerchant
    fun convertList(dos: List<BizMerchant>): List<BizMerchantAdminRespVO>
    fun convertList2(dos: List<BizMerchant>): List<BizMerchantFrontRespVO>
    fun convert2(doo: BizMerchant?): BizMerchantFrontRespVO
}