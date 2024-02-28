package org.contourgara.examination2gara.presentation

import org.contourgara.examination2gara.presentation.request.CreateBookRequest
import org.contourgara.examination2gara.presentation.response.AllBookResponse
import org.contourgara.examination2gara.presentation.response.BookResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

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

  @PostMapping("/v1/books")
  @ResponseStatus(CREATED)
  fun create(@RequestBody createBookRequest: CreateBookRequest): ResponseEntity<Unit> {
    val uri: URI = UriComponentsBuilder
      .fromUriString(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString())
      .path("/1")
      .build()
      .toUri()

    return ResponseEntity.created(uri).build()
  }
}
