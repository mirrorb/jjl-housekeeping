package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.usercustomer.*
import cn.lkgc.jjl.entity.UserCustomer
import cn.lkgc.jjl.framework.pojo.PageResult

interface UserCustomerService {
    fun getByUsername(username: String): UserCustomer?
    fun updateLoginTime(user: UserCustomer)
    fun reg(reqVO: UserCustomerRegReqVO)
    fun selfUpdate(reqVO: UserCustomerSelfUpdateReqVO)
    fun frontDetail(username: String): UserCustomerFrontRespVO
    fun adminPage(reqVO: UserCustomerAdminPageReqVO): PageResult<UserCustomerAdminRespVO>
    fun adminDetail(username: String): UserCustomerAdminRespVO
    fun adminUpdate(reqVO: UserCustomerAdminUpdateReqVO)
    fun adminCreate(reqVO: UserCustomerAdminCreateReqVO)
    fun adminDelete(username: String)
    fun list(usernames: List<String>): List<UserCustomer>
    fun update(customer: UserCustomer)
}