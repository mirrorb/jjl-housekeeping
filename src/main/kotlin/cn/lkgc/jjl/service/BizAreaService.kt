package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminRespVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminUpdateReqVO
import cn.lkgc.jjl.framework.pojo.PageResult

interface BizAreaService {
    fun adminPage(reqVO: BizAreaAdminPageReqVO): PageResult<BizAreaAdminRespVO>
    fun adminDetail(areaName: String): BizAreaAdminRespVO
    fun adminUpdate(reqVO: BizAreaAdminUpdateReqVO)
    fun adminCreate(reqVO: BizAreaAdminCreateReqVO)
    fun adminDelete(areaName: String)
    fun list(): List<String>
}