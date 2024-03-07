package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Repository

/**
 * BookRepositoryImpl は、本情報をデータベースに永続化するクラスです。
 */
@Repository
class BookRepositoryImpl(
  private val bookMapper: BookMapper
): BookRepository {
  override fun findAll(): List<Book> {
    return bookMapper.findAll().map {
      it.toModel()
    }
  }

  override fun findById(id: String): Book? {
    return bookMapper.findById(id.toLong())?.toModel()
  }

  override fun create(book: Book): Book {
    return Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)
  }
}
