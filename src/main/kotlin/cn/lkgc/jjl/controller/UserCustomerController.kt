package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.usercustomer.*
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.UserCustomerService
import org.springframework.web.bind.annotation.*

/**
 * 客户用户控制器
 */
@RequestMapping("/user/customer")
@RestController
class UserCustomerController(
    private val userCustomerService: UserCustomerService
) {

    @Permit
    @PostMapping("/reg")
    fun reg(@RequestBody reqVO: UserCustomerRegReqVO): CommonResult<Boolean> {
        userCustomerService.reg(reqVO)
        return success(true)
    }

    @PostMapping("/self-update")
    fun selfUpdate(@RequestBody reqVO: UserCustomerSelfUpdateReqVO): CommonResult<Boolean> {
        userCustomerService.selfUpdate(reqVO)
        return success(true)
    }

    @GetMapping("/front-detail")
    fun frontDetail(username: String): CommonResult<UserCustomerFrontRespVO> = success(userCustomerService.frontDetail(username))

    @GetMapping("/admin-page")
    fun adminPage(reqVO: UserCustomerAdminPageReqVO): CommonResult<PageResult<UserCustomerAdminRespVO>> = success(userCustomerService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(username: String): CommonResult<UserCustomerAdminRespVO> = success(userCustomerService.adminDetail(username))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: UserCustomerAdminUpdateReqVO): CommonResult<Boolean> {
        userCustomerService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: UserCustomerAdminCreateReqVO): CommonResult<Boolean> {
        userCustomerService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(username: String): CommonResult<Boolean> {
        userCustomerService.adminDelete(username)
        return success(true)
    }
    
}