package org.hakuxe.tests;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import pages.CartPage;
import pages.CheckoutPage;
import pages.DashboardPage;
import pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.components.HeaderComponent;
import pages.components.ToastComponent;
import utils.RetryTestNg;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SimplesTest extends BaseTest {


    @Test(dataProvider = "getDataHashMap", retryAnalyzer = RetryTestNg.class)
    public void simpleTest(HashMap<String, String> data) {


        LoginPage loginPage = new LoginPage(driver);
        HeaderComponent headerComponent = new HeaderComponent(driver);
        ToastComponent toastComponent = new ToastComponent(driver);

        DashboardPage dashboardPage = loginPage.loginWithUserAndPassword(data.get("user"), data.get("pass"));

        List<WebElement> products = dashboardPage.getProducts();
        dashboardPage.addToCart(data.get("productName"));

        CartPage cartPage = headerComponent.goToCartPage();

        List<WebElement> cartProducts = cartPage.getCartProducts();
        boolean isInCart = cartPage.checkIfProductIsInCart(data.get("productName"));

        Assert.assertTrue(isInCart);


        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Brazil");


        checkoutPage.placeOrder();

        String placedOrderMessage = toastComponent.getToastMessage();

        Assert.assertEquals(placedOrderMessage.trim(), "Order Placed Successfully");


    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"xapaco9852@bipochub.com", "Test@12345678", "ZARA COAT 3"},
                {"xapaco9852@bipochub.com", "Test@12345678", "Iphone 13 pro"}

        };
    }

    @DataProvider
    public Object[][] getDataHashMap() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("user", "xapaco9852@bipochub.com");
        map.put("pass", "Test@12345678");
        map.put("productName", "ZARA COAT 3");

        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("user", "xapaco9852@bipochub.com");
        map1.put("pass", "Test@12345678");
        map1.put("productName", "Iphone 13 pro");


        return new Object[][]{
                {map},
                {map1}

        };
    }

    @DataProvider
    public Object[][] getDatajson() {


        List<HashMap<String, String>> data = null;
        try {
            data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test/resources/testdata/data.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }




}
