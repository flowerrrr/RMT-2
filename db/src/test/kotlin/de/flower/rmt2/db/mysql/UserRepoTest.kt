package de.flower.rmt2.db.mysql

import de.flower.rmt2.db.repo.UserRepo
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class UserRepoTest : AbstractMysqlRepoTest() {

    @Autowired
    lateinit var userRepo: UserRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val users = userRepo.findAll()
        log.info(users.toString());
    }

}