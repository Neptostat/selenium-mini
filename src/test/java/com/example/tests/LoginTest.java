package com.example.tests;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.utils.DriverFactory;
import com.example.config.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(ConfigFactory.get("baseUrl", "https://example.com/login"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void userCanLogin() {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginAs(
                ConfigFactory.get("user", "testuser"),
                ConfigFactory.get("pass", "password123")
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcomeMsg")));
        Assert.assertTrue(home.getWelcomeText().contains("Welcome"));
        home.selectRole("Admin");
    }
}
