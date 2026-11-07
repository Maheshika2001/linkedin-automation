package com.sqa.linkedin.base;

import org.openqa.selenium.WebDriver;

/** Holds the driver of the current test thread so listeners can reach it (for screenshots). */
public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() { }

    public static WebDriver get() { return DRIVER.get(); }
    public static void set(WebDriver driver) { DRIVER.set(driver); }
    public static void remove() { DRIVER.remove(); }
}
