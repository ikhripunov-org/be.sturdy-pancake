package org.ikhripunov.pancake.factories;

import org.ikhripunov.pancake.api.PingApi;
import org.ikhripunov.pancake.impl.PingApiImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PingApiServiceFactory implements ApplicationContextAware {
    private static PingApi service;

    public static PingApi getPingApi() {
        return service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.service = applicationContext.getBean(PingApiImpl.class);
    }
}