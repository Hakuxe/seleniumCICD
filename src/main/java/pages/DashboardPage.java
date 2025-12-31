package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {
    protected WebDriver driver;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".card-body")
    List<WebElement> products;

    By cardBy = By.cssSelector(".card-body");
    By btnAddToCart = By.cssSelector("button>i.fa-shopping-cart");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public List<WebElement> getProducts() {
        waitForElementToAppear(cardBy);
        return driver.findElements(cardBy);
    }

    public WebElement getProductByName(String productName){

        WebElement product = null;

        for (WebElement el : getProducts()) {
            String text = el.findElement(By.tagName("h5")).getText();

            if (text.equalsIgnoreCase(productName)) {
                product = el.findElement(btnAddToCart);
            }
        }

        return product;
    }

    public void addToCart(String productName){
        WebElement product = getProductByName(productName);
        product.click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
