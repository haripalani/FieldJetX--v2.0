package fieldjetx.v2.POM;

import org.openqa.selenium.By;

public class LoginPageLocators {

    public static final By USERNAME_INPUT = By.xpath("//input[@type='email']");
    public static final By PASSWORD_INPUT = By.xpath("//input[@type='password']");
    public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By USERNAME_LABEL = By.xpath("//*[@id='header']/div/sa-login-info");
}
