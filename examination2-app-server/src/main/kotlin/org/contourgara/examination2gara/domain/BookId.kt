package org.contourgara.examination2gara.domain

data class BookId(private var _id: Long) {
  init {
    require((_id >= 1) && (_id <= 9999999999)) {"id must be a value between 1 and 9999999999. [id=$id]"}
  }

  private constructor(): this(1) {
    _id = 0
  }

  companion object {
    fun ofEmptyId(): BookId {
      return BookId()
    }
  }

  val id: Long get() = _id
}
