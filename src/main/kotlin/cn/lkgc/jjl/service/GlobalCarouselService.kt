package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalcarousel.*
import cn.lkgc.jjl.framework.pojo.PageResult

interface GlobalCarouselService {
    fun adminPage(reqVO: GlobalCarouselAdminPageReqVO): PageResult<GlobalCarouselAdminRespVO>
    fun adminDetail(name: String): GlobalCarouselAdminRespVO
    fun adminUpdate(reqVO: GlobalCarouselAdminUpdateReqVO)
    fun adminCreate(reqVO: GlobalCarouselAdminCreateReqVO)
    fun adminDelete(name: String)
    fun frontList(): List<GlobalCarouselFrontRespVO>
}