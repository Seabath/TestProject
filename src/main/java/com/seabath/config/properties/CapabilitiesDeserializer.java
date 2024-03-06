package com.seabath.config.properties;

import com.google.gson.*;
import org.assertj.core.data.MapEntry;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

public class CapabilitiesDeserializer implements JsonDeserializer<Map<String, Object>> {
    @Override
    public Map<String, Object> deserialize(JsonElement elem, Type type,
                                           JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return elem.getAsJsonObject()
            .entrySet()
            .stream()
            .map(entry -> formatType(entry.getKey(), entry.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private MapEntry<String, Object> formatType(String key, JsonElement value) {
        Class<?> type = switch (key) {
            case ChromeOptions.CAPABILITY, "chromeOptions" -> ChromeOptions.class;
            case EdgeOptions.CAPABILITY, "edgeOptions" -> EdgeOptions.class;
            case FirefoxOptions.FIREFOX_OPTIONS, "firefoxOptions" -> FirefoxOptions.class;
            case "se:ieOptions" -> InternetExplorerOptions.class;
            default -> String.class;
        };
        return MapEntry.entry(key, new Gson().fromJson(value, type));
    }
}
