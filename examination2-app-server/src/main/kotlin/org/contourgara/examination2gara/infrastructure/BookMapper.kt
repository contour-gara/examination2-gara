package org.contourgara.examination2gara.infrastructure

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
  @Select("SELECT id, title, author, publisher, price FROM books")
  fun findAll(): List<BookEntity>

  @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
  fun findById(id: Long): BookEntity?
}
