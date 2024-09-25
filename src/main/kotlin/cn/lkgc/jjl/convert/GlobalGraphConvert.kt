package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.globalgraph.GlobalGraphEmployeeRespVO
import cn.lkgc.jjl.entity.UserEmployee
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GlobalGraphConvert {
    fun convert(bean: UserEmployee): GlobalGraphEmployeeRespVO
    fun convertList(limit3DO: List<UserEmployee>): List<GlobalGraphEmployeeRespVO>
}