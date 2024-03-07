package org.contourgara.examination2gara.infrastructure

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.junit.jupiter.api.Test

class BookEntityTest {
  @Test
  fun `ドメインモデルへ変換できる`() {
    // setup
    val sut: BookEntity = BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    // execute
    val actual: Book = sut.toModel()

    // assert
    val expected: Book = Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
