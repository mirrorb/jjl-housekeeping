package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminPageReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminRespVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminUpdateReqVO
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizTypeService
import org.springframework.web.bind.annotation.*

/**
 * 服务类别控制器
 */
@RequestMapping("/biz/type")
@RestController
class BizTypeController(
    private val globalTypeService: BizTypeService,
) {

    @Permit
    @GetMapping("/list")
    fun frontList(): CommonResult<List<String>> = success(globalTypeService.list())

    @GetMapping("/admin-page")
    fun adminPage(reqVO: BizTypeAdminPageReqVO): CommonResult<PageResult<BizTypeAdminRespVO>> = success(globalTypeService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(typeName: String): CommonResult<BizTypeAdminRespVO> = success(globalTypeService.adminDetail(typeName))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: BizTypeAdminUpdateReqVO): CommonResult<Boolean> {
        globalTypeService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: BizTypeAdminCreateReqVO): CommonResult<Boolean> {
        globalTypeService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(typeName: String): CommonResult<Boolean> {
        globalTypeService.adminDelete(typeName)
        return success(true)
    }

}