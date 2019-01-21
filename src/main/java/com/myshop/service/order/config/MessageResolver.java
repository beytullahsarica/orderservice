package com.myshop.service.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:response-messages.properties")
public class MessageResolver {

    @Autowired
    private Environment env;

    public String getMessage(String propertyName) {
        return env.getProperty(propertyName);
    }
}
