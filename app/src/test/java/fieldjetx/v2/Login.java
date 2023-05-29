package fieldjetx.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fieldjetx.v2.POM.LoginPageLocators;

public class Login {
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


    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(driver, new LoginPageLocators());
    }

    public void navigateToLoginPage() {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    public boolean performlogin(String usernameValue, String passwordValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the username input field to be present and clear it before sending the username value
        WebElement usernameInputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocators.USERNAME_INPUT)));
        usernameInputField.clear();
        usernameInputField.sendKeys(usernameValue);

        // Wait for the password input field to be present and clear it before sending the password value
        WebElement passwordInputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocators.PASSWORD_INPUT)));
        passwordInputField.clear();
        passwordInputField.sendKeys(passwordValue);

        // Wait for the login button to be clickable and click it
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LoginPageLocators.LOGIN_BUTTON)));
        loginButtonElement.click();

        // Wait for the username label to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageLocators.USERNAME_LABEL)));

        // Verify that the username label contains the expected username value
        String usernameLabelText = usernameLabel.getText();
        if (!usernameLabelText.contains(usernameValue)) {
            return false;
        }


        return true;
    }
}
