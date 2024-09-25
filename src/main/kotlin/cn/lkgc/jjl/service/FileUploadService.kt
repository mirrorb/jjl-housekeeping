package cn.lkgc.jjl.service

import cn.lkgc.jjl.framework.pojo.EditorResult
import org.springframework.web.multipart.MultipartFile

interface FileUploadService {
    fun saveAvatar(file: MultipartFile): String
    fun saveEditorImage(file: MultipartFile): EditorResult
    fun saveEditorVideo(file: MultipartFile): EditorResult
    fun uploadPicture(file: MultipartFile): String
    fun uploadCarousel(file: MultipartFile): String
}