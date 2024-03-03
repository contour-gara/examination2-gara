package org.contourgara.examination2gara.domain

interface BookRepository {
  fun findAll(): List<Book>
}
