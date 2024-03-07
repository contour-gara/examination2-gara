package org.contourgara.examination2gara.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BookIdTest {
  @ParameterizedTest(name = "id が {0} の場合、例外が飛ぶ")
  @CsvSource(value = [
    "0",
    "10000000000"
  ])
  fun `ガード条件に違反した場合、例外が飛ぶ`(id: Long) {
    // execute & assert
    assertThatThrownBy {
      BookId(id)
    }
      .isInstanceOf(IllegalArgumentException::class.java)
      .hasMessage(String.format("id must be a value between 1 and 9999999999. [id=%s]", id))
  }

  @Test
  fun `ID 0 のインスタンスを生成できる`() {
    // execute
    val actual = BookId.ofEmptyId().id

    // assert
    val expected = 0L

    assertThat(actual).isEqualTo(expected)
  }
}
