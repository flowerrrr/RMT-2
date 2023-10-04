package de.flower.rmt2.db.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

class InvitationRepoTest : AbstractH2RepoTest() {

    @Autowired
    lateinit var invitationRepo: InvitationRepo

    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun findAll() {
        val invitations = invitationRepo.findAll()
        log.info(invitations.toString());
        assertThat(invitations).isNotEmpty()
    }

    @Test
    fun findTop20ByUserEmailOrderByEventDatetimeDesc() {
        val invitations = invitationRepo.findTop20ByUserEmailOrderByEventDateTimeDesc("oliver.blume@yahoo.de")
        log.info(invitations.toString());
        assertThat(invitations).isNotEmpty()
    }

}