package com.rkaneko.example;

import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkaneko.example.util.json.ObjectMapperBuilder;

public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = new ObjectMapperBuilder().configure().withJavaTimeModule().withOptionalModule().build();
        converters.add(new MappingJackson2HttpMessageConverter(mapper));
        super.configureMessageConverters(converters);
    }
}
