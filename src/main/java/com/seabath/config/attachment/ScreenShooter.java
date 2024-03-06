package com.seabath.config.attachment;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class ScreenShooter {

    private final TakesScreenshot takesScreenshot;

    public ScreenShooter(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @SuppressWarnings("UnusedReturnValue")
    public void shootScreen() {
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png"
        );
    }
}
