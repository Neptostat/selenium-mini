package com.example.utils;

import com.example.config.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        WebDriver drv = TL_DRIVER.get();
        if (drv == null) {
            drv = createDriver();
            TL_DRIVER.set(drv);
        }
        return drv;
    }

    public static void quitDriver() {
        WebDriver drv = TL_DRIVER.get();
        if (drv != null) {
            drv.quit();
            TL_DRIVER.remove();
        }
    }

    private static WebDriver createDriver() {
        String browser = ConfigFactory.get("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigFactory.get("headless", "false"));

        switch (browser) {
            case "firefox": {
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                WebDriver d = new FirefoxDriver(fo);
                postConfigure(d);
                return d;
            }
            case "chrome":
            default: {
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new");
                WebDriver d = new ChromeDriver(co);
                postConfigure(d);
                return d;
            }
        }
    }

    private static void postConfigure(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }
}
