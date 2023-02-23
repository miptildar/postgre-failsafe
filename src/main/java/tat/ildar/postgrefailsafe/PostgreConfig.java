package tat.ildar.postgrefailsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
public class PostgreConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreConfig.class);

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "postgre")
    DataSource dataSource() {
        LOGGER.info("Setting up PostgreSQL data source..");
        return DataSourceBuilder
                .create()
                .build();
    }

}
