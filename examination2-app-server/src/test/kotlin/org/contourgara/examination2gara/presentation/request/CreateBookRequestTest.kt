package org.contourgara.examination2gara.presentation.request

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.application.CreateBookParam
import org.junit.jupiter.api.Test

class CreateBookRequestTest {
  @Test
  fun `パラムオブジェクトに変換できる`() {
    // setup
    val sut = CreateBookRequest("テスト駆動開発", "Kent Beck", "オーム社", 3080)

    // execute
    val actual = sut.toParam()

    // assert
    val expected = CreateBookParam("テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
