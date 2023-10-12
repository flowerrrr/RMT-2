package de.flower.rmt2.db.mysql

import de.flower.rmt2.db.repo.PlayerRepo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

@Disabled
class PlayerRepoTest : AbstractMysqlRepoTest() {

    @Autowired
    lateinit var playerRepo: PlayerRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val players = playerRepo.findAll()
        log.info(players.toString());
    }

}