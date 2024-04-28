package com.artyom.crud.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.artyom.crud")
@PropertySource("classpath:database.properties")
public class CrudConfig {
    private final Environment env;

    public CrudConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean containerEntity = new LocalContainerEntityManagerFactoryBean();
        containerEntity.setDataSource(dataSource());
        containerEntity.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        containerEntity.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        containerEntity.setJpaProperties(getHibernateProperties());
        return containerEntity;
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(env.getRequiredProperty("db.url"));
        dataSource.setUser(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    private Properties getHibernateProperties() {
        try (InputStream reader = getClass().getClassLoader().getResourceAsStream("hibernate.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't file hibernate.properties");
        }
    }
}
