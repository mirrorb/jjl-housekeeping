package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminPageReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminRespVO
import cn.lkgc.jjl.entity.BizType
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BizTypeMapper : BaseMapper<BizType> {
    @Select("""
        <script>
        SELECT * FROM biz_type
        <where>
            <if test='reqVO.typeName != null and reqVO.typeName != ""'>
                AND type_name LIKE CONCAT('%', #{reqVO.typeName}, '%')
            </if>
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<BizType>, @Param("reqVO") reqVO: BizTypeAdminPageReqVO
    ): Page<BizTypeAdminRespVO>
}