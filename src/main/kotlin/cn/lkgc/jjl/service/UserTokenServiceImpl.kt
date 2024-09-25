package cn.lkgc.jjl.service

import cn.lkgc.jjl.dal.UserTokenMapper
import cn.lkgc.jjl.entity.UserI
import cn.lkgc.jjl.entity.UserToken
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserTokenServiceImpl(
    private val userTokenMapper: UserTokenMapper
) : UserTokenService {

    override fun generateToken(user: UserI): String {
        val token = UUID.randomUUID().toString()
        val genTime = LocalDateTime.now()
        val expiredTime = genTime.plusMinutes(30)
        userTokenMapper.insert(UserToken(null, user.tableName, user.username, user.role, token, genTime, expiredTime))
        return token
    }

    override fun refreshExpireTime(id: Int) {
        userTokenMapper.updateById(UserToken(id = id, expiredTime = LocalDateTime.now().plusMinutes(30)))
    }

    override fun getValidByToken(token: String?): UserToken? =
        userTokenMapper.selectOne(KtQueryWrapper(UserToken()).eq(UserToken::token, token).gt(UserToken::expiredTime, LocalDateTime.now()))

}