package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.useremployee.*
import cn.lkgc.jjl.entity.UserEmployee
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserEmployeeConvert {
    fun convert(reqVO: UserEmployeeFrontRegReqVO): UserEmployee
    fun convert(reqVO: UserEmployee): UserEmployeeAdminRespVO
    fun convert(reqVO: UserEmployeeAdminUpdateReqVO): UserEmployee
    fun convert(reqVO: UserEmployeeAdminCreateReqVO): UserEmployee
    fun convertList(dos: List<UserEmployee>): List<UserEmployeeAdminRespVO>
    fun convertList2(dos: List<UserEmployee>): List<UserEmployeeFrontRespVO>
    fun convert2(doo: UserEmployee?): UserEmployeeFrontRespVO
    fun convert(reqVO: UserEmployeeSelfUpdateReqVO): UserEmployee
}