package org.contourgara.examination2gara.presentation

import io.restassured.module.mockmvc.RestAssuredMockMvc.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
  fun `ルートにアクセスした場合、レスポンスコード200が返る`() {
    // execute & assert
    given()
      .`when`()
      .get("/")
      .then()
      .status(OK)
  }
}
