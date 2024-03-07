package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.contourgara.examination2gara.application.UpdateBookParam

data class UpdateBookRequest(
  @JsonProperty("title") val title: String?,
  @JsonProperty("author") val author: String?,
  @JsonProperty("publisher") val publisher: String?,
  @JsonProperty("price") val price: Int?
) {
  fun toParam(id: String): UpdateBookParam {
    return UpdateBookParam(id, title, author, publisher, price)
  }
}
