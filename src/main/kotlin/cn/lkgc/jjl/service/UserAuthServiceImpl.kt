package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.userauth.UserAuthLoginRespVO
import cn.lkgc.jjl.controller.vo.userauth.UserAuthResetPasswordReqVO
import cn.lkgc.jjl.entity.UserAdmin
import cn.lkgc.jjl.entity.UserCustomer
import cn.lkgc.jjl.entity.UserEmployee
import cn.lkgc.jjl.enums.ErrorCodeEnums
import cn.lkgc.jjl.enums.UserTypeEnums
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserAuthServiceImpl(
    private val userAdminService: UserAdminService,
    private val userEmployeeService: UserEmployeeService,
    private val userCustomerService: UserCustomerService,
    private val userTokenService: UserTokenService,
) : UserAuthService {

    override fun login(username: String, password: String, selectRole: String?): UserAuthLoginRespVO {
        val userList = when (selectRole) {
            UserTypeEnums.CUSTOMER.role -> listOfNotNull(userCustomerService.getByUsername(username))
            UserTypeEnums.EMPLOYEE.role -> listOfNotNull(userEmployeeService.getDo(username))
            UserTypeEnums.ADMIN.role -> listOfNotNull(userAdminService.getByUsername(username))
            null -> listOfNotNull(
                userAdminService.getByUsername(username),
                userEmployeeService.getDo(username),
                userCustomerService.getByUsername(username)
            )
            else -> throw exception("用户类型错误")
        }
        if (userList.isEmpty()) throw exception(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误")
        if (userList.size > 1) throw exception(ErrorCodeEnums.LOGIN_TYPE_NEEDED.value, "请选择用户类型")
        val user = userList.first()
        if (user.password != password) throw exception(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误")
        when (user) {
            is UserAdmin -> userAdminService.updateLoginTime(user)
            is UserEmployee -> userEmployeeService.updateLoginTime(user)
            is UserCustomer -> userCustomerService.updateLoginTime(user)
            else -> throw exception("用户类型错误")
        }
        val token = userTokenService.generateToken(user)
        return UserAuthLoginRespVO(token, user.role!!, user)
    }

    override fun resetPassword(reqVO: UserAuthResetPasswordReqVO) {
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        when (loginUser.role) {
            UserTypeEnums.ADMIN.role -> {
                val admin = userAdminService.getByUsername(loginUser.username) ?: throw exception("用户不存在")
                if (admin.password == reqVO.oldPassword) {
                    admin.password = reqVO.newPassword
                    userAdminService.update(admin)
                } else throw exception("密码错误")
            }

            UserTypeEnums.CUSTOMER.role -> {
                val customer = userCustomerService.getByUsername(loginUser.username) ?: throw exception("用户不存在")
                if (customer.password == reqVO.oldPassword) {
                    customer.password = reqVO.newPassword
                    userCustomerService.update(customer)
                } else throw exception("密码错误")
            }

            UserTypeEnums.EMPLOYEE.role -> {
                val employee = userEmployeeService.getDo(loginUser.username) ?: throw exception("用户不存在")
                if (employee.password == reqVO.oldPassword) {
                    employee.password = reqVO.newPassword
                    userEmployeeService.update(employee)
                } else throw exception("密码错误")
            }
        }
    }

}