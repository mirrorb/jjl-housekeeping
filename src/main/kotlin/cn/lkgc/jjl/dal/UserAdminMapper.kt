package cn.lkgc.jjl.dal

import cn.lkgc.jjl.entity.UserAdmin
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserAdminMapper : BaseMapper<UserAdmin> {
}