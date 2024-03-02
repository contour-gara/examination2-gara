package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class FindAllBooksUseCaseTest {
  @InjectMocks
  lateinit var sut: FindAllBooksUseCase

  @BeforeEach
  fun setup() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `実行した場合、すべての本を返す`() {
    // execute
    val actual: List<Book> = sut.execute()

    // assert
    val expected: List<Book> = listOf(
      Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )

    assertThat(actual).isEqualTo(expected)
  }
}