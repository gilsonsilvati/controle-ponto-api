package com.controleponto.api.modelmapper.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperCore {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
