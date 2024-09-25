package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminRespVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminUpdateReqVO
import cn.lkgc.jjl.entity.BizArea
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BizAreaConvert {
    fun convert(doo: BizArea): BizAreaAdminRespVO
    fun convert(reqVO: BizAreaAdminUpdateReqVO): BizArea
    fun convert(reqVO: BizAreaAdminCreateReqVO): BizArea
    fun convertList(dos: List<BizArea>): List<BizAreaAdminRespVO>
}