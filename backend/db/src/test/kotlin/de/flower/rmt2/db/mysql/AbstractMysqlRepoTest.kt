package de.flower.rmt2.db.mysql

import de.flower.rmt2.db.config.DbConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/**
 * These tests require a running mysql database in the docker container with preinstalled schema and data.
 */
@ActiveProfiles("mysql")
@SpringBootTest(classes = [DbConfig::class])
@EnableAutoConfiguration
abstract class AbstractMysqlRepoTest {
}