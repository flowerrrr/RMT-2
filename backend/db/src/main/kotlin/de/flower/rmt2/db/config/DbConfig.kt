package de.flower.rmt2.db.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableJpaRepositories("de.flower.rmt2.db.repo")
@EntityScan("de.flower.rmt2.db.entity")
@EnableTransactionManagement
class DbConfig {

//    @Bean
//    fun dataSource(): DataSource {
//        val builder = EmbeddedDatabaseBuilder()
//        return builder.setType(EmbeddedDatabaseType.H2).build()
//    }
//
//    @Bean
//    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
//        val vendorAdapter = HibernateJpaVendorAdapter()
//        vendorAdapter.setGenerateDdl(true)
//        val factory = LocalContainerEntityManagerFactoryBean()
//        factory.jpaVendorAdapter = vendorAdapter
//        factory.setPackagesToScan("de.flower.rmt2.db.entity")
//        factory.setDataSource(dataSource())
//        return factory
//    }
//
//    @Bean
//    fun transactionManager(entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
//        val txManager = JpaTransactionManager()
//        txManager.entityManagerFactory = entityManagerFactory
//        return txManager
//    }
}