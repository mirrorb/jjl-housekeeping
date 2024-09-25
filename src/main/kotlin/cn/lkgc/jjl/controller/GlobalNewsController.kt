package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.globalnews.*
import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.GlobalNewsService
import org.springframework.web.bind.annotation.*

/**
 * 系统公告控制器
 */
@RequestMapping("/global/news")
@RestController
class GlobalNewsController(
    private val globalNewsService: GlobalNewsService,
) {

    @GetMapping("/admin-page")
    fun adminPage(reqVO: GlobalNewsAdminPageReqVO): CommonResult<PageResult<GlobalNewsAdminRespVO>> = success(globalNewsService.adminPage(reqVO))

    @GetMapping("/admin-detail")
    fun adminDetail(newsName: String): CommonResult<GlobalNewsAdminRespVO> = success(globalNewsService.adminDetail(newsName))

    @PostMapping("/admin-update")
    fun adminUpdate(@RequestBody reqVO: GlobalNewsAdminUpdateReqVO): CommonResult<Boolean> {
        globalNewsService.adminUpdate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-create")
    fun adminCreate(@RequestBody reqVO: GlobalNewsAdminCreateReqVO): CommonResult<Boolean> {
        globalNewsService.adminCreate(reqVO)
        return success(true)
    }

    @PostMapping("/admin-delete")
    fun adminDelete(newsName: String): CommonResult<Boolean> {
        globalNewsService.adminDelete(newsName)
        return success(true)
    }

    @Permit
    @GetMapping("/front-limit")
    fun frontLimit(): CommonResult<List<GlobalNewsFrontRespVO>> = success(globalNewsService.frontLimit())

    @Permit
    @GetMapping("/front-page")
    fun frontPage(reqVO: GlobalNewsFrontPageReqVO): CommonResult<PageResult<GlobalNewsFrontRespVO>> = success(globalNewsService.frontPage(reqVO))

    @Permit
    @GetMapping("/front-detail")
    fun frontDetail(newsName: String): CommonResult<GlobalNewsFrontRespVO> = success(globalNewsService.frontDetail(newsName))

}