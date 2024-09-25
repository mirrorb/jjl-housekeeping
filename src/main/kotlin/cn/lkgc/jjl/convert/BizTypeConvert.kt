package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminRespVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminUpdateReqVO
import cn.lkgc.jjl.entity.BizType
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BizTypeConvert {
    fun convert(doo: BizType): BizTypeAdminRespVO
    fun convert(reqVO: BizTypeAdminUpdateReqVO): BizType
    fun convert(reqVO: BizTypeAdminCreateReqVO): BizType
    fun convertList(dos: List<BizType>): List<BizTypeAdminRespVO>
}