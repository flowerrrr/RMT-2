package de.flower.rmt2.db.repo

import de.flower.rmt2.db.config.DbConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [DbConfig::class])
@EnableAutoConfiguration
abstract class AbstractH2RepoTest {
}