package org.contourgara.examination2gara.infrastructure

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class BookRepositoryImplTest {
  @InjectMocks
  lateinit var sut: BookRepositoryImpl

  @Mock
  lateinit var bookMapper: BookMapper

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun `全件検索の場合、本情報のリストを返す`() {
    // setup
    doReturn(listOf(
      BookEntity("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      BookEntity("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )).`when`(bookMapper).findAll()

    // execute
    val actual: List<Book> = sut.findAll()

    // assert
    val expected: List<Book> = listOf(
      Book("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book("2", "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )

    assertThat(actual).isEqualTo(expected)
  }
}
