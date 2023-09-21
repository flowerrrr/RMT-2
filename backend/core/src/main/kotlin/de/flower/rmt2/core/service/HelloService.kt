package de.flower.rmt2.core.service

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun sayHello(): String {
        return "{ \"message\": \"Hello World!\" }"
    }
}