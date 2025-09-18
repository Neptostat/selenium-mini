package com.example.tests;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://example.com/login");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userCanLogin() {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginAs("testuser", "password123");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcomeMsg")));
        Assert.assertTrue(home.getWelcomeText().contains("Welcome"));
        home.selectRole("Admin");
    }
}
