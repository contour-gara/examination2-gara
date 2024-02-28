package org.contourgara.examination2gara.presentation.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateBookRequest(
  @JsonProperty("title") val title: String?,
  @JsonProperty("author") val author: String?,
  @JsonProperty("publisher") val publisher: String?,
  @JsonProperty("price") val price: Int?
)
