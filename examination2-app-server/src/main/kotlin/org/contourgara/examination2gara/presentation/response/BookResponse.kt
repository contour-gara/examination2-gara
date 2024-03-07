package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.domain.Book

/**
 * BookResponse は、本情報のレスポンスオブジェクトです。
 */
data class BookResponse(
  @JsonProperty("id") val id: String,
  @JsonProperty("title") val title: String,
  @JsonProperty("author") val author: String,
  @JsonProperty("publisher") val publisher: String,
  @JsonProperty("price") val price: Int
) {
  companion object {
    /**
     * BookResponse を初期化します。
     *
     * @param book 本情報。
     */
    fun of(book: Book): BookResponse {
      return BookResponse(book.id.id.toString(), book.title, book.author, book.publisher, book.price)
    }
  }
}
