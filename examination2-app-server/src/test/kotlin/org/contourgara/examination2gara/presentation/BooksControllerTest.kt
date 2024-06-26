package org.contourgara.examination2gara.presentation

import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc
import org.contourgara.examination2gara.application.CreateBookParam
import org.contourgara.examination2gara.application.CreateBookUseCase
import org.contourgara.examination2gara.application.DeleteBookUseCase
import org.contourgara.examination2gara.application.FindAllBooksUseCase
import org.contourgara.examination2gara.application.FindBookByIdUseCase
import org.contourgara.examination2gara.application.UpdateBookUseCase
import org.contourgara.examination2gara.domain.Book
import org.contourgara.examination2gara.domain.BookId
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class BooksControllerTest {
  @Autowired
  lateinit var mockMvc: MockMvc

  @MockBean
  lateinit var findAllBooksUseCase: FindAllBooksUseCase

  @MockBean
  lateinit var findBookByIdUseCase: FindBookByIdUseCase

  @MockBean
  lateinit var createBookUseCase: CreateBookUseCase

  @MockBean
  lateinit var updateBookUseCase: UpdateBookUseCase

  @MockBean
  lateinit var deleteBookUseCase: DeleteBookUseCase

  @BeforeEach
  fun setUp() {
    mockMvc(mockMvc)
  }

  @Test
  fun `ルートにアクセスした場合、レスポンスコード 200 が返る`() {
    // execute & assert
    given()
      .`when`()
      .get("/")
      .then()
      .status(OK)
  }

  @Test
  fun `全件検索の場合、レスポンスコード 200 と本情報のリストが返る`() {
    // setup
    doReturn(
      listOf(
        Book(BookId(1), "テスト駆動開発", "Kent Beck", "オーム社", 3080),
        Book(BookId(2), "アジャイルサムライ", "Jonathan Rasmusson", "オーム社", 2860)
      )
    ).`when`(findAllBooksUseCase).execute()

    // execute & assert
    given()
      .`when`()
      .get("/v1/books")
      .then()
      .status(OK)
      .body("books[0].id", equalTo("1"))
      .body("books[0].title", equalTo("テスト駆動開発"))
      .body("books[0].author", equalTo("Kent Beck"))
      .body("books[0].publisher", equalTo("オーム社"))
      .body("books[0].price", equalTo(3080))
      .body("books[1].id", equalTo("2"))
      .body("books[1].title", equalTo("アジャイルサムライ"))
      .body("books[1].author", equalTo("Jonathan Rasmusson"))
      .body("books[1].publisher", equalTo("オーム社"))
      .body("books[1].price", equalTo(2860))
  }

  @ParameterizedTest
  @CsvSource(
    delimiter = '|', value = [
      // id | title           | author             | publisher | price
      "   1 | テスト駆動開発    | Kent Beck          | オーム社    | 3080",
      "   2 | アジャイルサムライ | Jonathan Rasmusson | オーム社    | 2860"
    ]
  )
  fun `ID 検索の場合、レスポンスコード 200 と本情報が返る`(
    id: Long, title: String, author: String, publisher: String, price: Int
  ) {
    // setup
    doReturn(Book(BookId(id), title, author, publisher, price))
      .`when`(findBookByIdUseCase).execute(id.toString())

    // execute & assert
    given()
      .`when`()
      .get("/v1/books/$id")
      .then()
      .status(OK)
      .body("id", equalTo(id.toString()))
      .body("title", equalTo(title))
      .body("author", equalTo(author))
      .body("publisher", equalTo(publisher))
      .body("price", equalTo(price))
  }

  @ParameterizedTest
  @CsvSource(
    delimiter = '|', value = [
      // id | title           | author             | publisher | price
      "   1 | テスト駆動開発    | Kent Beck          | オーム社    | 3080",
      "   2 | アジャイルサムライ | Jonathan Rasmusson | オーム社    | 2860"
    ]
  )
  fun `登録の場合、レスポンスコード 201 とヘッダーにロケーションが返る`(
    id: Long, title: String, author: String, publisher: String, price: Int
  ) {
    // setup
    doReturn(Book(BookId(id), title, author, publisher, price))
      .`when`(createBookUseCase).execute(CreateBookParam(title, author, publisher, price))

    // execute & assert
    given()
      .contentType(APPLICATION_JSON_VALUE)
      .body("{\"title\": \"$title\", \"author\": \"$author\", \"publisher\": \"$publisher\", \"price\": $price}")
      .`when`()
      .post("/v1/books")
      .then()
      .status(CREATED)
      .header("Location", equalTo("http://localhost/v1/books/$id"))
  }

  @Test
  fun `本情報を更新した場合、レスポンスコード 204 が返る`() {
    // execute & assert
    given()
      .contentType(APPLICATION_JSON_VALUE)
      .body("{\"author\": \"Uncle Bob\"}")
      .`when`()
      .patch("/v1/books/1")
      .then()
      .status(NO_CONTENT)
  }

  @Test
  fun `本情報を削除した場合、レスポンスコード 204 が返る`() {
    // execute & assert
    given()
      .`when`()
      .delete("/v1/books/1")
      .then()
      .status(NO_CONTENT)
  }
}
