package de.flower.rmt2.core.config

import de.flower.rmt2.db.config.DbConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@ComponentScan(
    basePackages = ["de.flower.rmt2.core.service"],
    // avoid picking up @Configuration classes inside test classes
    excludeFilters = [ComponentScan.Filter(Configuration::class)]
)
@Import(DbConfig::class)
class CoreConfig {
}