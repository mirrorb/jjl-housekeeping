package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselAdminPageReqVO
import cn.lkgc.jjl.controller.vo.globalcarousel.GlobalCarouselAdminRespVO
import cn.lkgc.jjl.entity.GlobalCarousel
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GlobalCarouselMapper : BaseMapper<GlobalCarousel> {
    @Select("""
        <script>
        SELECT * FROM global_carousel
        <where>
            <if test='reqVO.name != null and reqVO.name != ""'>
                AND name LIKE CONCAT('%', #{reqVO.name}, '%')
            </if>
        </where>
        ORDER BY sort
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<GlobalCarousel>, @Param("reqVO") reqVO: GlobalCarouselAdminPageReqVO
    ): Page<GlobalCarouselAdminRespVO>
}