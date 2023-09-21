package de.flower.rmt2.db.mysql

import de.flower.rmt2.db.repo.EventRepo
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class EventRepoTest : AbstractMysqlRepoTest() {

    @Autowired
    lateinit var eventRepo: EventRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val events = eventRepo.findAll()
        events.forEach { log.info(it.toString()) }
    }

}