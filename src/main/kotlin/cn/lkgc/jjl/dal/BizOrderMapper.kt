package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.bizorder.BizOrderAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderAdminRespVO
import cn.lkgc.jjl.controller.vo.bizorder.BizOrderFrontRespVO
import cn.lkgc.jjl.entity.BizOrder
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BizOrderMapper : BaseMapper<BizOrder> {
    @Select("""
        <script>
        SELECT * FROM biz_order
        <where>
            <if test='status != null and status != ""'>
                AND status = #{status}
            </if>
            <if test='role == "客户"'>
                AND customer = #{username}
            </if>
            <if test='role == "雇员"'>
                AND employee = #{username}
            </if>
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectFrontPage(
        @Param("page") page: Page<BizOrder>,
        @Param("status") status: String,
        @Param("username") username: String,
        @Param("role") role: String
    ): Page<BizOrderFrontRespVO>

    @Select("""
        <script>
        SELECT * FROM biz_order
        <where>
            <if test='reqVO.id != null'>
                AND CAST(id AS VARCHAR) LIKE CONCAT('%', #{reqVO.id}, '%')
            </if>
            <if test='reqVO.customer != null and reqVO.customer != ""'>
                AND customer LIKE CONCAT('%', #{reqVO.customer}, '%')
            </if>
            <if test='reqVO.customerMobile != null and reqVO.customerMobile != ""'>
                AND customer_mobile LIKE CONCAT('%', #{reqVO.customerMobile}, '%')
            </if>
            <if test='reqVO.employee != null and reqVO.employee != ""'>
                AND employee LIKE CONCAT('%', #{reqVO.employee}, '%')
            </if>
            <if test='reqVO.employeeMobile != null and reqVO.employeeMobile != ""'>
                AND employee_mobile LIKE CONCAT('%', #{reqVO.employeeMobile}, '%')
            </if>
            <if test='reqVO.status != null and reqVO.status != ""'>
                AND status = #{reqVO.status}
            </if>
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<BizOrder>,
        @Param("reqVO") reqVO: BizOrderAdminPageReqVO
    ): Page<BizOrderAdminRespVO>
}