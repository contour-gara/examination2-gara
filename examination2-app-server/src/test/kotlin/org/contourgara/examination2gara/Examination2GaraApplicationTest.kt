package org.contourgara.examination2gara

import org.assertj.core.api.Assertions.*
import org.contourgara.examination2gara.presentation.BooksController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Examination2GaraApplicationTest {
  @Autowired
  lateinit var booksController: BooksController

  @Test
  fun contextLoads() {
    assertThat(booksController).isNotNull()
  }
}
