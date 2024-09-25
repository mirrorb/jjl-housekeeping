package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.useremployee.UserEmployeeAdminPageReqVO
import cn.lkgc.jjl.controller.vo.useremployee.UserEmployeeAdminRespVO
import cn.lkgc.jjl.controller.vo.useremployee.UserEmployeeFrontPageReqVO
import cn.lkgc.jjl.controller.vo.useremployee.UserEmployeeFrontRespVO
import cn.lkgc.jjl.entity.UserEmployee
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select


@Mapper
interface UserEmployeeMapper : BaseMapper<UserEmployee> {
    @Select("""
        <script>
        SELECT ue.*, 
        CASE WHEN bs.type = '点赞' OR bs.type = '点赞收藏' THEN true ELSE false END AS nice, 
        CASE WHEN bs.type = '收藏' OR bs.type = '点赞收藏' THEN true ELSE false END AS star 
        FROM user_employee ue LEFT JOIN biz_store bs ON ue.username = bs.employee AND bs.customer = #{username} 
        <where>
            <if test='reqVO.employee != null and reqVO.employee != "" '> 
                AND ue.username LIKE '%' || #{reqVO.employee} || '%' 
            </if> 
            <if test='reqVO.star != null and reqVO.star != "" '> 
                AND (bs.type = '收藏' OR bs.type = '点赞收藏') </if> 
            <if test='reqVO.nice != null and reqVO.nice != "" '> 
                AND (bs.type = '点赞' OR bs.type = '点赞收藏') 
            </if> 
        </where> 
        ORDER BY ue.order_count DESC, ue.star_count DESC, ue.nice_count DESC, ue.detail_count DESC
        </script>
    """)
    fun employeeFrontPage(
        @Param("page") page: Page<UserEmployeeFrontRespVO>,
        @Param("reqVO") reqVO: UserEmployeeFrontPageReqVO,
        @Param("username") username: String
    ): Page<UserEmployeeFrontRespVO>

    @Select("""
        <script>
        SELECT * FROM user_employee
        <where>
            <if test='reqVO.username != null and reqVO.username != ""'>
                AND username LIKE CONCAT('%', #{reqVO.username}, '%')
            </if>
            <if test='reqVO.name != null and reqVO.name != ""'>
                AND name LIKE CONCAT('%', #{reqVO.name}, '%')
            </if>
        </where> 
        ORDER BY reg_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<UserEmployee>, @Param("reqVO") reqVO: UserEmployeeAdminPageReqVO
    ): Page<UserEmployeeAdminRespVO>
}