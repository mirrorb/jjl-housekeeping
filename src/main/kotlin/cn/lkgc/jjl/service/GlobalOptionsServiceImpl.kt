package cn.lkgc.jjl.service

import cn.lkgc.jjl.dal.GlobalOptionsMapper
import cn.lkgc.jjl.entity.GlobalOption
import cn.lkgc.jjl.enums.GlobalOptionsEnums
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.stereotype.Service

@Service
class GlobalOptionsServiceImpl(
    private val globalOptionsMapper: GlobalOptionsMapper
) : GlobalOptionsService {

    override fun listOption(item: GlobalOptionsEnums): List<String> = globalOptionsMapper.selectList(
        KtQueryWrapper(GlobalOption()).eq(GlobalOption::item, item.item).orderByAsc(GlobalOption::sort)
    ).map { it.option ?: "" }

}