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
import org.springframework.http.HttpStatus.OK


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
}
