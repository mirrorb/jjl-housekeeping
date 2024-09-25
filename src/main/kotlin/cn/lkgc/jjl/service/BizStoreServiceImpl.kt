package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.bizstore.BizStoreUpdateReqVO
import cn.lkgc.jjl.dal.BizStoreMapper
import cn.lkgc.jjl.entity.BizStore
import cn.lkgc.jjl.enums.CountFieldEnums
import cn.lkgc.jjl.enums.StoreTypeEnums
import cn.lkgc.jjl.enums.UserTypeEnums
import cn.lkgc.jjl.framework.utils.LoginUserUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BizStoreServiceImpl(
    private val bizStoreMapper: BizStoreMapper,
    private val userEmployeeService: UserEmployeeService
) : BizStoreService {

    @Transactional
    override fun update(reqVO: BizStoreUpdateReqVO) {
        val loginUser = LoginUserUtils.loginUser ?: throw exception("用户未登录")
        if (loginUser.role != UserTypeEnums.CUSTOMER.role) throw exception("该用户不支持该操作")
        val type = if (reqVO.type !in StoreTypeEnums.entries.map { it.value }) throw exception("错误的操作类型") else {
            StoreTypeEnums.fromValue(reqVO.type)
        }
        val employee = reqVO.employee
        userEmployeeService.getDo(employee) ?: throw exception("家政人员不存在")
        val customer = loginUser.username
        val key = "$customer-$employee"
        val store: BizStore? = bizStoreMapper.selectById(key)
        // 如果没有记录则添加点赞/收藏
        if (store == null) {
            bizStoreMapper.insert(BizStore(key, customer, employee, reqVO.type))
            // 更新雇员的点赞/收藏次数
            userEmployeeService.addCount(
                employee, if (type == StoreTypeEnums.STAR) CountFieldEnums.STAR else CountFieldEnums.NICE
            )
        } else {
            when (type) {
                // 用户操作了点赞
                StoreTypeEnums.NICE -> {
                    when (StoreTypeEnums.fromValue(store.type!!)) {
                        // 已经是点赞 -> 删除记录
                        StoreTypeEnums.NICE -> {
                            bizStoreMapper.deleteById(key)
                            userEmployeeService.reduceCount(employee, CountFieldEnums.NICE)
                        }
                        // 已经是收藏 -> 更新为点赞收藏
                        StoreTypeEnums.STAR -> {
                            bizStoreMapper.updateById(store.copy(type = StoreTypeEnums.NICE_STAR.value))
                            userEmployeeService.addCount(employee, CountFieldEnums.NICE)
                        }
                        // 已经是点赞收藏 -> 更新为收藏
                        StoreTypeEnums.NICE_STAR -> {
                            bizStoreMapper.updateById(store.copy(type = StoreTypeEnums.STAR.value))
                            userEmployeeService.reduceCount(employee, CountFieldEnums.NICE)
                        }
                    }
                }
                // 用户操作了收藏
                StoreTypeEnums.STAR -> {
                    when (StoreTypeEnums.fromValue(store.type!!)) {
                        // 已经是点赞 -> 更新为点赞收藏
                        StoreTypeEnums.NICE -> {
                            bizStoreMapper.updateById(store.copy(type = StoreTypeEnums.NICE_STAR.value))
                            userEmployeeService.addCount(employee, CountFieldEnums.STAR)
                        }
                        // 已经是收藏 -> 删除记录
                        StoreTypeEnums.STAR -> {
                            bizStoreMapper.deleteById(key)
                            userEmployeeService.reduceCount(employee, CountFieldEnums.STAR)
                        }
                        // 已经是点赞收藏 -> 更新为点赞
                        StoreTypeEnums.NICE_STAR -> {
                            bizStoreMapper.updateById(store.copy(type = StoreTypeEnums.NICE.value))
                            userEmployeeService.reduceCount(employee, CountFieldEnums.STAR)
                        }
                    }
                }
                else -> throw exception("错误的操作")
            }
        }
    }

}