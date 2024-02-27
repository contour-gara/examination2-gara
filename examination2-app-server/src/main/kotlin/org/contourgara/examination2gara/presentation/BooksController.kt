package org.contourgara.examination2gara.presentation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * BooksController は、本情報のエンドポイントです。
 */
@RestController
class BooksController {
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  fun root(): Unit {
//    何もしません
  }
}
