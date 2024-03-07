package org.contourgara.examination2gara.presentation.response

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.junit.jupiter.api.Test

class AllBookResponseTest {
  @Test
  fun `Book のリストからインスタンスを生成できる`() {
    // setup
    val books: List<Book> = listOf(
      Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book(BookId(2), "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )

    // execute
    val actual: AllBookResponse = AllBookResponse.of(books)

    // assert
    val expected: AllBookResponse = AllBookResponse(listOf(
      BookResponse("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      BookResponse("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    ))

    assertThat(actual).isEqualTo(expected)
  }
}
