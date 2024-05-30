package com.utfpr.backendfuncionariocargo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories("com.utfpr.backendfuncionariocargo.repository")
@EnableTransactionManagement
public class SpringDataConfig {
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setUsername("root");
		dataSource.setPassword("senharoot");
		dataSource.setJdbcUrl("jdbc:h2:mem:teste");
		dataSource.setDriverClassName("org.h2.Driver");
		
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(false);
		
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.utfpr.backendfuncionariocargo.entity");
		factory.afterPropertiesSet();
		
		
		return factory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		transactionManager.setJpaDialect(new HibernateJpaDialect());
			
		return transactionManager;
	}

}
