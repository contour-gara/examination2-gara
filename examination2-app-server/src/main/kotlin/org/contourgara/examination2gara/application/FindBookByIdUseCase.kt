package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Service

@Service
class FindBookByIdUseCase(
  private val bookRepository: BookRepository
) {
  fun execute(id: String): Book {
    val book: Book? = bookRepository.findById(id)
    return book ?: throw NotFoundBookException(id)
  }
}
