package com.seabath.config.attachment;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShooter {

    private final TakesScreenshot takesScreenshot;

    public ScreenShooter(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] shootScreen() {
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
}
