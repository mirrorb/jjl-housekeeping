package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.globalnews.*
import cn.lkgc.jjl.framework.pojo.PageResult

interface GlobalNewsService {
    fun listGlobalNews(): List<String>
    fun adminPage(reqVO: GlobalNewsAdminPageReqVO): PageResult<GlobalNewsAdminRespVO>
    fun adminDetail(newsName: String): GlobalNewsAdminRespVO
    fun adminUpdate(reqVO: GlobalNewsAdminUpdateReqVO)
    fun adminCreate(reqVO: GlobalNewsAdminCreateReqVO)
    fun adminDelete(newsName: String)
    fun frontLimit(): List<GlobalNewsFrontRespVO>
    fun frontPage(reqVO: GlobalNewsFrontPageReqVO): PageResult<GlobalNewsFrontRespVO>
    fun frontDetail(newsName: String): GlobalNewsFrontRespVO
}