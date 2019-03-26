package com.spring.dtx.springdtxdbdb.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/20 22:36
 */
@Configuration
public class DBConfiguration {
    
    @Bean
    @Primary // @Primary注解的实例优先于其他实例被注入
    @ConfigurationProperties(prefix = "spring.ds_user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    @Primary
    public DataSource userDataSource() {
        return userDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    
    @Bean
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource userDataSource) {
        return new JdbcTemplate(userDataSource);
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.ds_order")
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    public DataSource orderDataSource() {
        return orderDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    
    @Bean
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource orderDataSource) {
        return new JdbcTemplate(orderDataSource);
    }
    
    // 链式事务管理器---DatasourceTransactionManager
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager userTM = new DataSourceTransactionManager(userDataSource());
        // userTM.setDataSource(userDataSource()); 该方式不是从spring容器中获取userDataSource()，采用上面的方式才是从spring容器中获取userDataSource()
        PlatformTransactionManager orderTM = new DataSourceTransactionManager(orderDataSource());
        // 链式事务管理器，先提交orderTM,再提交userTM
        ChainedTransactionManager tm = new ChainedTransactionManager(userTM, orderTM);
        return tm;
    }
}
