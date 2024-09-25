package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.useremployee.*
import cn.lkgc.jjl.enums.GlobalOptionsEnums
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizAreaService
import cn.lkgc.jjl.service.BizTypeService
import cn.lkgc.jjl.service.GlobalOptionsService
import cn.lkgc.jjl.service.UserEmployeeService
import org.springframework.web.bind.annotation.*

/**
 * 客户用户控制器
 */
@RequestMapping("/user/employee")
@RestController
class UserEmployeeController(
    private val userEmployeeService: UserEmployeeService,
    private val bizAreaService: BizAreaService,
    private val bizTypeService: BizTypeService,
    private val globalOptionsService: GlobalOptionsService
) {

    @Permit
    @GetMapping("/list-work-type")
    fun listWorkType(): CommonResult<List<String>> = success(globalOptionsService.listOption(GlobalOptionsEnums.WORK_TYPE))

    @Permit
    @PostMapping("/reg")
    fun reg(@RequestBody reqVO: UserEmployeeFrontRegReqVO): CommonResult<Boolean> {
        userEmployeeService.reg(reqVO)
        return success(true)
    }

    @GetMapping("/admin-page")
    fun adminPage(reqVO: UserEmployeeAdminPageReqVO): CommonResult<PageResult<UserEmployeeAdminRespVO>> = success(userEmployeeService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(username: String): CommonResult<UserEmployeeAdminRespVO> = success(userEmployeeService.adminDetail(username))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: UserEmployeeAdminUpdateReqVO): CommonResult<Boolean> {
        userEmployeeService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: UserEmployeeAdminCreateReqVO): CommonResult<Boolean> {
        userEmployeeService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(username: String): CommonResult<Boolean> {
        userEmployeeService.adminDelete(username)
        return success(true)
    }

    @PostMapping("/self-update")
    fun selfUpdate(@RequestBody reqVO: UserEmployeeSelfUpdateReqVO): CommonResult<Boolean> {
        userEmployeeService.selfUpdate(reqVO)
        return success(true)
    }

    @Permit
    @GetMapping("/front-limit")
    fun frontLimit(): CommonResult<List<UserEmployeeFrontRespVO>> = success(userEmployeeService.frontLimit())

    @Permit
    @GetMapping("/front-page")
    fun frontPage(reqVO: UserEmployeeFrontPageReqVO): CommonResult<PageResult<UserEmployeeFrontRespVO>> = success(userEmployeeService.frontPage(reqVO))

    @Permit
    @GetMapping("/front-detail")
    fun frontDetail(username: String): CommonResult<UserEmployeeFrontRespVO> = success(userEmployeeService.frontDetail(username))

}