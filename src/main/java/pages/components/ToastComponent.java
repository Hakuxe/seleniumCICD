package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ToastComponent extends BasePage {

    protected WebDriver driver;

    public ToastComponent(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By toastBy = By.id("toast-container");

    public String getToastMessage(){
        waitForElementToAppear(toastBy);
        return driver.findElement(toastBy).getText();
    }

}
