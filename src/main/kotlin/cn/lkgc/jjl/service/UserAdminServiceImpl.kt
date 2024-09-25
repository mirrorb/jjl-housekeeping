package cn.lkgc.jjl.service

import cn.lkgc.jjl.dal.UserAdminMapper
import cn.lkgc.jjl.entity.UserAdmin
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserAdminServiceImpl(
    private val userAdminMapper: UserAdminMapper
) : UserAdminService {

    override fun getByUsername(username: String): UserAdmin? {
        return userAdminMapper.selectOne(
            KtQueryWrapper(UserAdmin())
                .eq(UserAdmin::username, username)
        )
    }

    override fun updateLoginTime(user: UserAdmin) {
        user.loginTime = LocalDateTime.now()
        userAdminMapper.updateById(user)
    }

    override fun update(doo: UserAdmin) {
        userAdminMapper.updateById(doo)
    }

}