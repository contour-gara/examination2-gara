package org.contourgara.examination2gara.presentation

import io.restassured.module.mockmvc.RestAssuredMockMvc.*
import org.contourgara.examination2gara.application.CreateBookUseCase
import org.contourgara.examination2gara.application.DeleteBookUseCase
import org.contourgara.examination2gara.application.FindAllBooksUseCase
import org.contourgara.examination2gara.application.FindBookByIdUseCase
import org.contourgara.examination2gara.application.UpdateBookUseCase
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus.BAD_REQUEST
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

  @MockBean
  lateinit var updateBookUseCase: UpdateBookUseCase

  @MockBean
  lateinit var deleteBookUseCase: DeleteBookUseCase

  @BeforeEach
  fun setUp() {
    mockMvc(mockMvc)
  }

  @Nested
  inner class `新規登録で` {
    @Test
    fun `全ての項目が null の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body("{\"title\": null, \"author\": null, \"publisher\": null, \"price\": null}")
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
        .body("{\"title\": null, \"author\": \"Kent Beck\", \"publisher\": \"オーム社\", \"price\": 3080}")
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
        .body("{\"title\": \"\", \"author\": \"\", \"publisher\": \"\", \"price\": 1000}")
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
        .body("{\"title\": \"\", \"author\": \"Kent Beck\", \"publisher\": \"オーム社\", \"price\": 3080}")
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
        .body("{\"title\": \"${"a".repeat(101)}\", \"author\":\"${"a".repeat(101)}\", \"publisher\": \"${"a".repeat(101)}\", \"price\": 3080}")
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
        .body("{\"title\": \"${"a".repeat(101)}\", \"author\": \"Kent Beck\", \"publisher\": \"オーム社\", \"price\": 3080}")
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
  inner class `更新` {
    @Test
    fun `タイトルと著者が 100 文字以上の場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body("{\"title\": \"${"a".repeat(101)}\", \"author\":\"${"a".repeat(101)}\"}")
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
        .body("{\"title\": \"${"a".repeat(101)}\"}")
        .`when`()
        .patch("/v1/books/1")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details[0]", equalTo("title length must be between 0 and 100"))
    }
  }
}
