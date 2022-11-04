package com.DermApp.Backend.diagnosticIllness.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("overviewMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public DematologistMapper dematologistMapper(){
        return new DematologistMapper();
    }

    @Bean
    public PatientMapper patientMapper(){
        return new PatientMapper();
    }




}
