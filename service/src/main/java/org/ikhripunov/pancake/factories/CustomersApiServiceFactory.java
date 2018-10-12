package org.ikhripunov.pancake.factories;

import org.ikhripunov.pancake.api.CustomersApi;
import org.ikhripunov.pancake.impl.CustomersApiImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CustomersApiServiceFactory implements ApplicationContextAware {
    private static CustomersApi service;

    public static CustomersApi getCustomersApi() {
        return service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.service = applicationContext.getBean(CustomersApiImpl.class);
    }
}