package com.example.demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.*
import java.util.*
import kotlin.Exception

@ControllerAdvice
@RestController
class CustomizeResponseEntityExceptionHandler :ResponseEntityExceptionHandler(){

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex:Exception,request:WebRequest) :
            ResponseEntity<ExceptionsResponse>{
            val exceptionsResponse = ExceptionsResponse(
                Date(),
                ex.message,
                request.getDescription(false)
            )
        return ResponseEntity<ExceptionsResponse>(exceptionsResponse,HttpStatus.INTERNAL_SERVER_ERROR)
    }
    @ExceptionHandler(UnsupportedMathOperationException::class)
    fun handleBadRequestExceptions(ex:Exception,request:WebRequest) :
            ResponseEntity<ExceptionsResponse>{
        val exceptionsResponse = ExceptionsResponse(
            Date(),
            ex.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionsResponse>(exceptionsResponse,HttpStatus.BAD_REQUEST)
    }
}