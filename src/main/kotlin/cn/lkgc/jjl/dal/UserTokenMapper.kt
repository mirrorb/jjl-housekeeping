package cn.lkgc.jjl.dal

import cn.lkgc.jjl.entity.UserToken
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserTokenMapper : BaseMapper<UserToken> {
}