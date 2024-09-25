package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.bizcomment.BizCommentPageReqVO
import cn.lkgc.jjl.entity.BizComment
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BizCommentMapper : BaseMapper<BizComment> {
    @Select("""
        <script>
        SELECT * FROM biz_comment
        <where>
            <if test='reqVO.target != null'>
                AND target = #{reqVO.target}
            </if>
            <if test='reqVO.targetType != null'>
                AND target_type = #{reqVO.targetType}
            </if>
            AND parent_id IS NULL
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectPage(@Param("page") page: Page<BizComment>, @Param("reqVO") reqVO: BizCommentPageReqVO): Page<BizComment>
}