package pl.loziuu.ivms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class IvmsApplication

fun main(args: Array<String>) {
    SpringApplication.run(IvmsApplication::class.java, *args)
}
