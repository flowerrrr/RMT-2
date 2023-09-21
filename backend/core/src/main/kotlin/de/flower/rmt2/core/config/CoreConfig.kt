package de.flower.rmt2.core.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
        basePackages = ["de.flower.rmt2.core.service"],
        // avoid picking up @Configuration classes inside test classes
        excludeFilters = [ComponentScan.Filter(Configuration::class)]
)
class CoreConfig {
}