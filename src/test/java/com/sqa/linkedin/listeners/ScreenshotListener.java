package com.sqa.linkedin.listeners;

import com.sqa.linkedin.base.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Saves a PNG to /screenshots automatically whenever a test fails. */
public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.get();
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }
        try {
            Path dir = Paths.get("screenshots");
            Files.createDirectories(dir);
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path target = dir.resolve("FAIL_" + result.getMethod().getMethodName() + "_" + stamp + ".png");
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(target, png);
            System.out.println("Screenshot saved: " + target.toAbsolutePath());
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not take screenshot: " + e.getMessage());
        }
    }
}
