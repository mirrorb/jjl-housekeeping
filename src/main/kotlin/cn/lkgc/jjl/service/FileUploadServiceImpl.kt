package cn.lkgc.jjl.service

import cn.lkgc.jjl.framework.config.ResourcesProperties
import cn.lkgc.jjl.framework.config.ServerProperties
import cn.lkgc.jjl.framework.pojo.EditorResult
import cn.lkgc.jjl.framework.utils.ServiceExceptionUtils.exception
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.util.*

@EnableConfigurationProperties(ResourcesProperties::class, ServerProperties::class)
@Service
class FileUploadServiceImpl(
    private val resourcesProperties: ResourcesProperties,
    private val serverProperties: ServerProperties
) : FileUploadService {

    @Suppress("DuplicatedCode")
    override fun saveAvatar(file: MultipartFile): String {
        if (file.isEmpty) {
            throw exception("文件未上传")
        }
        val extName = file.originalFilename?.split(".")?.last() ?: "jpg"
        val uploadDir: String = resourcesProperties.avatarPath
        val uploadFile = File(uploadDir, UUID.randomUUID().toString() + ".$extName")
        try {
            file.transferTo(uploadFile)
            val address =
                serverProperties.host + if (!serverProperties.port.isNullOrBlank()) ":${serverProperties.port}" else ""
            val fileUrl = address + "/resources/avatar/" + uploadFile.name
            return fileUrl
        } catch (e: IOException) {
            e.printStackTrace()
            throw exception("文件上传失败")
        }
    }

    override fun saveEditorImage(file: MultipartFile): EditorResult {
        if (file.isEmpty) {
            return EditorResult.error(500, "文件未上传")
        }
        val extName = file.originalFilename?.split(".")?.last() ?: "jpg"
        val uploadDir: String = resourcesProperties.editorImagePath
        val uploadFile = File(uploadDir, UUID.randomUUID().toString() + ".$extName")
        try {
            file.transferTo(uploadFile)
            val address =
                serverProperties.host + if (!serverProperties.port.isNullOrBlank()) ":${serverProperties.port}" else ""
            val fileUrl = address + "/resources/editor/image/" + uploadFile.name
            return EditorResult.success(fileUrl)
        } catch (e: IOException) {
            e.printStackTrace()
            return EditorResult.error(500, "文件上传失败")
        }
    }

    override fun saveEditorVideo(file: MultipartFile): EditorResult {
        if (file.isEmpty) {
            return EditorResult.error(500, "文件未上传")
        }
        val extName = file.originalFilename?.split(".")?.last() ?: "mp4"
        val uploadDir: String = resourcesProperties.editorVideoPath
        val uploadFile = File(uploadDir, UUID.randomUUID().toString() + ".$extName")
        try {
            file.transferTo(uploadFile)
            val address =
                serverProperties.host + if (!serverProperties.port.isNullOrBlank()) ":${serverProperties.port}" else ""
            val fileUrl = address + "/resources/editor/video/" + uploadFile.name
            return EditorResult.success(fileUrl)
        } catch (e: IOException) {
            e.printStackTrace()
            return EditorResult.error(500, "文件上传失败")
        }
    }

    @Suppress("DuplicatedCode")
    override fun uploadPicture(file: MultipartFile): String {
        if (file.isEmpty) {
            throw exception("文件未上传")
        }
        val extName = file.originalFilename?.split(".")?.last()?: "jpg"
        val uploadDir: String = resourcesProperties.picturePath
        val uploadFile = File(uploadDir, UUID.randomUUID().toString() + ".$extName")
        try {
            file.transferTo(uploadFile)
            val address =
                serverProperties.host + if (!serverProperties.port.isNullOrBlank()) ":${serverProperties.port}" else ""
            val fileUrl = address + "/resources/picture/" + uploadFile.name
            return fileUrl
        } catch (e: IOException) {
            e.printStackTrace()
            throw exception("文件上传失败")
        }
    }

    @Suppress("DuplicatedCode")
    override fun uploadCarousel(file: MultipartFile): String {
        if (file.isEmpty) {
            throw exception("文件未上传")
        }
        val extName = file.originalFilename?.split(".")?.last()?: "jpg"
        val uploadDir: String = resourcesProperties.carouselPath
        val uploadFile = File(uploadDir, UUID.randomUUID().toString() + ".$extName")
        try {
            file.transferTo(uploadFile)
            val address =
                serverProperties.host + if (!serverProperties.port.isNullOrBlank()) ":${serverProperties.port}" else ""
            val fileUrl = address + "/resources/carousel/" + uploadFile.name
            return fileUrl
        } catch (e: IOException) {
            e.printStackTrace()
            throw exception("文件上传失败")
        }
    }

}