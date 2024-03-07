package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FindBookByIdUseCaseTest {
  @InjectMocks
  lateinit var sut: FindBookByIdUseCase

  @Mock
  lateinit var bookRepository: BookRepository

  @BeforeEach
  fun setup() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `本 ID が存在した場合、本情報を返す`() {
    // setup
    doReturn(Book(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      .`when`(bookRepository).findById("1")

    // execute
    val actual: Book = sut.execute("1")

    // assert
    val expected: Book = Book(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `本 ID が存在しない場合、本が見つからないという例外を返す`() {
    // setup
    doThrow(NotFoundBookException("2"))
      .`when`(bookRepository).findById("2")

    // execute & assert
    assertThatThrownBy {
      sut.execute("2")
    }
      .isInstanceOf(NotFoundBookException::class.java)
      .extracting("id")
      .isEqualTo("2")
  }
}
