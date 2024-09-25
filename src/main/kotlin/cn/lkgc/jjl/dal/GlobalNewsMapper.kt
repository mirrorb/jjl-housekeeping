package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsAdminPageReqVO
import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsAdminRespVO
import cn.lkgc.jjl.controller.vo.globalnews.GlobalNewsFrontRespVO
import cn.lkgc.jjl.entity.GlobalNews
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GlobalNewsMapper : BaseMapper<GlobalNews> {
    @Select("""
        <script>
        SELECT * FROM global_news
        <where>
            <if test='reqVO.newsName != null and reqVO.newsName != "" '>
                AND news_name LIKE CONCAT('%', #{reqVO.newsName}, '%')
            </if>
        </where>
        ORDER BY create_time
    </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<GlobalNews>, @Param("reqVO") reqVO: GlobalNewsAdminPageReqVO
    ): Page<GlobalNewsAdminRespVO>

    @Select("""
        SELECT * FROM global_news
        ORDER BY create_time
    """
    )
    fun selectFrontPage(@Param("page") page: Page<GlobalNews>): Page<GlobalNewsFrontRespVO>
}