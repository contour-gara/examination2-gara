package org.contourgara.examination2gara.infrastructure

import org.apache.ibatis.annotations.*

@Mapper
interface BookMapper {
  @Select("SELECT id, title, author, publisher, price FROM books")
  fun findAll(): List<BookEntity>

  @Select("SELECT id, title, author, publisher, price FROM books WHERE id = #{id}")
  fun findById(id: Long): BookEntity?

  @Insert("INSERT INTO books (id, title, author, publisher, price) VALUES (nextval('BOOK_ID_SEQ'), #{title}, #{author}, #{publisher}, #{price})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  fun create(bookEntity: BookEntity): Int

  @Update("UPDATE books SET title = #{title}, author = #{author}, publisher = #{publisher}, price = #{price} WHERE id = #{id}")
  fun update(bookEntity: BookEntity): Int

  @Delete("DELETE from books WHERE id = #{id}")
  fun delete(id: Long): Int
}
