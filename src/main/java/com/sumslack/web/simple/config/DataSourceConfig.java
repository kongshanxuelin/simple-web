package com.sumslack.web.simple.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig implements EnvironmentAware{

	private RelaxedPropertyResolver propertyResolver;


    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "database.main.");
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    public DataSource dataSource() {
        return getDataSource(propertyResolver);
    }

    private DataSource getDataSource(RelaxedPropertyResolver resolver) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(resolver.getProperty("url"));
        datasource.setDriverClassName(resolver
                .getProperty("driverClassName"));
        datasource.setMaxActive(NumberUtils.toInt(resolver.getProperty("max-active")));
        datasource.setMinIdle(NumberUtils.toInt(resolver.getProperty("min-idle")));
        datasource.setInitialSize(NumberUtils.toInt(resolver.getProperty("initial-size")));
        datasource.setValidationQuery(resolver.getProperty("validation-query"));
        datasource.setUsername(resolver.getProperty("username"));
        datasource.setPassword(resolver.getProperty("password"));
        return datasource;
    }


}
