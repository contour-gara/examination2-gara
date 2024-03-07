package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateBookParamTest {
  @ParameterizedTest
  @MethodSource("dataset")
  fun `ドメインモデルに変換できる`(sut: UpdateBookParam, expected: Book) {
    // execute
    val actual = sut.toModel(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))

    // assert
    assertThat(actual).isEqualTo(expected)
  }

  companion object {
    @JvmStatic
    fun dataset(): Stream<Arguments> {
      return Stream.of(
        Arguments.of(UpdateBookParam("1", "テスト駆動設計", "Kent", "あああ", 198), Book(BookId(1), "テスト駆動設計", "Kent", "あああ", 198)),
        Arguments.of(UpdateBookParam("1", null, null, null, null), Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)),
      )
    }
  }
}
