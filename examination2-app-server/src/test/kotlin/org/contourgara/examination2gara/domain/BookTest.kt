package org.contourgara.examination2gara.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BookTest {
  @ParameterizedTest(name = "{4} が {5} の場合、例外が飛ぶ")
  @MethodSource("createData")
  fun `ガード条件に違反した場合、例外が飛ぶ`(title: String, author: String, publisher: String, price: Int,
                                            field: String, value: String, message: String) {
    // execute & assert
    assertThatThrownBy {
      Book(BookId(1), title, author, publisher, price)
    }
      .isInstanceOf(IllegalArgumentException::class.java)
      .hasMessage(String.format(message, field, field, value))
  }

  companion object {
    private const val MESSAGE_NOT_BLANK: String = "%s must not be blank. [%s=%s]"
    private const val MESSAGE_RANGE: String = "%s length must be between 1 and 100. [%s=%s]"
    private var stringOverHundred: String = "a".repeat(101)

    @JvmStatic
    fun createData(): Stream<Arguments> {
      return Stream.of(
        Arguments.of(" ", "Kent Beck", "オーム社", 3080, "title", " ", MESSAGE_NOT_BLANK),
        Arguments.of("テスト駆動開発", " ", "オーム社", 3080, "author", " ", MESSAGE_NOT_BLANK),
        Arguments.of("テスト駆動開発", "Kent Beck", " ", 3080, "publisher", " ", MESSAGE_NOT_BLANK),
        Arguments.of(stringOverHundred, "Kent Beck", "オーム社", 3080, "title", stringOverHundred, MESSAGE_RANGE),
        Arguments.of("テスト駆動開発", stringOverHundred, "オーム社", 3080, "author", stringOverHundred, MESSAGE_RANGE),
        Arguments.of("テスト駆動開発", "Kent Beck", stringOverHundred, 3080, "publisher", stringOverHundred, MESSAGE_RANGE),
      )
    }
  }
}
