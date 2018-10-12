package org.ikhripunov.pancake.configuration;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.ikhripunov.pancake.model.Customer;
import org.ikhripunov.pancake.model.Note;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {

    @Bean
    public DozerBeanMapper createDozerBeanMapper() {
        return (DozerBeanMapper) DozerBeanMapperBuilder.create()
                .withMappingBuilder(new DozerBeanMappingBuilder())
                .build();
    }

    private class DozerBeanMappingBuilder extends BeanMappingBuilder {

        @Override
        protected void configure() {
            mapping(Customer.class, org.ikhripunov.pancake.jooq.tables.pojos.Customer.class)
                    .fields("id", "externalId", FieldsMappingOptions.customConverter(StringToUUIDConverter.class));
            mapping(Note.class, org.ikhripunov.pancake.jooq.tables.pojos.Note.class)
                    .fields("id", "externalId", FieldsMappingOptions.customConverter(StringToUUIDConverter.class));
        }
    }
}
