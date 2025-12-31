package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    protected WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By productNameBy = By.cssSelector(".cartSection h3");
    By checkoutBtn = By.cssSelector("div.subtotal button");

    public List<WebElement> getCartProducts(){
        waitForElementToAppear(productNameBy);
        return driver.findElements(productNameBy);
    }

    public boolean checkIfProductIsInCart(String productName){
        return getCartProducts().stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckoutPage(){
        driver.findElement(checkoutBtn).click();

        return new CheckoutPage(driver);
    }

}
