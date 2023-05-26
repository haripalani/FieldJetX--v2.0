package fieldjetx.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    ChromeDriver driver;
    String url ="http://18.188.111.206/";

    // Declare elements using @FindBy annotations
    @FindBy(xpath="//input[@type='email']")
    WebElement username;

    @FindBy(xpath="//input[@type='password']")
    WebElement password;

    @FindBy(xpath="//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath="//*[@id='header']/div/sa-login-info")
    WebElement usernameLabel;

    // Constructor for Login object
    public Login(WebDriver driver) {
        this.driver = (ChromeDriver) driver;
        PageFactory.initElements(driver, this);
    }

    // Navigates to login page
    public void navigateToLoginPage() {
        // Check if the current URL is not equal to the target URL
        if (!driver.getCurrentUrl().equals(url)) {
            // Navigate to the target URL
            driver.get(url);
        }
    }

    // Performs login by entering the username and password and clicking the login button
    // Returns a Boolean value indicating whether the user is logged in or not
    public Boolean performlogin(String usernameValue, String passwordValue) throws InterruptedException {
        // Enter the username and password values
        username.clear();
        username.sendKeys(usernameValue);
        password.clear();
        password.sendKeys(passwordValue);

        // Click the login button
        loginButton.click();

        // Wait for 5 seconds for the user to be logged in
        Thread.sleep(5000);

        // Return a Boolean value indicating whether the user is logged in or not
        return this.verifyUserLoggedIn(usernameValue);
    }

    // Checks whether the given username is currently logged in
    // Returns a Boolean value indicating whether the user is logged in or not
    public Boolean verifyUserLoggedIn(String usernameValue) {
        try {
            // Compare the text of the username label with the given username
            return usernameLabel.getText().equals(usernameValue);
        } catch (Exception e) {
            // Return false if the username label is not found or an exception is thrown
            return false;
        }
    }
}
