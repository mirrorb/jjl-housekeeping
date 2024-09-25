package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminPageReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminRespVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminUpdateReqVO
import cn.lkgc.jjl.framework.pojo.PageResult

interface BizTypeService {
    fun adminPage(reqVO: BizTypeAdminPageReqVO): PageResult<BizTypeAdminRespVO>
    fun adminDetail(typeName: String): BizTypeAdminRespVO
    fun adminUpdate(reqVO: BizTypeAdminUpdateReqVO)
    fun adminCreate(reqVO: BizTypeAdminCreateReqVO)
    fun adminDelete(typeName: String)
    fun list(): List<String>
}