package org.contourgara.examination2gara.presentation

import io.restassured.module.mockmvc.RestAssuredMockMvc.*
import org.contourgara.examination2gara.TestUtils.readFrom
import org.contourgara.examination2gara.application.CreateBookUseCase
import org.contourgara.examination2gara.application.DeleteBookUseCase
import org.contourgara.examination2gara.application.FindAllBooksUseCase
import org.contourgara.examination2gara.application.FindBookByIdUseCase
import org.contourgara.examination2gara.application.UpdateBookUseCase
import org.contourgara.examination2gara.application.exception.NotFoundBookException
import org.contourgara.examination2gara.infrastructure.BookMapper
import org.contourgara.examination2gara.infrastructure.BookRepositoryImpl
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class GlobalExceptionHandlerTest {
  @Autowired
  lateinit var mockMvc: MockMvc

  @MockBean
  lateinit var findAllBooksUseCase: FindAllBooksUseCase

  @MockBean
  lateinit var findBookByIdUseCase: FindBookByIdUseCase

  @MockBean
  lateinit var createBookUseCase: CreateBookUseCase

  @SpyBean
  lateinit var updateBookUseCase: UpdateBookUseCase

  @SpyBean
  lateinit var deleteBookUseCase: DeleteBookUseCase

  @SpyBean
  lateinit var bookRepositoryImpl: BookRepositoryImpl

  @MockBean
  lateinit var bookMapper: BookMapper

  @BeforeEach
  fun setUp() {
    mockMvc(mockMvc)
  }

  @Nested
  inner class `検索で` {
    @Test
    fun `本が存在しない場合、レスポンスコード 400 とエラーメッセージと空の詳細リストが返る`() {
      // setup
      doThrow(NotFoundBookException("1")).`when`(findBookByIdUseCase).execute("1")

      // execute & assert
      given()
        .`when`()
        .get("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0003"))
        .body("message", equalTo("specified book [id = 1] is not found."))
        .body("details", hasSize<String>(0))
    }
  }

  @Nested
  inner class `新規登録で` {
    @Test
    fun `全ての項目が null の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-null.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details", hasSize<String>(4))
    }

    @Test
    fun `タイトルが null の場合、レスポンスコード 400 とエラーメッセージと想定されたメッセージのリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-null-only-title.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("title must not be blank"))
    }

    @Test
    fun `値段以外が空文字の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-empty-without-price.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details", hasSize<String>(3))
    }

    @Test
    fun `タイトルが空文字の場合、レスポンスコード 400 とエラーメッセージと想定されたメッセージのリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-empty-only-title.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("title must not be blank"))
    }

    @Test
    fun `値段以外が 100 文字以上の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-over100-without-price.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details", hasSize<String>(3))
    }

    @Test
    fun `タイトルが 100 文字以上の場合、レスポンスコード 400 とエラーメッセージと想定されたメッセージのリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("create-over100-only-title.json"))
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("title length must be between 0 and 100"))
    }
  }

  @Nested
  inner class `更新で` {
    @Test
    fun `タイトルと著者が 100 文字以上の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("update-over100-title-author.json"))
        .`when`()
        .patch("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details", hasSize<String>(2))
    }

    @Test
    fun `タイトルが 100 文字以上の場合、レスポンスコード 400 とエラーメッセージと想定されたメッセージのリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("update-over100-title.json"))
        .`when`()
        .patch("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("title length must be between 0 and 100"))
    }

    @Test
    fun `本が存在しない場合、レスポンスコード 400 とエラーメッセージと空の詳細リストが返る`() {
      // setup
      doThrow(NotFoundBookException("1")).`when`(findBookByIdUseCase).execute("1")

      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body(readFrom("update.json"))
        .`when`()
        .patch("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0003"))
        .body("message", equalTo("specified book [id = 1] is not found."))
        .body("details", hasSize<String>(0))
    }
  }

  @Nested
  inner class `削除で` {
    @Test
    fun `本が存在しない場合、レスポンスコード 400 とエラーメッセージと空の詳細リストが返る`() {
      // setup
      doThrow(NotFoundBookException("1")).`when`(findBookByIdUseCase).execute("1")

      // execute & assert
      given()
        .`when`()
        .delete("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0003"))
        .body("message", equalTo("specified book [id = 1] is not found."))
        .body("details", hasSize<String>(0))
    }
  }

  @Test
  fun `予期しない例外が発生した場合、レスポンスコード 500 とエラーメッセージと空の詳細リストが返る`() {
    // setup
    doThrow(NullPointerException()).`when`(findBookByIdUseCase).execute("1")

    // execute & assert
    given()
      .`when`()
      .get("/v1/books/1")
      .then()
      .status(INTERNAL_SERVER_ERROR)
      .body("code", equalTo("0000"))
      .body("message", equalTo("unexpected exception has occurred. [null]"))
      .body("details", hasSize<String>(0))
  }
}
