package de.flower.rmt2.rest.controller

import de.flower.rmt2.core.service.HelloService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HelloWorldController(val helloService: HelloService) {


    @GetMapping(path = ["/hello"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayHello(): String {
//        return "{ \"message\": \"Hello World!\" }"
//        return "Hello World!"
        return helloService.sayHello()
    }
}