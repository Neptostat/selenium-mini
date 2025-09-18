package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private final WebDriver driver;

    @FindBy(id = "welcomeMsg")
    private WebElement welcomeMsg;

    @FindBy(id = "roleDropdown")
    private WebElement roleDropdown;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeText() {
        return welcomeMsg.getText();
    }

    public void selectRole(String roleVisibleText) {
        new Select(roleDropdown).selectByVisibleText(roleVisibleText);
    }
}
