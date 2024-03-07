package org.contourgara.examination2gara.presentation.request

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.application.UpdateBookParam
import org.junit.jupiter.api.Test

class UpdateBookRequestTest {
  @Test
  fun `Param オブジェクトに変換できる`() {
    // setup
    val sut = UpdateBookRequest("テスト駆動開発", "Kent Beck", "オーム社", 3080)

    // execute
    val actual = sut.toParam("1")

    // assert
    val expected = UpdateBookParam("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
