package cn.lkgc.jjl.service


import cn.lkgc.jjl.controller.vo.useremployee.*
import cn.lkgc.jjl.entity.UserEmployee
import cn.lkgc.jjl.enums.CountFieldEnums
import cn.lkgc.jjl.framework.pojo.PageResult

interface UserEmployeeService {
    fun getDo(username: String): UserEmployee?
    fun updateLoginTime(user: UserEmployee)
    fun reg(reqVO: UserEmployeeFrontRegReqVO)
    fun adminPage(reqVO: UserEmployeeAdminPageReqVO): PageResult<UserEmployeeAdminRespVO>
    fun adminDetail(username: String): UserEmployeeAdminRespVO
    fun adminUpdate(reqVO: UserEmployeeAdminUpdateReqVO)
    fun adminCreate(reqVO: UserEmployeeAdminCreateReqVO)
    fun adminDelete(username: String)
    fun selfUpdate(reqVO: UserEmployeeSelfUpdateReqVO)
    fun frontLimit(): List<UserEmployeeFrontRespVO>
    fun frontDetail(username: String): UserEmployeeFrontRespVO
    fun list(usernames: List<String>): List<UserEmployee>
    fun frontPage(reqVO: UserEmployeeFrontPageReqVO): PageResult<UserEmployeeFrontRespVO>
    fun addCount(username: String, type: CountFieldEnums)
    fun reduceCount(username: String, type: CountFieldEnums)
    fun update(employee: UserEmployee)
    fun limit3DO(): List<UserEmployee>
}