package com.victim.configs.dbconfigs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mySqlEntityManager",
        basePackages = "com.victim.repositories.mysql",
        transactionManagerRef = "mySqlTransactionManager"
)
public class MySQLDatabaseConfiguration {

    /****
     * DataSource Configuration
     ****/
    @Primary
    @Bean("mysqlDb")
    @ConfigurationProperties(prefix = "mysql.datasource")
    public DataSource mySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    /****
     * Entity Manager Configuration
     ****/
    @Primary
    @Bean("mySqlEntityManager")
    public LocalContainerEntityManagerFactoryBean mySqlEntityManager(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        // Adding some JPA/Hibernate specific properties to set on properties field.
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.show_sql", "true");

        return entityManagerFactoryBuilder
                .dataSource(mySqlDataSource())
                .packages("com.victim.entities.mysql")
                .properties(jpaProperties)
                .build();
    }

    /****
     * Transaction Manager Configuration
     ****/
    @Primary
    @Bean("mySqlTransactionManager")
    public PlatformTransactionManager mySqlTransactionManager(@Qualifier("mySqlEntityManager")
                                                              LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }


}
