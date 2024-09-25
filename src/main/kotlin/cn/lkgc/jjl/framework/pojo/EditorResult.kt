package cn.lkgc.jjl.framework.pojo

import lombok.Data

@Data
class EditorResult(
    val errno: Int,
    val message: String,
    val data: Map<String, String>?) {
    companion object {
        fun success(url: String): EditorResult {
            return EditorResult(0, "成功", java.util.Map.of("url", url))
        }

        fun error(code: Int, msg: String): EditorResult {
            return EditorResult(code, msg, null)
        }
    }
}
