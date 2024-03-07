package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateBookUseCase(
  private val bookRepository: BookRepository
) {
  fun execute(createBookParam: CreateBookParam): Book {
    return bookRepository.create(createBookParam.toModel())
  }
}
