package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
  @JsonProperty("code") val code: String,
  @JsonProperty("message") val message: String,
  @JsonProperty("details") val details: List<String>,
)
