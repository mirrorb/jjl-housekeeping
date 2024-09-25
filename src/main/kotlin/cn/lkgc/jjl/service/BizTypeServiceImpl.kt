package cn.lkgc.jjl.service

import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminCreateReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminPageReqVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminRespVO
import cn.lkgc.jjl.controller.vo.biztype.BizTypeAdminUpdateReqVO
import cn.lkgc.jjl.convert.BizTypeConvert
import cn.lkgc.jjl.dal.BizTypeMapper
import cn.lkgc.jjl.entity.BizType
import cn.lkgc.jjl.framework.pojo.PageResult
import cn.lkgc.jjl.framework.utils.PageResultUtils
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BizTypeServiceImpl(
    private val bizTypeMapper: BizTypeMapper, private val bizTypeConvert: BizTypeConvert

) : BizTypeService {
    
    override fun adminPage(reqVO: BizTypeAdminPageReqVO): PageResult<BizTypeAdminRespVO> =
        PageResultUtils.from(
            bizTypeMapper.selectAdminPage(Page(reqVO.current, reqVO.pageSize), reqVO)
        )

    override fun adminDetail(typeName: String): BizTypeAdminRespVO {
        val doo = bizTypeMapper.selectById(typeName)
        return bizTypeConvert.convert(doo)
    }

    override fun adminUpdate(reqVO: BizTypeAdminUpdateReqVO) {
        // TODO: 当前没有更新需求
//        val doo = bizTypeConvert.convert(reqVO)
//        bizTypeMapper.updateById(doo)
    }

    override fun adminCreate(reqVO: BizTypeAdminCreateReqVO) {
        if (bizTypeMapper.selectById(reqVO.typeName) != null) {
            throw exception("服务类别名已存在")
        }
        val doo = bizTypeConvert.convert(reqVO)
        doo.createTime = LocalDateTime.now()
        bizTypeMapper.insert(doo)
    }

    override fun adminDelete(typeName: String) {
        bizTypeMapper.deleteById(typeName)
    }

    override fun list(): List<String> = bizTypeMapper.selectList(KtQueryWrapper(BizType())).map { it.typeName ?: "" }

}