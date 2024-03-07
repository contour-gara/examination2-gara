package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.application.exception.NotFoundBookException
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteBookUseCase(
  private val bookRepository: BookRepository
) {
  fun execute(id: String): Unit {
    bookRepository.findById(id) ?: throw NotFoundBookException(id)

    bookRepository.delete(id)
  }
}
