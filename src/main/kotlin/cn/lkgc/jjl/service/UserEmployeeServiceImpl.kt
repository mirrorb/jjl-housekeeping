package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.useremployee.*
import cn.lkgc.jjl.convert.UserEmployeeConvert
import cn.lkgc.jjl.dal.UserEmployeeMapper
import cn.lkgc.jjl.entity.UserEmployee
import cn.lkgc.jjl.enums.CountFieldEnums
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.JsoupUtils
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserEmployeeServiceImpl(
    private val userEmployeeMapper: UserEmployeeMapper, private val userEmployeeConvert: UserEmployeeConvert
) : UserEmployeeService {

    override fun getDo(username: String): UserEmployee? = userEmployeeMapper.selectOne(
        KtQueryWrapper(UserEmployee()).eq(UserEmployee::username, username)
    )

    override fun updateLoginTime(user: UserEmployee) {
        user.loginTime = LocalDateTime.now()
        userEmployeeMapper.updateById(user)
    }

    override fun reg(reqVO: UserEmployeeFrontRegReqVO) {
        val doo: UserEmployee = userEmployeeConvert.convert(reqVO)
        doo.regTime = LocalDateTime.now()
        doo.detailCount = 0
        doo.niceCount = 0
        doo.starCount = 0
        doo.orderCount = 0
        userEmployeeMapper.insert(doo)
    }

    override fun adminPage(reqVO: UserEmployeeAdminPageReqVO): PageResult<UserEmployeeAdminRespVO> =
        PageResultUtils.from(userEmployeeMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO))


    override fun adminDetail(username: String): UserEmployeeAdminRespVO {
        val doo = userEmployeeMapper.selectById(username)
        return userEmployeeConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: UserEmployeeAdminUpdateReqVO) {
        val doo = userEmployeeConvert.convert(reqVO)
        userEmployeeMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: UserEmployeeAdminCreateReqVO) {
        if (userEmployeeMapper.selectById(reqVO.username) != null) {
            throw exception("用户名已存在")
        }
        val doo = userEmployeeConvert.convert(reqVO)
        doo.regTime = LocalDateTime.now()
        doo.detailCount = 0
        doo.niceCount = 0
        doo.starCount = 0
        doo.orderCount = 0
        userEmployeeMapper.insert(doo)
    }

    override fun adminDelete(username: String) {
        userEmployeeMapper.deleteById(username)
    }

    override fun selfUpdate(reqVO: UserEmployeeSelfUpdateReqVO) {
        if (LoginUserUtils.loginUser?.username != reqVO.username) throw exception("无权操作该雇员")
        val doo = userEmployeeConvert.convert(reqVO)
        userEmployeeMapper.updateById(doo)
    }

    override fun addCount(username: String, type: CountFieldEnums) {
        val employee = userEmployeeMapper.selectById(username) ?: throw exception("雇员不存在")
        when (type) {
            CountFieldEnums.DETAIL -> employee.detailCount = (employee.detailCount ?: 0) + 1
            CountFieldEnums.NICE -> employee.niceCount = (employee.niceCount ?: 0) + 1
            CountFieldEnums.STAR -> employee.starCount = (employee.starCount ?: 0) + 1
            CountFieldEnums.ORDER -> employee.orderCount = (employee.orderCount ?: 0) + 1
        }
        userEmployeeMapper.updateById(employee)
    }

    override fun reduceCount(username: String, type: CountFieldEnums) {
        val employee = userEmployeeMapper.selectById(username) ?: throw exception("用户不存在")
        when (type) {
            CountFieldEnums.DETAIL -> employee.detailCount =
                (employee.detailCount ?: 0).let { if (it < 1) 0 else it - 1 }

            CountFieldEnums.NICE -> employee.niceCount = (employee.niceCount ?: 0).let { if (it < 1) 0 else it - 1 }
            CountFieldEnums.STAR -> employee.starCount = (employee.starCount ?: 0).let { if (it < 1) 0 else it - 1 }
            CountFieldEnums.ORDER -> employee.orderCount = (employee.orderCount ?: 0).let { if (it < 1) 0 else it - 1 }
        }
        userEmployeeMapper.updateById(employee)
    }

    override fun update(employee: UserEmployee) {
        userEmployeeMapper.updateById(employee)
    }

    override fun limit3DO(): List<UserEmployee> {
        return userEmployeeMapper.selectList(
            KtQueryWrapper(UserEmployee()).orderByDesc(
                UserEmployee::orderCount, UserEmployee::starCount, UserEmployee::niceCount, UserEmployee::detailCount
            ).last("LIMIT 3")
        )
    }

    override fun frontLimit(): List<UserEmployeeFrontRespVO> {
        val dos = limit3DO()
        dos.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return userEmployeeConvert.convertList2(dos)
    }

    override fun frontDetail(username: String): UserEmployeeFrontRespVO {
        val doo: UserEmployee = userEmployeeMapper.selectById(username) ?: throw exception("家政人员不存在")
        addCount(username, CountFieldEnums.DETAIL)
        return userEmployeeConvert.convert2(doo)
    }

    override fun list(usernames: List<String>): List<UserEmployee> = if (usernames.isNotEmpty()) {
        userEmployeeMapper.selectList(KtQueryWrapper(UserEmployee()).`in`(UserEmployee::username, usernames))
    } else {
        emptyList()
    }

    override fun frontPage(reqVO: UserEmployeeFrontPageReqVO): PageResult<UserEmployeeFrontRespVO> {
        val loginUser = LoginUserUtils.loginUser
        val page = PageResultUtils.from(
            userEmployeeMapper.employeeFrontPage(
                Page(reqVO.current, reqVO.pageSize), reqVO, loginUser?.username ?: ""
            )
        )
        page.list.forEach { it.description = JsoupUtils.getTextFromHtml(it.description ?: "") }
        return page
    }

}