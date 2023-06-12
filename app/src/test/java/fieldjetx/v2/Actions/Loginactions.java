package fieldjetx.v2.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fieldjetx.v2.POM.LoginPageLocators;

public class Loginactions {
    private WebDriver driver;
    private String url = "http://18.188.111.206/";
    @FindBy(xpath = LoginPageLocators.USERNAME_INPUT)
    private WebElement username;
    @FindBy(xpath = LoginPageLocators.PASSWORD_INPUT)
    private WebElement password;
    @FindBy(xpath = LoginPageLocators.LOGIN_BUTTON)
    private WebElement loginBtn;
    @FindBy(xpath = LoginPageLocators.USERNAME_LABEL)
    private WebElement usernameLabel;
    @FindBy(xpath = LoginPageLocators.LOGOUT_BTN_XPATH)
    private WebElement logoutBtn;
    @FindBy(xpath = LoginPageLocators.CONFIRM_BTN_XPATH)
    private WebElement confirmBtn;
    @FindBy(xpath = LoginPageLocators.LOGIN_PAGE)
    private WebElement loginPage;

    public Loginactions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(driver, new LoginPageLocators());
    }

/**
 * This function navigates to the login page by checking if the current URL is the same as the expected URL.
 * If it is not, it will navigate to the expected URL.
 */
public void navigateToLoginPage() {
    String currentUrl = driver.getCurrentUrl();
    // If the current URL is not the expected URL, navigate to the expected URL.
    if (!currentUrl.equals(url)) {
        driver.get(url);
    }
}

public boolean performlogin(String usernameValue, String passwordValue) {
    // Set up a WebDriverWait instance with a timeout of 10 seconds
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Locate and clear the username input field, then enter the provided username value
    WebElement usernameInputField = wait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocators.USERNAME_INPUT))
    );
    usernameInputField.clear();
    usernameInputField.sendKeys(usernameValue);

    // Locate and clear the password input field, then enter the provided password value
    WebElement passwordInputField = wait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocators.PASSWORD_INPUT))
    );
    passwordInputField.clear();
    passwordInputField.sendKeys(passwordValue);

    // Locate and click the login button
    WebElement loginButtonElement = wait.until(
        ExpectedConditions.elementToBeClickable(By.xpath(LoginPageLocators.LOGIN_BUTTON))
    );
    loginButtonElement.click();

    // Wait for the username label to be displayed
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageLocators.USERNAME_LABEL)));


    // Verify that the username label contains the expected username value
    WebElement usernameLabel = driver.findElement(By.xpath(LoginPageLocators.USERNAME_LABEL));
    String usernameLabelText = usernameLabel.getText();
    if (!usernameLabelText.contains(usernameValue)) {
        return false;
    }

    // If the username label contains the expected value, return true
    return true;
}

/**
 * Attempts to logout of the application.
 * @return true if successful, false otherwise.
 */
public boolean performLogout() {
    try {
        // Click the logout button
        logoutBtn.click();

        // Wait for the confirmation button to become clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));

        // Click the confirmation button
        confirmBtn.click();
        
        // Wait for the login page to become visible
        wait.until(ExpectedConditions.visibilityOf(loginPage)); 

        // Return true to indicate successful logout
        return true;
    } catch (Exception e) {
        // Return false if any exceptions occur
        return false;
    }
}

    }

