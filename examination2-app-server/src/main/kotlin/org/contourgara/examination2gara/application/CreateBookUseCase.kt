package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.springframework.stereotype.Service

@Service
class CreateBookUseCase {
  fun execute(createBookParam: CreateBookParam): Book {
    return Book(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080)
  }
}
