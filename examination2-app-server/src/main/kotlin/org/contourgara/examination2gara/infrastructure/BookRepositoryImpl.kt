package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
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
    return bookMapper.findById(id)?.toModel()
  }
}
