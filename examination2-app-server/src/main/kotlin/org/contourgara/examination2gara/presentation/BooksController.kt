package org.contourgara.examination2gara.presentation

import org.contourgara.examination2gara.application.CreateBookUseCase
import org.contourgara.examination2gara.application.FindAllBooksUseCase
import org.contourgara.examination2gara.application.FindBookByIdUseCase
import org.contourgara.examination2gara.presentation.request.CreateBookRequest
import org.contourgara.examination2gara.presentation.request.UpdateBookRequest
import org.contourgara.examination2gara.presentation.response.AllBookResponse
import org.contourgara.examination2gara.presentation.response.BookResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
class BooksController(
  private val findAllBooksUseCase: FindAllBooksUseCase,
  private val findBookByIdUseCase: FindBookByIdUseCase,
  private val createBookUseCase: CreateBookUseCase,
) {
  /**
   * ルートエンドポイントです。
   * Get メソッドでアクセスされた場合、レスポンスコード 200 を返します。
   */
  @GetMapping("/")
  @ResponseStatus(OK)
  fun root(): Unit {
    // 何もしません
  }

  /**
   * 全件検索のエンドポイントです。
   * Get メソッドでアクセスされた場合、レスポンスコード 200 を返します。
   *
   * @return 本情報のリストオブジェクト。
   */
  @GetMapping("/v1/books")
  @ResponseStatus(OK)
  fun findAll(): AllBookResponse {
    return AllBookResponse.of(findAllBooksUseCase.execute())
  }

  /**
   * ID 検索のエンドポイントです。
   * Get メソッドでアクセスされた場合、レスポンスコード 200 を返します。
   *
   * @param id 検索する本 ID。
   * @return 本情報のリストオブジェクト。
   */
  @GetMapping("/v1/books/{id}")
  @ResponseStatus(OK)
  fun findById(@PathVariable id: String): BookResponse {
    return BookResponse.of(findBookByIdUseCase.execute(id))
  }

  /**
   * 登録のエンドポイントです。
   * Post メソッドでアクセスされた場合、レスポンスコード 201 を返します。
   *
   * @param createBookRequest 登録したい本情報。
   * @return ヘッダーの Location に登録した本情報のエンドポイントがあります。
   */
  @PostMapping("/v1/books")
  @ResponseStatus(CREATED)
  fun create(@RequestBody createBookRequest: CreateBookRequest): ResponseEntity<Unit> {
    val book = createBookUseCase.execute(createBookRequest.toParam())

    val uri: URI = UriComponentsBuilder
      .fromUriString(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString())
      .path("/${book.id.id}")
      .build()
      .toUri()

    return ResponseEntity.created(uri).build()
  }

  @PatchMapping("/v1/books/{id}")
  @ResponseStatus(NO_CONTENT)
  fun update(@RequestBody updateBookRequest: UpdateBookRequest): Unit {
    // 何もしません
  }

  @DeleteMapping("/v1/books/{id}")
  @ResponseStatus(NO_CONTENT)
  fun delete(): Unit {
    // 何もしません
  }
}
