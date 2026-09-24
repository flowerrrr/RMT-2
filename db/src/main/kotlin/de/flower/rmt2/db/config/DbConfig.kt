package de.flower.rmt2.db.config

import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@PropertySource("classpath:application-db.properties")
@EnableJpaRepositories("de.flower.rmt2.db.repo")
@EntityScan("de.flower.rmt2.db.entity")
@EnableTransactionManagement
class DbConfig {

}