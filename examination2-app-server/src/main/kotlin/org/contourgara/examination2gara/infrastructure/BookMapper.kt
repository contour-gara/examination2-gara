package org.contourgara.examination2gara.infrastructure

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
  @Select("SELECT id, title, author, publisher, price FROM books")
  fun findAll(): List<BookEntity>
}
