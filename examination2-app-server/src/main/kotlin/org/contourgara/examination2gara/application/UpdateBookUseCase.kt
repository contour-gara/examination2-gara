package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.application.exception.NotFoundBookException
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateBookUseCase(
  private val bookRepository: BookRepository
) {
  fun execute(updateBookParam: UpdateBookParam): Unit {
    val book: Book = bookRepository.findById(updateBookParam.id) ?: throw NotFoundBookException(updateBookParam.id)

    bookRepository.update(updateBookParam.toModel(book))
  }
}
