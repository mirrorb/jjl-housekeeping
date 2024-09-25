package cn.lkgc.jjl.enums

enum class UserTypeEnums(val role: String, val tableName: String) {
    ADMIN("管理员", "user_admin"),
    EMPLOYEE("雇员", "user_employee"),
    CUSTOMER("客户", "user_customer"),
}