package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentCreateReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentPageReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentRespVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderRateUpdateReqVO
import cn.lkgc.jjl.framework.pojo.PageResult

interface BizCommentService {
    fun page(reqVO: BizCommentPageReqVO): PageResult<BizCommentRespVO>
    fun create(reqVO: BizCommentCreateReqVO): BizCommentRespVO
    fun delete(id: Int)
    fun updateRate(reqVO: BizOrderRateUpdateReqVO)
}