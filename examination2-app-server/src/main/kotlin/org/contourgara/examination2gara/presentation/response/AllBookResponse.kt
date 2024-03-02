package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.domain.Book

/**
 * AllBookResponse は、複数の本情報を持ったレスポンスオブジェクトです。
 */
data class AllBookResponse(@JsonProperty("books") val bookResponses: List<BookResponse>) {
  companion object {
    /**
     * AllBookResponse を初期化します。
     *
     * @param books 本情報のリスト。
     */
    fun of(books: List<Book>): AllBookResponse {
      return AllBookResponse(books.map {
        BookResponse.of(it)
      })
    }
  }
}
