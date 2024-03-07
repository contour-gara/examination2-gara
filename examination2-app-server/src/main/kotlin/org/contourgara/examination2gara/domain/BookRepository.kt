package org.contourgara.examination2gara.domain

/**
 * BookRepository は、本情報を永続化するためのインターフェースです。
 */
interface BookRepository {
  /**
   * 本方法を全件取得します。
   *
   * @return 本情報のリスト。
   */
  fun findAll(): List<Book>

  fun findById(id: String): Book?

  fun create(book: Book): Book
}
