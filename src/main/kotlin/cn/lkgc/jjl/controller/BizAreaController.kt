package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminRespVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminUpdateReqVO
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizAreaService
import org.springframework.web.bind.annotation.*

/**
 * 服务类别控制器
 */
@RequestMapping("/biz/area")
@RestController
class BizAreaController(
    private val globalAreaService: BizAreaService,
) {

    @Permit
    @GetMapping("/list")
    fun frontList(): CommonResult<List<String>> = success(globalAreaService.list())

    @GetMapping("/admin-page")
    fun adminPage(reqVO: BizAreaAdminPageReqVO): CommonResult<PageResult<BizAreaAdminRespVO>> = success(globalAreaService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(areaName: String): CommonResult<BizAreaAdminRespVO> = success(globalAreaService.adminDetail(areaName))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: BizAreaAdminUpdateReqVO): CommonResult<Boolean> {
        globalAreaService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: BizAreaAdminCreateReqVO): CommonResult<Boolean> {
        globalAreaService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(areaName: String): CommonResult<Boolean> {
        globalAreaService.adminDelete(areaName)
        return success(true)
    }

}