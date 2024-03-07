package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.contourgara.examination2gara.domain.BookRepository
import org.contourgara.examination2gara.infrastructure.exception.QueryExecutionFailException
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
    val bookEntity: BookEntity = BookEntity.of(book)

    val count: Int = bookMapper.create(bookEntity)

    checkQueryExecution(count, bookEntity)

    return bookEntity.toModel()
  }

  override fun update(book: Book): Unit {
    val count: Int = bookMapper.update(BookEntity.of(book))

    checkQueryExecution(count, BookEntity.of(book))
  }

  private fun checkQueryExecution(count: Int, bookEntity: BookEntity) {
    if (count != 1)
      throw QueryExecutionFailException(bookEntity.id, bookEntity.title, bookEntity.author, bookEntity.publisher, bookEntity.price)
  }
}
