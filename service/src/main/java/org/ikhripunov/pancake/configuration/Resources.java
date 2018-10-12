package org.ikhripunov.pancake.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class Resources extends ResourceConfig {
    public Resources() {
        register(org.ikhripunov.pancake.api.CustomersApi.class);
        register(org.ikhripunov.pancake.api.PingApi.class);
    }
}
