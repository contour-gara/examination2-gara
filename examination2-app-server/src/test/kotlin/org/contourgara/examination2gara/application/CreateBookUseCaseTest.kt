package org.contourgara.examination2gara.application

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.contourgara.examination2gara.domain.BookRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CreateBookUseCaseTest {
  @InjectMocks
  lateinit var sut: CreateBookUseCase

  @Mock
  lateinit var bookRepository: BookRepository

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `本を登録できた場合、本情報が返る`() {
    // setup
    doReturn(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      .`when`(bookRepository).create(Book(BookId.ofEmptyId(), "テスト駆動開発", "Kent Beck", "オーム社", 3080))

    // execute
    val actual = sut.execute(CreateBookParam("テスト駆動開発", "Kent Beck", "オーム社", 3080))

    // assert
    val expected = Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)

    assertThat(actual).isEqualTo(expected)
  }
}
