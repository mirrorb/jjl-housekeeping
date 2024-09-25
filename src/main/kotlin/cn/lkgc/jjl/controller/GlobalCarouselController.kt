package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.globalcarousel.*
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.GlobalCarouselService
import org.springframework.web.bind.annotation.*

/**
 * 系统轮播图控制器
 */
@RequestMapping("/global/carousel")
@RestController
class GlobalCarouselController(
    private val globalCarouselService: GlobalCarouselService,
) {

    @GetMapping("/admin-page")
    fun adminPage(reqVO: GlobalCarouselAdminPageReqVO): CommonResult<PageResult<GlobalCarouselAdminRespVO>> = success(globalCarouselService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(name: String): CommonResult<GlobalCarouselAdminRespVO> = success(globalCarouselService.adminDetail(name))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: GlobalCarouselAdminUpdateReqVO): CommonResult<Boolean> {
        globalCarouselService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: GlobalCarouselAdminCreateReqVO): CommonResult<Boolean> {
        globalCarouselService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(name: String): CommonResult<Boolean> {
        globalCarouselService.adminDelete(name)
        return success(true)
    }

    @Permit
    @GetMapping("/front-list")
    fun frontList(): CommonResult<List<GlobalCarouselFrontRespVO>> = success(globalCarouselService.frontList())

}