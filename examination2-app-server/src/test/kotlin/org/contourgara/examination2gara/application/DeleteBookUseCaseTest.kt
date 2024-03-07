package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.application.exception.NotFoundBookException
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.contourgara.examination2gara.domain.BookRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DeleteBookUseCaseTest {
  @InjectMocks
  lateinit var sut: DeleteBookUseCase

  @Mock
  lateinit var bookRepository: BookRepository

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `削除する本 ID が存在する場合、リポジトリを呼び出す`() {
    // setup
    doReturn(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      .`when`(bookRepository).findById("1")

    // execute
    sut.execute("1")

    // assert
    verify(bookRepository, times(1)).delete("1")
  }

  @Test
  fun `削除する本 ID が存在しない場合、例外を投げる`() {
    // setup
    doReturn(null).`when`(bookRepository).findById("1")

    // execute & assert
    assertThatThrownBy {
      sut.execute("1")
    }
      .isInstanceOf(NotFoundBookException::class.java)
      .extracting("id")
      .isEqualTo("1")
  }
}
