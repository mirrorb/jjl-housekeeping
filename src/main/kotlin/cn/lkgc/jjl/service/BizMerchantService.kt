package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizmerchant.*
import cn.lkgc.jjl.framework.pojo.PageResult

interface BizMerchantService {
    fun list(): List<String>
    fun adminPage(reqVO: BizMerchantAdminPageReqVO): PageResult<BizMerchantAdminRespVO>
    fun adminDetail(merchantName: String): BizMerchantAdminRespVO
    fun adminUpdate(reqVO: BizMerchantAdminUpdateReqVO)
    fun adminCreate(reqVO: BizMerchantAdminCreateReqVO)
    fun adminDelete(merchantName: String)
    fun frontLimit(): List<BizMerchantFrontRespVO>
    fun frontPage(reqVO: BizMerchantFrontPageReqVO): PageResult<BizMerchantFrontRespVO>
    fun frontDetail(merchantName: String): BizMerchantFrontRespVO
}