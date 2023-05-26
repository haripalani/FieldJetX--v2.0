package fieldjetx.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;

import fieldjetx.v2.POM.Constants;
import fieldjetx.v2.POM.LoginPageLocators;

// This is a class called Login
public class Login {
    // These are private instance variables of the class
    private WebDriver driver;
    private String url = Constants.BASE_URL;

    // These are private instance variables of the class
    // They are WebElements that represent the username input, 
    //password input, and login button on the login page
    private WebElement username;
    private WebElement password;
    private WebElement loginBtn;

    // This is a private instance variable of the class
    // It is a WebElement that represents the username label on the login page
    private By usernameLabel = LoginPageLocators.USERNAME_LABEL;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(driver, new LoginPageLocators());
        this.username = driver.findElement(LoginPageLocators.USERNAME_INPUT);
        this.password = driver.findElement(LoginPageLocators.PASSWORD_INPUT);
        this.loginBtn = driver.findElement(LoginPageLocators.LOGIN_BUTTON);
    }

    public void navigateToLoginPage() {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

   // This method performs a login action by filling the username and password fields
// with the given values, clicking the login button, and verifying that the username label
// displays the expected username value.
    public boolean performlogin(String usernameValue, String passwordValue) {
    // Clear the username field and fill it with the given username value
    username.clear();
    username.sendKeys(usernameValue);

    // Clear the password field and fill it with the given password value
    password.clear();
    password.sendKeys(passwordValue);

    // Click the login button
    loginBtn.click();

    // Wait for the username label to be displayed
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLabel));

    // Verify that the username label contains the expected username value
    WebElement usernameLabelElement = driver.findElement(usernameLabel);
    return usernameLabelElement.getText().contains(usernameValue);
}

}
