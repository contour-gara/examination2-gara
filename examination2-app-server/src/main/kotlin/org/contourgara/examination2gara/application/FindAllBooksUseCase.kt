package org.contourgara.examination2gara.application

import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.springframework.stereotype.Service

/**
 * FindAllBooksUseCase は、本情報の全件検索のロジックです。
 */
@Service
class FindAllBooksUseCase(
  private val bookRepository: BookRepository
) {
  /**
   * 本情報の全件検索を実行します。
   *
   * @return 本情報のリスト
   */
  fun execute(): List<Book> {
    return bookRepository.findAll()
  }
}
