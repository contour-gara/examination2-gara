package org.contourgara.examination2gara.presentation

import io.restassured.module.mockmvc.RestAssuredMockMvc.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.OK
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class BooksControllerTest {
  @Autowired
  lateinit var mockMvc: MockMvc

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
//  @CsvSource(delimiter = '|', textBlock = """# id | title | author | publisher | price
//    1 | テスト駆動開発 | Kent Beck | オーム社 | 3080
//    2 | アジャイルサムライ | Jonathan Rasmusson | オーム社 | 2860""")
  @CsvSource(delimiter = '|', value = [
    // id | title           | author             | publisher | price
    "   1 | テスト駆動開発    | Kent Beck          | オーム社    | 3080",
    "   2 | アジャイルサムライ | Jonathan Rasmusson | オーム社    | 2860"
                                      ])
  fun `ID 検索の場合、レスポンスコード 200 と本情報が返る`(
    id : String, title : String, author: String, publisher: String, price: Int
  ) {
    // execute & assert
    given()
      .`when`()
      .get("/v1/books/$id")
      .then()
      .status(OK)
      .body("id", equalTo(id))
      .body("title", equalTo(title))
      .body("author", equalTo(author))
      .body("publisher", equalTo(publisher))
      .body("price", equalTo(price))
  }
}
