package com.sumslack.web.simple.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.sumslack.web.simple.interceptor.MyHandlerInterceptor;

/**
 * Created by huaijie.chen on 2016/9/26.
 */
@Component
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	@Bean
	MyHandlerInterceptor myHandlerInterceptor() {
		return new MyHandlerInterceptor();
	}

    /**
     * 配置MessageConverter中的ObjectMapper：允许将空对象转成Json字符串。
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJsonConverter = (MappingJackson2HttpMessageConverter)converter;
                mappingJsonConverter.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            }
        }
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(myHandlerInterceptor()).addPathPatterns("/**");
    	super.addInterceptors(registry);
    }
}
