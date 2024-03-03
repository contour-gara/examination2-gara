package org.contourgara.examination2gara.infrastructure

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.connection.ConnectionHolder
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.junit5.api.DBRider
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.DriverManager

@DBRider
@DBUnit
@SpringBootTest
class BookMapperTest {
  companion object {
    private const val DB_URL: String = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false"
    private const val DB_USERNAME: String = "sa"
    private const val DB_PASSWORD: String = "sa"

    private val connectionHolder: ConnectionHolder = ConnectionHolder {
      DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)
    }
  }

  @Autowired
  lateinit var sut: BookMapper

  @Nested
  inner class 全件検索 {
    @DataSet(value = ["/datasets/setup/0-book.yml"])
    @ExpectedDataSet(value = ["/datasets/expected/0-book.yml"])
    @Test
    fun `テーブルが空の場合、空のリストが返る`() {
      // execute
      val actual: List<BookEntity> = sut.findAll()

      // assert
      val expected: List<BookEntity> = emptyList()

      assertThat(actual).isEqualTo(expected)
    }

    @DataSet(value = ["datasets/setup/1-book.yml"])
    @ExpectedDataSet(value = ["/datasets/expected/1-book.yml"])
    @Test
    fun `テーブルに 1 件データがある場合、本情報のリストが返る`() {
      // execute
      val actual: List<BookEntity> = sut.findAll()

      // assert
      val expected: List<BookEntity> = listOf(
        BookEntity("1", "テスト駆動開発", "Kent Beck", "オーム社", 3080)
      )

      assertThat(actual).isEqualTo(expected)
    }
  }
}
