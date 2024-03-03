package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
  private val bookMapper: BookMapper
): BookRepository {
  override fun findAll(): List<Book> {
    return bookMapper.findAll().map {
      it.toModel()
    }
  }
}
