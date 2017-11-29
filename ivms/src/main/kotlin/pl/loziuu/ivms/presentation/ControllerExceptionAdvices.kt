package pl.loziuu.ivms.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class ControllerExceptionAdvices {

    @ExceptionHandler(*arrayOf(NoSuchElementException::class))
    fun handleNoSuchElement(): ResponseEntity<Any>
            = RestException("Resource does not exists!", 404).toResponseEntity()
}

class RestException(val content: String = "", val code: Int = 200) {
    fun toResponseEntity(): ResponseEntity<Any> {
        return ResponseEntity(this, HttpStatus.valueOf(code))
    }
}
