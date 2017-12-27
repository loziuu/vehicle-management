package pl.loziuu.ivms.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import pl.loziuu.ivms.ddd.DomainValidationException
import java.util.*

@ControllerAdvice
class ControllerExceptionAdvices {

    @ExceptionHandler(*arrayOf(NoSuchElementException::class))
    fun handleNoSuchElement(): ResponseEntity<Any>
            = RestResponse("Resource does not exists!", 404).toResponseEntity()

    @ExceptionHandler(*arrayOf(DomainValidationException::class))
    fun handleValidationException(exception: DomainValidationException): ResponseEntity<Any> =
        RestResponse(exception.message.orEmpty(), 400).toResponseEntity()
}

class RestResponse(val content: String = "", val code: Int = 200) {
    fun toResponseEntity(): ResponseEntity<Any> {
        return ResponseEntity(this, HttpStatus.valueOf(code))
    }
}


