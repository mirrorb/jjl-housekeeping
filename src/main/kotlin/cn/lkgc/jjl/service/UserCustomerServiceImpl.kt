package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.usercustomer.*
import cn.lkgc.jjl.convert.UserCustomerConvert
import cn.lkgc.jjl.dal.UserCustomerMapper
import cn.lkgc.jjl.entity.UserCustomer
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserCustomerServiceImpl(
    private val userCustomerMapper: UserCustomerMapper, private val userCustomerConvert: UserCustomerConvert
) : UserCustomerService {

    override fun getByUsername(username: String): UserCustomer? {
        val doo: UserCustomer? = userCustomerMapper.selectOne(
            KtQueryWrapper(UserCustomer()).eq(UserCustomer::username, username)
        )
        return doo
    }

    override fun updateLoginTime(user: UserCustomer) {
        user.loginTime = LocalDateTime.now()
        userCustomerMapper.updateById(user)
    }

    override fun reg(reqVO: UserCustomerRegReqVO) {
        if(getByUsername(reqVO.username) != null) {
            throw exception("用户已存在");
        }
        val doo: UserCustomer = userCustomerConvert.convert(reqVO)
        doo.regTime = LocalDateTime.now()
        userCustomerMapper.insert(doo)
    }

    override fun selfUpdate(reqVO: UserCustomerSelfUpdateReqVO) {
        if (LoginUserUtils.loginUser?.username != reqVO.username) throw exception("无权操作该客户")
        val doo = userCustomerConvert.convert(reqVO)
        userCustomerMapper.updateById(doo)
    }

    override fun frontDetail(username: String): UserCustomerFrontRespVO {
        val doo: UserCustomer = userCustomerMapper.selectById(username) ?: throw exception("客户不存在")
        return userCustomerConvert.convert2(doo)
    }

    override fun adminPage(reqVO: UserCustomerAdminPageReqVO): PageResult<UserCustomerAdminRespVO> =
        PageResultUtils.from(userCustomerMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO))

    override fun adminDetail(username: String): UserCustomerAdminRespVO {
        val doo = userCustomerMapper.selectById(username)
        return userCustomerConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: UserCustomerAdminUpdateReqVO) {
        val doo = userCustomerConvert.convert(reqVO)
        userCustomerMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: UserCustomerAdminCreateReqVO) {
        if (userCustomerMapper.selectById(reqVO.username) != null) {
            throw exception("用户名已存在")
        }
        val doo = userCustomerConvert.convert(reqVO)
        doo.regTime = LocalDateTime.now()
        userCustomerMapper.insert(doo)
    }

    override fun adminDelete(username: String) {
        userCustomerMapper.deleteById(username)
    }

    override fun list(usernames: List<String>): List<UserCustomer> = if (usernames.isNotEmpty()) {
        userCustomerMapper.selectList(KtQueryWrapper(UserCustomer()).`in`(UserCustomer::username, usernames))
    } else {
        emptyList()
    }

    override fun update(customer: UserCustomer) {
        userCustomerMapper.updateById(customer)
    }

}