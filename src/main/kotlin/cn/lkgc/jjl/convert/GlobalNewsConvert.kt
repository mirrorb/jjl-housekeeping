package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsAdminRespVO
import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsAdminUpdateReqVO
import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsFrontRespVO
import cn.lkgc.jjl.entity.GlobalNews
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GlobalNewsConvert {
    fun convert(reqVO: GlobalNews): GlobalNewsAdminRespVO
    fun convert(reqVO: GlobalNewsAdminUpdateReqVO): GlobalNews
    fun convert(reqVO: GlobalNewsAdminCreateReqVO): GlobalNews
    fun convertList(dos: List<GlobalNews>): List<GlobalNewsAdminRespVO>
    fun convertList2(dos: List<GlobalNews>): List<GlobalNewsFrontRespVO>
    fun convert2(doo: GlobalNews?): GlobalNewsFrontRespVO
}