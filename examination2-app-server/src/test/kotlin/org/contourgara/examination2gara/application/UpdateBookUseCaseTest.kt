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

class UpdateBookUseCaseTest {
  @InjectMocks
  lateinit var sut: UpdateBookUseCase

  @Mock
  lateinit var bookRepository: BookRepository

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `更新できる場合、リポジトリの更新が呼ばれる`() {
    // setup
    doReturn(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      .`when`(bookRepository).findById("1")

    // execute
    sut.execute(UpdateBookParam("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080))

    // assert
    verify(bookRepository, times(1)).update(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
  }

  @Test
  fun `更新できない場合、例外を投げる`() {
    // setup
    doReturn(null)
      .`when`(bookRepository).findById("1")

    // execute & assert
    assertThatThrownBy {
      sut.execute(UpdateBookParam("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080))
    }
      .isInstanceOf(NotFoundBookException::class.java)
      .extracting("id")
      .isEqualTo("1")
  }
}
