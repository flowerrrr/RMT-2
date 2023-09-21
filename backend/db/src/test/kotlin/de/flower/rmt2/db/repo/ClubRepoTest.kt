package de.flower.rmt2.db.repo

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class ClubRepoTest : AbstractRepoTest() {

    @Autowired
    lateinit var clubRepo: ClubRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val clubs = clubRepo.findAll()
        log.info(clubs.toString());
    }

}