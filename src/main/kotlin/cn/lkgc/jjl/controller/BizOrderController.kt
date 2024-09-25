package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.bizorder.*
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizOrderService
import org.springframework.web.bind.annotation.*

/**
 * 订单控制器
 */
@RequestMapping("/biz/order")
@RestController
class BizOrderController(
    private val bizOrderService: BizOrderService
) {

    @PostMapping("/create")
    fun create(@RequestBody reqVO: BizOrderFrontCreateReqVO): CommonResult<Boolean> {
        bizOrderService.create(reqVO)
        return success(true)
    }

    @GetMapping("/admin-page")
    fun adminPage(reqVO: BizOrderAdminPageReqVO): CommonResult<PageResult<BizOrderAdminRespVO>> = success(bizOrderService.adminPage(reqVO))

    @GetMapping("/front-page")
    fun frontPage(reqVO: BizOrderFrontPageReqVO): CommonResult<PageResult<BizOrderFrontRespVO>> = success(bizOrderService.frontPage(reqVO))

    @PostMapping("/review")
    fun review(@RequestBody reqVO: BizOrderReviewReqVO): CommonResult<Boolean> {
        bizOrderService.review(reqVO)
        return success(true)
    }

    @PostMapping("/cancel")
    fun cancel(id: Int): CommonResult<Boolean> {
        bizOrderService.cancel(id)
        return success(true)
    }

    @PostMapping("/pay")
    fun pay(id: Int): CommonResult<Boolean> {
        bizOrderService.pay(id)
        return success(true)
    }

    @PostMapping("/finish")
    fun finish(id: Int): CommonResult<Boolean> {
        bizOrderService.finish(id)
        return success(true)
    }

}