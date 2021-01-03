package com.hong9lol.Todo.handler

import com.hong9lol.Todo.controller.api.todo.TodoApiController
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import com.hong9lol.Todo.model.http.Error
import com.hong9lol.Todo.model.http.ErrorResponse
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import java.time.LocalDateTime

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach { errorObject ->
            Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }.apply {
                errors.add(this)
            }
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpmethod = request.method
            this.message = ""
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.badRequest().body(errorResponse)
    }
}