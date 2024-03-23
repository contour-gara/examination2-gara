package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import org.contourgara.examination2gara.application.CreateBookParam

data class CreateBookRequest(
  @JsonProperty("title")
  @field:NotNull
  val title: String?,

  @JsonProperty("author")
  @field:NotNull
  val author: String?,

  @JsonProperty("publisher")
  @field:NotNull
  val publisher: String?,

  @JsonProperty("price")
  @field:NotNull
  val price: Int?
) {
  fun toParam(): CreateBookParam {
    return CreateBookParam(title!!, author!!, publisher!!, price!!)
  }
}
