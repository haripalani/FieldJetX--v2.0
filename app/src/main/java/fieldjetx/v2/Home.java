package fieldjetx.v2;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    private ChromeDriver driver;
    private String url = "http://18.188.111.206/#/dispatch/search";
    
    //Constructor
    public Home(ChromeDriver driver) {
        this.driver = driver;
    }

    // This method navigates to the home page by checking the current URL and navigating to the specified URL if it is not the same.
public void navigateToHome() {
    if (!driver.getCurrentUrl().equals(url)) { // Check if the current URL is not equal to the specified URL.
        driver.get(url); // Navigate to the specified URL.
    }
}


   /**
 * These two lines use the @FindBy annotation to locate the logout button and confirm button elements
 * on the webpage by their respective xpaths: "//div[@id='logout']" and "//button[@id='bot2-Msg1']".
 * The located elements are stored in private member variables logoutBtn and confirmBtn respectively,
 * so that they can be accessed by other methods in the same class.
 */
@FindBy(xpath = "//div[@id='logout']")
private WebElement logoutBtn;

@FindBy(xpath = "//button[@id='bot2-Msg1']")
private WebElement confirmBtn;


    /**
 * This function performs a logout action on the webpage.
 * It clicks on the logout button, waits for 5 seconds, confirms the logout,
 * waits for up to 10 seconds for the URL to be "http://18.188.111.206/#/auth/login",
 * and returns true if successful or false if there's an exception.
 */
public boolean performLogout() {
    try {
        logoutBtn.click(); // Click on the logout button
        Thread.sleep(5000); // Wait for 5 seconds
        confirmBtn.click(); // Confirm the logout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        wait.until(ExpectedConditions.urlToBe("http://18.188.111.206/#/auth/login")); // Wait for the URL to be "http://18.188.111.206/#/auth/login"
        return true; // Return true if successful
    } catch (Exception e) {
        return false; // Return false if there's an exception
    }
}

}
