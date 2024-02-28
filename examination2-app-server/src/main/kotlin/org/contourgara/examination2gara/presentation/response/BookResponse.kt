package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BookResponse(
  @JsonProperty("id") val id: String,
  @JsonProperty("title") val title: String,
  @JsonProperty("author") val author: String,
  @JsonProperty("publisher") val publisher: String,
  @JsonProperty("price") val price: Int
)
