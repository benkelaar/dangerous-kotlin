package com.bol.dangerous.extensions

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.MalformedURLException

@ControllerAdvice
@ResponseBody
class ExceptionHandlers {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MalformedURLException::class)
    fun handleMalformedURLException() = ErrorBody("Malformed URL")

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotEnoughStockException::class)
    fun handleNotEnoughStockException(e: NotEnoughStockException) = e.asError()

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(UntrustedSourceException::class)
    fun handleUntrustedSourceException(e: UntrustedSourceException) = e.asError()

    private fun Exception.asError() =
        ErrorBody(checkNotNull(message) { "Exception $this should have a message" })
}

data class ErrorBody(val message: String)