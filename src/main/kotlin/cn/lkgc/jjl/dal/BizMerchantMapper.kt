package cn.lkgc.jjl.dal

import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantAdminPageReqVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantAdminRespVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantFrontPageReqVO
import cn.lkgc.jjl.controller.vo.bizmerchant.BizMerchantFrontRespVO
import cn.lkgc.jjl.entity.BizMerchant
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BizMerchantMapper : BaseMapper<BizMerchant> {
    @Select("""
        <script>
        SELECT * FROM biz_merchant
        <where>
            <if test='reqVO.merchantName != null and reqVO.merchantName != ""'>
                AND merchant_name LIKE CONCAT('%', #{reqVO.merchantName}, '%')
            </if>
            <if test='reqVO.telephone != null and reqVO.telephone != ""'>
                AND telephone LIKE CONCAT('%', #{reqVO.telephone}, '%')
            </if>
        </where>
        ORDER BY create_time
        </script>
    """)
    fun selectAdminPage(
        @Param("page") page: Page<BizMerchant>, @Param("reqVO") reqVO: BizMerchantAdminPageReqVO
    ): Page<BizMerchantAdminRespVO>

    @Select("""
        SELECT * FROM biz_merchant
        ORDER BY create_time
    """
    )
    fun selectFrontPage(@Param("page") page: Page<BizMerchant>): Page<BizMerchantFrontRespVO>
}