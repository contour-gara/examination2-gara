package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId

data class CreateBookParam(val title: String, val author: String, val publisher: String, val price: Int) {
  fun toModel(): Book {
    return Book(BookId.ofEmptyId(), title, author, publisher, price)
  }
}
