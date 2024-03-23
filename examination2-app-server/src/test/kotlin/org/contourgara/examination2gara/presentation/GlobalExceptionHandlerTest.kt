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
    fun `全ての項目がnullの場合、レスポンスコード 400 とエラーメッセージと詳細のリストが返る`() {
      // execute & assert
      given()
        .contentType(APPLICATION_JSON)
        .body("{\"title\": null,\"author\": null,\"publisher\": null,\"price\": null}")
        .`when`()
        .post("/v1/books")
        .then()
        .status(BAD_REQUEST)
        .body("code", equalTo("0002"))
        .body("message", equalTo("request validation error is occurred."))
        .body("details", hasSize<String>(4))
    }
  }
}
