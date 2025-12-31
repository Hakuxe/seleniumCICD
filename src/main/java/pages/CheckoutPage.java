package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    protected WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By inputSelectCountry = By.cssSelector("div.user__name div.user__address input");
    By optionSelectCountry =By.cssSelector("section.ta-results button");
    By placeOrderBtn = By.cssSelector("a.action__submit");

    public void selectCountry(String countryName){
        driver.findElement(inputSelectCountry).sendKeys(countryName);
        waitForElementToAppear(optionSelectCountry);
        driver.findElement(optionSelectCountry).click();
    }

    public void placeOrder(){
        waitForElementToAppear(placeOrderBtn);
        new Actions(driver)
                .scrollToElement(driver.findElement(placeOrderBtn))
                .perform();
        driver.findElement(placeOrderBtn).click();

    }
}
