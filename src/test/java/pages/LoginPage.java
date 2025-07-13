package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPage {
    private AppiumDriver driver;

    private static final Logger log = LogManager.getLogger(LoginPage.class);



    public LoginPage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),this);
    }

    //Locators
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]\n")
    private WebElement accessLoginPopup;

    @AndroidFindBy(xpath = "//com.horcrux.svg.B/com.horcrux.svg.J[2]")
    private WebElement headerText;

    public void loginPage(){
//        accessLoginPopup.click();
        String text = headerText.getText();
        log.info("Capturing the header text");
        System.out.println(text);
        log.info("printing the header text");
    }




}
