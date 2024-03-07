package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.springframework.stereotype.Service

@Service
class CreateBookUseCase {
  fun execute(createBookParam: CreateBookParam): Book {
    return Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)
  }
}
