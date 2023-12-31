package de.flower.rmt2.rest.config

import de.flower.rmt2.core.config.CoreConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@ComponentScan(
    basePackages = ["de.flower.rmt2.rest.error", "de.flower.rmt2.rest.jwt"],
    // avoid picking up @Configuration classes inside test classes
    excludeFilters = [ComponentScan.Filter(Configuration::class)]
)
@Import(
    CoreConfig::class,
    SecurityConfig::class
)
class RestConfig {

}