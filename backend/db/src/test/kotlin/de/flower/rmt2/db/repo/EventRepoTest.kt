package de.flower.rmt2.db.repo

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class EventRepoTest  : AbstractRepoTest() {

    @Autowired
    lateinit var eventRepo: EventRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val events = eventRepo.findAll()
        log.info(events.toString());
    }

}