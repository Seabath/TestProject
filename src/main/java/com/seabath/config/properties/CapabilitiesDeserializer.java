package com.seabath.config.properties;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;
import org.assertj.core.data.MapEntry;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariOptions;

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
        Class<?> type;
        switch (key) {
            case ChromeOptions.CAPABILITY:
            case "chromeOptions":
                type = ChromeOptions.class;
                break;
            case EdgeOptions.CAPABILITY:
            case "edgeOptions":
                type = EdgeOptions.class;
                break;
            case FirefoxOptions.FIREFOX_OPTIONS:
            case "firefoxOptions":
                type = FirefoxOptions.class;
                break;
            case OperaOptions.CAPABILITY:
                type = OperaOptions.class;
                break;
            case "se:ieOptions":
                type = InternetExplorerOptions.class;
                break;
            case SafariOptions.CAPABILITY:
                type = SafariOptions.class;
                break;
            default:
                type = String.class;
                break;
        }
        return MapEntry.entry(key, new Gson().fromJson(value, type));

    }
}
