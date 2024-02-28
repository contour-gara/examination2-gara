package org.contourgara.examination2gara.presentation

import org.contourgara.examination2gara.presentation.response.AllBookResponse
import org.contourgara.examination2gara.presentation.response.BookResponse
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * BooksController は、本情報のエンドポイントです。
 */
@RestController
class BooksController {
  @GetMapping("/")
  @ResponseStatus(OK)
  fun root(): Unit {
//    何もしません
  }

  @GetMapping("/v1/books")
  @ResponseStatus(OK)
  fun findAll(): AllBookResponse {
    return AllBookResponse(listOf(
      BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      BookResponse("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    ))
  }

  @GetMapping("/v1/books/{id}")
  @ResponseStatus(OK)
  fun findById(@PathVariable id: String): BookResponse {
    if ("1".equals(id))
      return BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)
    return BookResponse("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
  }
}
