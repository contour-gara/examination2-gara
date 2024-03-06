package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.springframework.stereotype.Service

@Service
class FindBookByIdUseCase {
  fun execute(id: String): Book {
    var book: Book? = null
    if (id == "1")
      book = Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)
    return book ?: throw NotFoundBookException(id)
  }
}
