package org.contourgara.examination2gara.infrastructure

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl: BookRepository {
  override fun findAll(): List<Book> {
    return listOf(
      Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )
  }
}
