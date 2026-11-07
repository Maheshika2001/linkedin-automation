package com.sqa.linkedin.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Reads settings from environment variables first, then config.properties in the project root. */
public final class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("config.properties")) {
            PROPS.load(in);
        } catch (IOException ignored) {
            // File is optional when environment variables are used.
        }
    }

    private Config() { }

    public static String username() { return required("LINKEDIN_USERNAME", "linkedin.username"); }
    public static String password() { return required("LINKEDIN_PASSWORD", "linkedin.password"); }

    public static String browser() {
        String b = System.getProperty("browser");
        if (b == null || b.isBlank() || b.startsWith("${")) {
            b = PROPS.getProperty("browser", "chrome");
        }
        return b.trim().toLowerCase();
    }

    public static String searchTerm() { return PROPS.getProperty("search.term", "software testing"); }
    public static int timeoutSeconds() { return Integer.parseInt(PROPS.getProperty("timeout.seconds", "45")); }
    public static int checkpointSeconds() { return Integer.parseInt(PROPS.getProperty("checkpoint.wait.seconds", "180")); }

    private static String required(String envName, String propName) {
        String v = System.getenv(envName);
        if (v == null || v.isBlank()) {
            v = PROPS.getProperty(propName);
        }
        if (v == null || v.isBlank() || v.contains("example.com") || v.contains("your-password")) {
            throw new IllegalStateException("Missing credential '" + propName
                    + "'. Set env var " + envName + " or fill config.properties (see README).");
        }
        return v;
    }
}
