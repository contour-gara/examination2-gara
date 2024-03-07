package org.contourgara.examination2gara.domain

/**
 * Book は、本情報のドメインオブジェクトです。
 *
 * @param id 本の ID。
 * @param title 本のタイトル。
 * @param author 本の著者。
 * @param publisher 本の出版社。
 * @param price 本の値段。
 */
data class Book(val id: Long, val title: String, val author: String, val publisher: String, val price: Int) {
  init {
    require((id >= 1) && (id <= 9999999999)) {"id must be a value between 1 and 9999999999. [id=$id]"}
    require(title.isNotBlank()) {"title must not be blank. [title=$title]"}
    require(title.length <= 100) {"title length must be between 1 and 100. [title=$title]"}
    require(author.isNotBlank()) {"author must not be blank. [author=$author]"}
    require(author.length <= 100) {"author length must be between 1 and 100. [author=$author]"}
    require(publisher.isNotBlank()) {"publisher must not be blank. [publisher=$publisher]"}
    require(publisher.length <= 100) {"publisher length must be between 1 and 100. [publisher=$publisher]"}
  }
}
