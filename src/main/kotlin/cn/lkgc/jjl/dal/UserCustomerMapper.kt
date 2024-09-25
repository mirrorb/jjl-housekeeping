package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.usercustomer.UserCustomerAdminPageReqVO
import cn.lkgc.jjl.controller.vo.usercustomer.UserCustomerAdminRespVO
import cn.lkgc.jjl.entity.UserCustomer
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserCustomerMapper : BaseMapper<UserCustomer> {
    @Select("""
        <script>
        SELECT * FROM user_customer
        <where>
            <if test='reqVO.username != null and reqVO.username != ""'>
                AND username LIKE CONCAT('%', #{reqVO.username}, '%')
            </if>
            <if test='reqVO.nickname != null and reqVO.nickname != ""'>
                AND nickname LIKE CONCAT('%', #{reqVO.nickname}, '%')
            </if>
        </where>
        ORDER BY reg_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<UserCustomer>, @Param("reqVO") reqVO: UserCustomerAdminPageReqVO
    ): Page<UserCustomerAdminRespVO>
}