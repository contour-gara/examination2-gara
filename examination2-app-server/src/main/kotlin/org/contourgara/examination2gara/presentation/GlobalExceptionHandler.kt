package org.contourgara.examination2gara.presentation

import org.contourgara.examination2gara.application.exception.NotFoundBookException
import org.contourgara.examination2gara.presentation.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.Exception

@RestControllerAdvice
class GlobalExceptionHandler {
  companion object {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(BAD_REQUEST)
  fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
    val details = e.fieldErrors.stream()
      .map {
        log.warn("request validation error is occurred. [${it.field} = ${it.rejectedValue}: ${it.defaultMessage}]", e)
        "${it.field} ${it.defaultMessage}"
      }
      .toList()

    return ErrorResponse(
      "0002",
      "request validation error is occurred.",
      details
    )
  }

  @ExceptionHandler(NotFoundBookException::class)
  @ResponseStatus(BAD_REQUEST)
  fun handleNotFoundBookException(e: NotFoundBookException): ErrorResponse {
    log.warn("specified employee [id = ${e.id}] is not found.", e)
    return ErrorResponse(
      "0003",
      "specified book [id = ${e.id}] is not found.",
      emptyList()
    )
  }

  @ExceptionHandler(Exception::class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  fun handleException(e: Exception): ErrorResponse {
    log.error("unexpected exception has occurred. [${e.message}]")
    return ErrorResponse(
      "0000",
      "unexpected exception has occurred. [${e.message}]",
      emptyList()
    )
  }
}
