package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.junit.jupiter.api.Test

class CreateBookParamTest {
  @Test
  fun `モデルクラスに変換できる`() {
    // setup
    val sut = CreateBookParam("テスト駆動開発", "Kent Beck", "オーム社", 3080)

    // execute
    val actual = sut.toModel()

    // assert
    val expected = Book(BookId.ofEmptyId(), "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
