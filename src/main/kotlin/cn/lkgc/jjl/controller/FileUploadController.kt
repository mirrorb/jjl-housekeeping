package cn.lkgc.jjl.controller

import cn.lkgc.jjl.framework.anno.Permit
import cn.lkgc.jjl.framework.pojo.CommonResult
import cn.lkgc.jjl.framework.pojo.CommonResult.Companion.success
import cn.lkgc.jjl.framework.pojo.EditorResult
import cn.lkgc.jjl.service.FileUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

/**
 * 文件上传控制器
 */
@RequestMapping("/file-upload")
@RestController
class FileUploadController(
    private val fileUploadService: FileUploadService
) {

    @Permit
    @PostMapping("/avatar")
    fun uploadAvatar(file: MultipartFile): CommonResult<String> = success(fileUploadService.saveAvatar(file))

    @PostMapping("/editor/image")
    fun uploadEditorImage(file: MultipartFile): EditorResult = fileUploadService.saveEditorImage(file)

    @PostMapping("/editor/video")
    fun uploadEditorVideo(file: MultipartFile): EditorResult = fileUploadService.saveEditorVideo(file)

    @PostMapping("/picture")
    fun uploadPicture(file: MultipartFile): CommonResult<String> = success(fileUploadService.uploadPicture(file))

    @PostMapping("/carousel")
    fun uploadCarousel(file: MultipartFile): CommonResult<String> = success(fileUploadService.uploadCarousel(file))

}