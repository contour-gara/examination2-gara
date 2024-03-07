package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId

data class UpdateBookParam(val id: String, val title: String?, val author: String?, val publisher: String?, val price: Int?) {
  fun toModel(book: Book): Book {
    return Book(
      BookId(id.toLong()),
      title ?: book.title,
      author ?: book.author,
      publisher ?: book.publisher,
      price ?: book.price
    )
  }
}
