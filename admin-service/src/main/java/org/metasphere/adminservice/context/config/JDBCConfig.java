package org.metasphere.adminservice.context.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-09 21:48
 * @Modified By:
 */
@Configuration
public class JDBCConfig {
    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.daq-db-datasource.driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty("spring.daq-db-datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.daq-db-datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.daq-db-datasource.password"));
        return new JdbcTemplate(dataSource);
    }
}
