package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUpPage(){
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin(){
        loginPage.loginPage();
    }


}
