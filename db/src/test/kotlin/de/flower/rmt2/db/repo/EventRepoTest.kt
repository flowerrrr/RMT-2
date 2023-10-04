package de.flower.rmt2.db.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class EventRepoTest : AbstractH2RepoTest() {

    @Autowired
    lateinit var eventRepo: EventRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val events = eventRepo.findAll()
        log.info(events.toString());
    }

    @Test
    fun findByInvitationsUserEmailAndDateTimeAfter() {
        val events = eventRepo.findByInvitationsUserEmailAndDateTimeAfter("oliver.blume@yahoo.de", LocalDateTime.now().minusDays(30))
        log.info(events.toString());
        assertThat(events).isNotEmpty()
    }

}