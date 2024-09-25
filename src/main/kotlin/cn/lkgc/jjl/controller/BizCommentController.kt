package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentCreateReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentPageReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentRespVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderRateUpdateReqVO
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.service.BizCommentService
import org.springframework.web.bind.annotation.*

/**
 * 评论控制器
 */
@RequestMapping("/biz/comment")
@RestController
class BizCommentController(
    private val bizCommentService: BizCommentService
) {

    @GetMapping("/page")
    fun page(reqVO: BizCommentPageReqVO): CommonResult<PageResult<BizCommentRespVO>> {
        return success(bizCommentService.page(reqVO))
    }

    @PostMapping("/create")
    fun create(@RequestBody reqVO: BizCommentCreateReqVO): CommonResult<BizCommentRespVO> {
        return success(bizCommentService.create(reqVO))
    }

    @PostMapping("/delete")
    fun delete(id: Int): CommonResult<Boolean> {
        bizCommentService.delete(id)
        return success(true)
    }

    @PostMapping("/update-rate")
    fun updateRate(@RequestBody reqVO: BizOrderRateUpdateReqVO): CommonResult<Boolean> {
        bizCommentService.updateRate(reqVO)
        return success(true)
    }

}