package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("Login")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUpPage(){
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Login with valid credentials")
    @Story("Valid Login Story")
    public void testValidLogin(){
        loginPage.loginPage();
    }


}
