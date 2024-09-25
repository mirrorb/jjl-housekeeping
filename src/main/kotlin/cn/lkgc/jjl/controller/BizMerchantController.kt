package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.bizmerchant.*
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizMerchantService
import org.springframework.web.bind.annotation.*

/**
 * 服务商家控制器
 */
@RequestMapping("/biz/merchant")
@RestController
class BizMerchantController(
    private val bizMerchantService: BizMerchantService,
) {

    @Permit
    @GetMapping("/list")
    fun listServiceMerchant(): CommonResult<List<String>> = success(bizMerchantService.list())

    @GetMapping("/admin-page")
    fun adminPage(reqVO: BizMerchantAdminPageReqVO): CommonResult<PageResult<BizMerchantAdminRespVO>> = success(bizMerchantService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(merchantName: String): CommonResult<BizMerchantAdminRespVO> = success(bizMerchantService.adminDetail(merchantName))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: BizMerchantAdminUpdateReqVO): CommonResult<Boolean> {
        bizMerchantService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: BizMerchantAdminCreateReqVO): CommonResult<Boolean> {
        bizMerchantService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(merchantName: String): CommonResult<Boolean> {
        bizMerchantService.adminDelete(merchantName)
        return success(true)
    }

    @Permit
    @GetMapping("/front-limit")
    fun frontLimit(): CommonResult<List<BizMerchantFrontRespVO>> = success(bizMerchantService.frontLimit())

    @Permit
    @GetMapping("/front-page")
    fun frontPage(reqVO: BizMerchantFrontPageReqVO): CommonResult<PageResult<BizMerchantFrontRespVO>> = success(bizMerchantService.frontPage(reqVO))

    @Permit
    @GetMapping("/front-detail")
    fun frontDetail(merchantName: String): CommonResult<BizMerchantFrontRespVO> = success(bizMerchantService.frontDetail(merchantName))

}