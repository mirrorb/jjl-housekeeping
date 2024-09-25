package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizorder.*
import cn.lkgc.jjl.convert.BizOrderConvert
import cn.lkgc.jjl.dal.BizOrderMapper
import cn.lkgc.jjl.entity.BizOrder
import cn.lkgc.jjl.enums.CountFieldEnums
import cn.lkgc.jjl.enums.OrderStatusEnums
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import cn.lkgc.jjl.websocket.WebSocketService
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

@Service
class BizOrderServiceImpl(
    private val bizOrderMapper: BizOrderMapper,
    private val userEmployeeService: UserEmployeeService,
    private val userCustomerService: UserCustomerService,
    private val bizOrderConvert: BizOrderConvert
) : BizOrderService {

    override fun create(reqVO: BizOrderFrontCreateReqVO) {
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (loginUser.role != "客户") {
            throw exception("该用户无法预约")
        }
        val employee = userEmployeeService.frontDetail(reqVO.employee)
        val customer = userCustomerService.getByUsername(loginUser.username) ?: throw exception("客户不存在")
        val amount = employee.bizPrice?.times(reqVO.appointmentDuration)
        val roundedAmount = amount?.let { BigDecimal(it).setScale(2, RoundingMode.HALF_UP).toDouble() }
        bizOrderMapper.insert(
            BizOrder(
                customer = customer.username,
                customerMobile = customer.mobile,
                employee = employee.username,
                employeeMobile = employee.mobile,
                appointmentAddress = reqVO.appointmentAddress,
                appointmentTime = reqVO.appointmentTime,
                appointmentDuration = reqVO.appointmentDuration,
                amount = roundedAmount,
                status = OrderStatusEnums.UNREVIEWED.value,
                createTime = LocalDateTime.now(),
            )
        )
        WebSocketService.sendMessage("雇员", reqVO.employee, "您有新预约，请尽快处理")
    }

    override fun frontPage(reqVO: BizOrderFrontPageReqVO): PageResult<BizOrderFrontRespVO> {
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        return PageResultUtils.from(
            bizOrderMapper.selectFrontPage(
                Page(reqVO.current, reqVO.pageSize), reqVO.status, loginUser.username, loginUser.role
            )
        )
    }

    override fun getValidOrder(id: Int): BizOrder = bizOrderMapper.selectById(id) ?: throw exception("订单不存在")

    override fun review(reqVO: BizOrderReviewReqVO) {
        val order = getValidOrder(reqVO.id)
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (order.employee != loginUser.username) {
            throw exception("无权操作该订单")
        }
        val doo = bizOrderConvert.convert(reqVO)
        doo.reviewTime = LocalDateTime.now()
        bizOrderMapper.updateById(doo)
        WebSocketService.sendMessage("客户", order.customer!!, "预约已被审核，前往查看")
    }

    override fun cancel(id: Int) {
        val order = getValidOrder(id)
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (order.customer != loginUser.username) {
            throw exception("无权操作该订单")
        }
        order.status = OrderStatusEnums.CANCELLED.value
        order.finalizeTime = LocalDateTime.now()
        bizOrderMapper.updateById(order)
        WebSocketService.sendMessage("雇员", order.employee!!, "用户取消了订单，前往查看")
    }

    override fun pay(id: Int) {
        val order = getValidOrder(id)
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (order.customer != loginUser.username) {
            throw exception("无权操作该订单")
        }
        order.status = OrderStatusEnums.IN_PROGRESS.value
        bizOrderMapper.updateById(order)
        WebSocketService.sendMessage("雇员", order.employee!!, "用户支付了订单，前往查看")
    }

    override fun finish(id: Int) {
        val order = getValidOrder(id)
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (order.employee != loginUser.username) {
            throw exception("无权操作该订单")
        }
        order.status = OrderStatusEnums.COMPLETED.value
        order.finalizeTime = LocalDateTime.now()
        userEmployeeService.addCount(order.employee ?: throw exception("异常的订单"), CountFieldEnums.ORDER)
        bizOrderMapper.updateById(order)
        WebSocketService.sendMessage("客户", order.customer!!, "您的订单已完成，前往查看")
    }

    override fun updateRate(reqVO: BizOrderRateUpdateReqVO) {
        bizOrderMapper.updateById(bizOrderConvert.convert(reqVO))
    }

    override fun adminPage(reqVO: BizOrderAdminPageReqVO): PageResult<BizOrderAdminRespVO> = PageResultUtils.from(
        bizOrderMapper.selectAdminPage(
            Page(reqVO.current, reqVO.pageSize), reqVO
        )
    )

}