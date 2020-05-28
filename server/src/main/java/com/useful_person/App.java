package com.useful_person;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    JdbcTemplateAutoConfiguration.class
})
public class App {

  @Bean
  @ConfigurationProperties("foo.datasource")
  public DataSourceProperties fooDataSourceProperties() {
    return new DataSourceProperties();
  }
  @Bean
  public DataSource fooDataSource() {
    DataSourceProperties dataSourceProperties = fooDataSourceProperties();
    log.info("foo datasource: {}", dataSourceProperties.getUrl());
    return dataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Resource
  public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
    return new DataSourceTransactionManager(fooDataSource);
  }

  @Bean
  @ConfigurationProperties("bar.datasource")
  public DataSourceProperties barDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource barDataSource() {
    DataSourceProperties dataSourceProperties = barDataSourceProperties();
    log.info("bar datasource: {}", dataSourceProperties.getUrl());
    return dataSourceProperties.initializeDataSourceBuilder().build();
  }

  @Bean
  @Resource
  public PlatformTransactionManager barTxManager(DataSource barDataSource) {
    return new DataSourceTransactionManager(barDataSource);
  }


  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
