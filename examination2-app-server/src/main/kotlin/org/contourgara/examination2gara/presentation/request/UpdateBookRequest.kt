package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.application.UpdateBookParam
import org.hibernate.validator.constraints.Length

data class UpdateBookRequest(
  @JsonProperty("title")
  @field:Length(max = 100)
  val title: String?,

  @JsonProperty("author")
  @field:Length(max = 100)
  val author: String?,

  @JsonProperty("publisher")
  @field:Length(max = 100)
  val publisher: String?,

  @JsonProperty("price")
  val price: Int?
) {
  fun toParam(id: String): UpdateBookParam {
    return UpdateBookParam(id, title, author, publisher, price)
  }
}
