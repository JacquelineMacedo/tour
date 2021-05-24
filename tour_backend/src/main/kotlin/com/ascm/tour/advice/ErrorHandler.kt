package com.ascm.tour.advice

import com.ascm.tour.exception.PromocaoException
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage( "Json ERROR", exception.message ?: "invalidJson"), BAD_REQUEST)
    }

    @ExceptionHandler(PromocaoException::class)
    fun promocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Promocao Não encontrada", exception.message !!), HttpStatus.NOT_FOUND)
    }
}