package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.application.CreateBookParam

data class CreateBookRequest(
  @JsonProperty("title") val title: String,
  @JsonProperty("author") val author: String,
  @JsonProperty("publisher") val publisher: String,
  @JsonProperty("price") val price: Int
) {
  fun toParam(): CreateBookParam {
    return CreateBookParam(title, author, publisher, price)
  }
}
