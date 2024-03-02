package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.domain.Book

data class BookResponse(
  @JsonProperty("id") val id: String,
  @JsonProperty("title") val title: String,
  @JsonProperty("author") val author: String,
  @JsonProperty("publisher") val publisher: String,
  @JsonProperty("price") val price: Int
) {
  companion object {
    fun of(book: Book): BookResponse {
      return BookResponse(book.id, book.title, book.author, book.publisher, book.price)
    }
  }
}
