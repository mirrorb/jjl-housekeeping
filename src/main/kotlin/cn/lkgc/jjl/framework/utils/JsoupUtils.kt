package cn.lkgc.jjl.framework.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object JsoupUtils {

    private fun parseHtml(html: String): Document {
        return Jsoup.parse(html)
    }

    fun getTextFromHtml(html: String): String {
        val document = parseHtml(html)
        return document.text()
    }

    fun getTitleFromHtml(html: String): String {
        val document = parseHtml(html)
        return document.title()
    }
}
