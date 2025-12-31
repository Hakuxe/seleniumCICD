package org.hakuxe.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.components.HeaderComponent;
import pages.components.ToastComponent;

public class ErrorValidationTests extends BaseTest {

    @Test
    public void shouldVerifyErrorMessage() {

        String user = "xapaco9852F@wrong.com";
        String pass = "Test@12345678";

        LoginPage loginPage = new LoginPage(driver);
        ToastComponent toastComponent = new ToastComponent(driver);

        loginPage.loginWithUserAndPassword(user, pass);
        String errorMessage = toastComponent.getToastMessage();
        Assert.assertEquals(errorMessage.trim(), "Incorrect email or password.");
    }
}
