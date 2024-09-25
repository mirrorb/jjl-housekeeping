package cn.lkgc.jjl.controller

import cn.lkgc.jjl.controller.vo.bizstore.BizStoreUpdateReqVO
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.service.BizStoreService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 收藏点赞控制器
 */
@RequestMapping("/biz/store")
@RestController
class BizStoreController(
    private val bizStoreService: BizStoreService
) {

    @PostMapping("/update")
    fun update(@RequestBody reqVO: BizStoreUpdateReqVO) : CommonResult<Boolean> {
        bizStoreService.update(reqVO)
        return success(true)
    }

}