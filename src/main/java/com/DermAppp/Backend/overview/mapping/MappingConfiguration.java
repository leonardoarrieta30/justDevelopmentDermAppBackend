package com.DermAppp.Backend.overview.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("overviewMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public DematologistMapper dematologistMapper(){
        return new DematologistMapper();
    }





}
