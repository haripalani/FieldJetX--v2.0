package fieldjetx.v2;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import fieldjetx.v2.POM.Constants;

public class Home {

    private WebDriver driver;
    private String url;

    //Constructor
    public Home(WebDriver driver) {
        this.driver = driver;
        this.url = Constants.HOME_PAGE_URL;
    }

    public void navigateToHome() {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    public boolean performLogout() {
        try {
            logoutBtn.click();
    
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
    
            confirmBtn.click();
    
            wait.until(ExpectedConditions.urlToBe(Constants.HOME_PAGE_URL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    @FindBy(xpath = Constants.LOGOUT_BTN_XPATH)
    private WebElement logoutBtn;

    @FindBy(xpath = Constants.CONFIRM_BTN_XPATH)
    private WebElement confirmBtn;
}