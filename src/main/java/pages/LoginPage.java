package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // @FindBy deve ser usado em páginas estáticas
    @FindBy(id = "userEmail")
    private WebElement inputEmail;

    @FindBy(id = "userPassword")
    private WebElement inputPassword;

    @FindBy(id = "login")
    private WebElement btnLogin;




    public DashboardPage loginWithUserAndPassword(String userName, String password) {
        waitForElementToAppear(By.id("userEmail"));
        waitForElementToAppear(By.id("userPassword"));
        waitForElementToAppear(By.id("login"));
        inputEmail.sendKeys(userName);
        inputPassword.sendKeys(password);
        btnLogin.click();

        return new DashboardPage(driver);
    }


}
