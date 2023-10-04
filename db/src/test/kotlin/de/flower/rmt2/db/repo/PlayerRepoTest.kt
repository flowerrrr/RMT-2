package de.flower.rmt2.db.repo

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class PlayerRepoTest : AbstractH2RepoTest() {

    @Autowired
    lateinit var playerRepo: PlayerRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val players = playerRepo.findAll()
        log.info(players.toString());
    }

}