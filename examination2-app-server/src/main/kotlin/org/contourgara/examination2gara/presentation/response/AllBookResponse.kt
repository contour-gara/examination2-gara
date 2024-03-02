package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.domain.Book

data class AllBookResponse(@JsonProperty("books") val bookResponses: List<BookResponse>) {
  companion object {
    fun of(books: List<Book>): AllBookResponse {
      return AllBookResponse(books.map {
        BookResponse.of(it)
      })
    }
  }
}
