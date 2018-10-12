package org.ikhripunov.pancake.impl;

import org.ikhripunov.pancake.api.PingApi;
import org.springframework.stereotype.Component;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Component
public class PingApiImpl implements PingApi {
    @Override
    public String ping() throws Exception {
        Manifest mf = new Manifest();
        mf.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"));
        Attributes atts = mf.getMainAttributes();
        return atts.getValue("Implementation-Vendor-Id") + "." + atts.getValue("Implementation-Title") + " " + atts.getValue("Implementation-Version");
    }
}
