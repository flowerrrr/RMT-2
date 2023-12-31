package de.flower.rmt2.db.mysql

import de.flower.rmt2.db.repo.TeamRepo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

@Disabled
class TeamRepoTest : AbstractMysqlRepoTest() {

    @Autowired
    lateinit var teamRepo: TeamRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val teams = teamRepo.findAll()
        log.info(teams.toString());
    }

}