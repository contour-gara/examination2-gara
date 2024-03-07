package org.contourgara.examination2gara.integration

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.configuration.Orthography
import com.github.database.rider.core.api.connection.ConnectionHolder
import com.github.database.rider.junit5.api.DBRider
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import io.restassured.RestAssured.*
import java.sql.DriverManager
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE



@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class BooksIT {
  companion object {
    private const val DB_URL: String = "jdbc:postgresql://localhost:5432/sample"
    private const val DB_USER: String = "user"
    private const val DB_PASSWORD: String = "password"

    private val connectionHolder: ConnectionHolder = ConnectionHolder {
      DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
    }

    @BeforeAll
    fun initialize() {
      baseURI = "http://localhost:8080"
    }
  }

  @Test
  fun `ルートにアクセスした場合、レスポンスコード 200 が返る`() {
    // execute & assert
    given()
      .get()
      .then()
      .statusCode(OK.value())
  }

  @DataSet("datasets/setup/2-book.yml")
  @ExpectedDataSet("datasets/expected/2-book.yml")
  @Test
  fun `全件検索した場合、レスポンスコード 200 と本情報のリストが返る`() {
    // execute & assert
    given()
      .get("/v1/books")
      .then()
      .statusCode(OK.value())
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

  @DataSet("datasets/setup/1-book.yml")
  @ExpectedDataSet("datasets/expected/1-book.yml")
  @Test
  fun `ID 検索できた場合、レスポンスコード 200 と本情報が返る`() {
    // execute & assert
    given()
      .get("/v1/books/1")
      .then()
      .statusCode(OK.value())
      .body("id", equalTo("1"))
      .body("title", equalTo("テスト駆動開発"))
      .body("author", equalTo("Kent Beck"))
      .body("publisher", equalTo("オーム社"))
      .body("price", equalTo(3080))
  }

  @DataSet("datasets/setup/0-book.yml")
  @ExpectedDataSet("datasets/expected/1-book.yml")
  @Test
  fun `登録できた場合、レスポンスコード 201 とヘッダーにロケーションが返る`() {
    // execute & assert
    given()
      .contentType(APPLICATION_JSON_VALUE)
      .body("{\"title\": \"テスト駆動開発\",\"author\": \"Kent Beck\",\"publisher\": \"オーム社\",\"price\": 3080}")
      .`when`()
      .post("/v1/books")
      .then()
      .statusCode(CREATED.value())
      .header("Location", equalTo("http://localhost:8080/v1/books/1"))
  }

  @DataSet("datasets/setup/1-book.yml")
  @ExpectedDataSet("datasets/expected/1-book-update.yml")
  @Test
  fun `更新できた場合、レスポンスコード 204 が返る`() {
    // execute & assert
    given()
      .contentType(APPLICATION_JSON_VALUE)
      .body("{\"author\": \"Uncle Bob\"}")
      .`when`()
      .patch("/v1/books/1")
      .then()
      .statusCode(NO_CONTENT.value())
  }

  @DataSet("datasets/setup/1-book.yml")
  @ExpectedDataSet("datasets/expected/0-book.yml")
  @Test
  fun `削除できた場合、レスポンスコード 204 が返る`() {
    // execute & assert
    given()
      .`when`()
      .delete("/v1/books/1")
      .then()
      .statusCode(NO_CONTENT.value())
  }
}
