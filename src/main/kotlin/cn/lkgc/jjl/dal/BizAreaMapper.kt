package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizarea.BizAreaAdminRespVO
import cn.lkgc.jjl.entity.BizArea
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BizAreaMapper : BaseMapper<BizArea> {
    @Select("""
        <script>
        SELECT * FROM biz_area
        <where>
            <if test='reqVO.areaName != null and reqVO.areaName != ""'>
                AND area_name LIKE CONCAT('%', #{reqVO.areaName}, '%')
            </if>
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<BizArea>, @Param("reqVO") reqVO: BizAreaAdminPageReqVO
    ): Page<BizAreaAdminRespVO>
}