package com.sumslack.web.simple.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration  
@AutoConfigureAfter({ DataSourceConfig.class }) 
@MapperScan(basePackages = MybatisConfig.PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

    public static final String PACKAGE = "com.sumslack.web.simple.dao";

	@Resource(name= "dataSource")
    private DataSource dataSource;


    @Bean(name = "transactionManager")
    @Qualifier(value = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
            return getSqlSessionTemplate(dataSource);
    }

    private SqlSessionTemplate getSqlSessionTemplate(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setFailFast(true);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/com/sumslack/simple/dao/*.xml"));
        return new SqlSessionTemplate(sessionFactory.getObject());
    }
}
