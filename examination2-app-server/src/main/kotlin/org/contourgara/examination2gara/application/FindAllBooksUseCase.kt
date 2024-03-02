package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.springframework.stereotype.Service

@Service
class FindAllBooksUseCase {
  fun execute(): List<Book> {
    return listOf(
      Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )
  }
}
