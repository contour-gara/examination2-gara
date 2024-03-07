package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId

/**
 * BookEntity は、本情報をデータベースとのマッピングをするためのクラスです。
 */
data class BookEntity(val id: Long, val title: String, val author: String, val publisher: String, val price:Int) {
  companion object {
    @JvmStatic
    fun of(book: Book): BookEntity {
      return BookEntity(book.id.id, book.title, book.author, book.publisher, book.price)
    }
  }

  /**
   * ドメインオブジェクトへと変換します。
   *
   * @return 本情報
   */
  fun toModel(): Book {
    return Book(BookId(id), title, author, publisher, price)
  }
}
