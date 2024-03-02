package org.contourgara.examination2gara.presentation.response

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.junit.jupiter.api.Test

class BookResponseTest {
  @Test
  fun `ドメインオブジェクトからインスタンスを生成できる`() {
    // setup
    val book: Book = Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    // execute
    val actual: BookResponse = BookResponse.of(book)

    // assert
    val expected: BookResponse = BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
