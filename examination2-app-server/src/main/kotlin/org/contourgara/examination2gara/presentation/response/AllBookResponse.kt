package org.contourgara.examination2gara.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty

data class AllBookResponse(@JsonProperty("books") val bookResponses: List<BookResponse>)
