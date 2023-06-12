package fieldjetx.v2.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;

import fieldjetx.v2.POM.Dispatchlocators;

import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DispatchCreationflow {
    private WebDriver driver;
    private Dispatchlocators locators;
    private WebDriverWait wait;

    public DispatchCreationflow(WebDriver driver) {
        this.driver = driver;
        this.locators = new Dispatchlocators();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickDispatch() {
        driver.findElement(locators.getServiceButton()).click();
        wait.until(ExpectedConditions.elementToBeClickable(locators.getDispatchButton())).click();
    }

    public void clickNewDispatch() {
        wait.until(ExpectedConditions.elementToBeClickable(locators.getNewDropdown())).click();
        wait.until(ExpectedConditions.elementToBeClickable(locators.getNewDispatchButton())).click();
    }

    public void fillDispatchForm(String customerName, String customerLocation, String customerClass) {
        driver.findElement(locators.getCustomerName()).sendKeys(customerName);
        driver.findElement(locators.getCustomerLocation()).sendKeys(customerLocation);
        driver.findElement(locators.getCustomerclass()).sendKeys(customerClass);
        driver.findElement(locators.getSaveButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locators.getVerifyingdispatchcreated()));
    }

    public void assignTechnician(String technicianName) {
        driver.findElement(locators.getAssignTechnician()).click();
        driver.findElement(locators.getSearchingPerson()).sendKeys(technicianName);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locators.getSelectingPerson()));
        List<WebElement> technicianResults = driver.findElements(locators.getSelectingPerson());
        technicianResults.get(0).click();
        driver.findElement(locators.getAssignTechButton()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locators.getVerifyingAssignedTech()));
        driver.findElement(locators.getFinalAssignButton()).click();
    }

    public boolean verifyDispatchCreated(String customerName, String customerLocation, String customerClass) {
        return false;
    }
}
