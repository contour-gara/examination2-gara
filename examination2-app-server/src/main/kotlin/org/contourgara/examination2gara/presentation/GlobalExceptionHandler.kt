package org.contourgara.examination2gara.presentation

import org.contourgara.examination2gara.presentation.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
  companion object {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
    val details = e.fieldErrors.stream()
      .map {
        log.error("request validation error is occurred. [${it.field} = ${it.rejectedValue}: ${it.defaultMessage}]")
        "${it.field} ${it.defaultMessage}"
      }
      .toList()

    return ErrorResponse(
      "0002",
      "request validation error is occurred.",
      details
    )
  }
}
