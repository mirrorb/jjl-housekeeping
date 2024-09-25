package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentCreateReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentPageReqVO
import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentRespVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderRateUpdateReqVO
import cn.lkgc.jjl.convert.BizCommentConvert
import cn.lkgc.jjl.dal.BizCommentMapper
import cn.lkgc.jjl.entity.BizComment
import cn.lkgc.jjl.enums.UserTypeEnums
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class BizCommentServiceImpl(
    private val bizCommentMapper: BizCommentMapper,
    private val userCustomerService: UserCustomerService,
    private val userEmployeeService: UserEmployeeService,
    private val bizOrderService: BizOrderService,
    private val bizCommentConvert: BizCommentConvert
) : BizCommentService {

    override fun page(reqVO: BizCommentPageReqVO): PageResult<BizCommentRespVO> {
        // 查出评论分页
        val commentPage = PageResultUtils.from(bizCommentMapper.selectPage(
            Page(reqVO.current, reqVO.pageSize),
            reqVO
        ))
        // 评论的id
        val parentIds = commentPage.list.map { it.id!! }
        // 找出评论对应的回复分组
        val repliesGroup = parentIds.takeIf { it.isNotEmpty() }?.let { it ->
            bizCommentMapper.selectList(
                KtQueryWrapper(BizComment()).`in`(BizComment::parentId, it)
            ).groupBy { it.parentId }
        } ?: emptyMap()
        // 找出评论和回复对应的用户名
        val allUsernames = (commentPage.list + repliesGroup.values.flatten()).map { it.username!! }
        val customerUsernames = allUsernames.filter { username ->
            commentPage.list.any { it.username == username && it.role == UserTypeEnums.CUSTOMER.role } || repliesGroup.values.flatten()
                .any { it.username == username && it.role == UserTypeEnums.CUSTOMER.role }
        }
        val employeeUsernames = allUsernames.filter { username ->
            commentPage.list.any { it.username == username && it.role == UserTypeEnums.EMPLOYEE.role } || repliesGroup.values.flatten()
                .any { it.username == username && it.role == UserTypeEnums.EMPLOYEE.role }
        }
        // 找出评论和回复对应头像分组
        val customerAvatars = userCustomerService.list(customerUsernames)
            .associate { Pair(UserTypeEnums.CUSTOMER.role, it.username!!) to it.avatar }
        val employeeAvatars = userEmployeeService.list(employeeUsernames)
            .associate { Pair(UserTypeEnums.EMPLOYEE.role, it.username!!) to it.avatar }
        val avatarGroup = customerAvatars + employeeAvatars
        val resp = PageResultUtils.empty<BizCommentRespVO>(commentPage.total)
        resp.list = commentPage.list.map {
            BizCommentRespVO(id = it.id!!,
                username = it.username!!,
                role = it.role!!,
                avatar = avatarGroup[Pair(it.role!!, it.username!!)],
                text = it.text!!,
                replies = bizCommentConvert.convertList(repliesGroup[it.id] ?: emptyList()).map { reply ->
                    reply.apply {
                        avatar = avatarGroup[Pair(role, username)]
                    }
                })
        }
        return resp
    }


    override fun create(reqVO: BizCommentCreateReqVO): BizCommentRespVO {
        val doo: BizComment = bizCommentConvert.convert(reqVO)
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        doo.username = loginUser.username
        doo.role = loginUser.role
        doo.createTime = LocalDateTime.now()
        bizCommentMapper.insert(doo)
        return bizCommentConvert.convert(doo).apply {
            avatar = when (doo.role) {
                UserTypeEnums.CUSTOMER.role -> userCustomerService.getByUsername(username)?.avatar
                UserTypeEnums.EMPLOYEE.role -> userEmployeeService.getDo(username)?.avatar
                else -> throw exception("该用户不能评论")
            }
        }
    }

    @Transactional
    override fun delete(id: Int) {
        bizCommentMapper.deleteById(id)
        bizCommentMapper.delete(KtQueryWrapper(BizComment()).eq(BizComment::parentId, id))
    }

    override fun updateRate(reqVO: BizOrderRateUpdateReqVO) {
        bizOrderService.updateRate(reqVO)
    }

}