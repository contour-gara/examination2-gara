package org.contourgara.examination2gara

import org.springframework.core.io.DefaultResourceLoader
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object TestUtils {
  fun readFrom(path: String): String {
    val resource = DefaultResourceLoader().getResource("classpath:$path")
    val reader = InputStreamReader(resource.inputStream, StandardCharsets.UTF_8)
    return FileCopyUtils.copyToString(reader)
  }
}
