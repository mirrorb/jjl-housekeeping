package cn.lkgc.jjl.convert

import cn.lkgc.jjl.controller.vo.usercustomer.*
import cn.lkgc.jjl.entity.UserCustomer
import cn.lkgc.jjl.framework.pojo.PageResult
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserCustomerConvert {
    fun convert(reqVO: UserCustomerRegReqVO): UserCustomer
    fun convert(doo: UserCustomer): UserCustomerAdminRespVO
    fun convert(reqVO: UserCustomerAdminUpdateReqVO): UserCustomer
    fun convert(reqVO: UserCustomerAdminCreateReqVO): UserCustomer
    fun convertList(dos: List<UserCustomer>): List<UserCustomerAdminRespVO>
    fun convert2(doo: UserCustomer): UserCustomerFrontRespVO
    fun convert(reqVO: UserCustomerSelfUpdateReqVO): UserCustomer
}