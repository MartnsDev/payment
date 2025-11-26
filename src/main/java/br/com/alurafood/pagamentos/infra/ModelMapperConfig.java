package br.com.alurafood.pagamentos.infra;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // opcional: configurações que evitam sobrescrever campos com nulls, etc
        // mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }
}
