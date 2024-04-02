package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.contourgara.examination2gara.application.CreateBookParam
import org.hibernate.validator.constraints.Length

data class CreateBookRequest(
  @JsonProperty("title")
  @field:Length(max = 100)
  @field:NotBlank
  val title: String?,

  @JsonProperty("author")
  @field:Length(max = 100)
  @field:NotBlank
  val author: String?,

  @JsonProperty("publisher")
  @field:Length(max = 100)
  @field:NotBlank
  val publisher: String?,

  @JsonProperty("price")
  @field:NotNull
  val price: Int?
) {
  fun toParam(): CreateBookParam {
    return CreateBookParam(title!!, author!!, publisher!!, price!!)
  }
}
