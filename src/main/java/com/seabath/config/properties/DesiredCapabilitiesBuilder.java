package com.seabath.config.properties;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesBuilder {

    private final String pathToCapabilities;
    private String capabilitiesFileContent;


    public DesiredCapabilitiesBuilder(String pathToCapabilities) {
        this.pathToCapabilities = pathToCapabilities;
    }

    public DesiredCapabilitiesBuilder loadFile() throws IOException {
        if (StringUtils.isBlank(pathToCapabilities)) {
            return this;
        }
        FileInputStream fis = new FileInputStream(pathToCapabilities);
        capabilitiesFileContent = IOUtils.toString(fis, StandardCharsets.UTF_8);
        return this;
    }

    public DesiredCapabilities build() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (StringUtils.isBlank(capabilitiesFileContent)) {
            return desiredCapabilities;
        }
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        final Map<String, Object> map = new GsonBuilder()
            .registerTypeAdapter(type, new CapabilitiesDeserializer())
            .create()
            .fromJson(capabilitiesFileContent, type);
        map.forEach(desiredCapabilities::setCapability);

        return desiredCapabilities;
    }
}
