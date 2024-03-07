package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class CreateBookUseCaseTest {
  @InjectMocks
  lateinit var sut: CreateBookUseCase

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `本を登録できた場合、本情報が返る`() {
    // setup
    val createBookParam = CreateBookParam("テスト駆動開発", "Kent Beck", "オーム社", 3080)
    // execute
    val actual = sut.execute(createBookParam)

    // assert
    val expected = Book(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
