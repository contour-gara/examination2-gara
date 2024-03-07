package org.contourgara.examination2gara.infrastructure

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.contourgara.examination2gara.infrastructure.exception.QueryExecutionFailException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
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
      BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      BookEntity(2, "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )).`when`(bookMapper).findAll()

    // execute
    val actual: List<Book> = sut.findAll()

    // assert
    val expected: List<Book> = listOf(
      Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080),
      Book(BookId(2), "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
    )

    assertThat(actual).isEqualTo(expected)
  }

  @Nested
  inner class `ID検索` {
    @Test
    fun `検索できた場合、本情報が返る`() {
      // setup
      doReturn(BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))
        .`when`(bookMapper).findById(1)

      // execute
      val actual: Book? = sut.findById("1")

      // assert
      val expected: Book = Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)

      assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `検索できなかった場合、null`() {
      // setup
      doReturn(null)
        .`when`(bookMapper).findById(2)

      // execute
      val actual: Book? = sut.findById("2")

      // assert
      assertThat(actual).isNull()
    }
  }

  @Nested
  inner class `登録` {
    @Test
    fun `登録できた場合、本情報が返る`() {
      // setup
      doReturn(1).`when`(bookMapper).create(BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))

      // execute
      val actual = sut.create(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))

      // assert
      val expected = Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)

      assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `登録できなかった場合、例外を投げる`() {
      // setup
      doReturn(0).`when`(bookMapper).create(BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))

      // execute & assert
      assertThatThrownBy {
        sut.create(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      }
        .isInstanceOf(QueryExecutionFailException::class.java)
        .extracting("title")
        .isEqualTo("テスト駆動開発")
    }
  }

  @Nested
  inner class `更新` {
    @Test
    fun `更新できた場合、何も起きない`() {
      // setup
      doReturn(1).`when`(bookMapper).update(BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))

      // execute & assert
      assertThatCode { sut.update(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080)) }.doesNotThrowAnyException()
    }

    @Test
    fun `更新できなかった場合、例外を投げる`() {
      // setup
      doReturn(0).`when`(bookMapper).update(BookEntity(1, "テスト駆動開発", "Kent Beck", "オーム社", 3080))

      // execute & assert
      assertThatThrownBy {
        sut.update(Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080))
      }
        .isInstanceOf(QueryExecutionFailException::class.java)
        .extracting("title")
        .isEqualTo("テスト駆動開発")
    }
  }
}
