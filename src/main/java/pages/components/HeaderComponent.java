package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class HeaderComponent {

    protected WebDriver driver;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    By cartBtn = By.cssSelector("[routerlink='/dashboard/cart']");

    public CartPage goToCartPage(){
        driver.findElement(cartBtn).click();

        return new CartPage(driver);
    }

}
