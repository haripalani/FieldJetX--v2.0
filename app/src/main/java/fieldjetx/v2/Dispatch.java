package fieldjetx.v2;

import java.time.Duration;

import org.openqa.selenium.By;
// import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Dispatch {
    ChromeDriver driver;
    String url ="http://18.188.111.206/#/auth/login?returnUrl=%2Fdispatch%2Fsearch";
    public Dispatch(ChromeDriver driver) {
        this.driver = driver;
    }    public void navigateToDispatchPage(){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }   
    }
    public boolean clickOnService() {
        try {
            ServicePage servicePage = new ServicePage(driver);
    // Click on the Service button
    servicePage.clickServiceButton();

    // Click on the Dispatch button
    servicePage.clickDispatchButton();

    // Click on the New button dropdown
    servicePage.clickNewDropdownButton();

    // Click on the New Dispatch button
    servicePage.clickNewDispatchButton();

    // Fill customer name
    servicePage.fillcustomername();

    // Fill customer location
    servicePage.fillcustomerlocation();

    // Fill customer class
    servicePage.fillcustomerclass();

    // Click on the Save button
    servicePage.clickSave();

    // Verify that dispatch was created
    servicePage.Verifydispatchcreated();

    // Click on the Assign Technician button
    servicePage.clickAssignTechnician();

    // Search for a person
    servicePage.searchPerson();

    // Select a person
    servicePage.selectPerson();

    // Assign technician to the dispatch
    servicePage.AssignTech();

    // Verify that technician was assigned
    servicePage.verifyassignedTech();

    // Finalize assignment
    servicePage.finalassign();

    // Return true if everything was successful
    return true;
} catch (Exception e) {
    // Print any error message and return false if there was an exception
    System.out.println(e);
    return false;
}

    }
    
    // This is a Java class named ServicePage. It uses the Selenium WebDriver API to interact with a web page.
class ServicePage {
    // This private instance variable is a WebDriverWait object with a timeout of 10 seconds.
    private WebDriverWait wait;

    // This is the constructor for the ServicePage class.
    // It takes a WebDriver object and initializes the wait object with a timeout of 10 seconds.
    // It also initializes the PageFactory with the driver and the current object.
    public ServicePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // This private instance variable represents a service button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='left-panel']/nav/ul/li[3]/a/span")
    private WebElement serviceButton;

    // This private instance variable represents a dispatch button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='left-panel']/nav/ul/li[3]/ul/li[1]/a/span")
    private WebElement dispatchButton;

    // This private instance variable represents a new dropdown button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='main']/dqoi-main/div[1]/div[2]/div[3]/kendo-splitbutton/button[2]")
    private WebElement newDropdown;

    // This private instance variable represents a new dispatch button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='82fbff62-aa52-4695-a247-c2a9080ee7d2']/ul/li[1]")
    private WebElement newDispatchButton;

    // This private instance variable represents a customer name field on the page, located using ID.
    @FindBy(id = "k-cd10f0bb-276b-4e8f-9ff5-0bfb232bc18b")
    private WebElement customerName;

    // This private instance variable represents a customer location field on the page, located using ID.
    @FindBy(id = "k-81f0bd75-d796-4112-b7dd-70bb5107319f")
    private WebElement customerLocation;

    // This private instance variable represents a customer class field on the page, located using ID.
    @FindBy(id = "k-18fa0993-f9d9-4619-b996-25e379887ec1")
    private WebElement customerclass;

    // This private instance variable represents a save button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='k-tabstrip-tabpanel-0']/dispatches/div[1]/div/div[11]/span[2]/button")
    private WebElement saveButton;

    // This private instance variable represents a verifying dispatch created message on the page, located using XPath.
    @FindBy(xpath = "//div[text()='DSP: Ready for Dispatch']")
    private WebElement Verifyingdispatchcreated;

    // This private instance variable represents an assign technician button on the page, located using XPath.
    @FindBy(xpath ="//*[@id='k-tabstrip-tabpanel-0']/dispatches/div[1]/div/div[5]/button")
    private WebElement assignTechnician;

    // This private instance variable represents a searching person field on the page, located using ID.
    @FindBy(id = "k-c299945d-ac7d-4fdd-9e96-d8a8da28e79b")
    private WebElement searchingPerson;

    // This private instance variable represents a selecting person button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='2e711175-c52f-478c-93e3-0d853d013644-Brian Wright                                      ::795D236B-E8DD-4513-852D-231267F17DCC']/div")
    private WebElement selectingPerson;

    // This private instance variable represents an assign tech button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='k-tabstrip-tabpanel-0']/dispatches/cp-window/kendo-window/div/section/cp-assign-tech/message-info-dialog/kendo-dialog/div[2]/kendo-dialog-actions/button[2]")
    private WebElement AssignTechButton;

    // This private instance variable represents a final assign button on the page, located using XPath.
    @FindBy(xpath = "//*[@id='k-tabstrip-tabpanel-0']/dispatches/cp-window/kendo-window/div/section/cp-assign-tech/form/div/div[4]/div[4]/div/div/div[2]/div/button")
    private WebElement finalassignbtn;

        public void clickServiceButton() {
            wait.until(ExpectedConditions.elementToBeClickable(serviceButton)).click();
        }
    
        public void clickDispatchButton() {
            wait.until(ExpectedConditions.elementToBeClickable(dispatchButton)).click();
        }
    
        public void clickNewDropdownButton() {
            wait.until(ExpectedConditions.elementToBeClickable(newDropdown)).click();
        }
    
        public void clickNewDispatchButton() {
            wait.until(ExpectedConditions.elementToBeClickable(newDispatchButton)).click();
        }

        public void fillcustomername() {
            wait.until(ExpectedConditions.elementToBeClickable(customerName)).sendKeys("101 market");
        }
        
        public void fillcustomerlocation() {
            wait.until(ExpectedConditions.elementToBeClickable(customerLocation)).sendKeys("101 Market - Otsego, MN");
        }

        public void fillcustomerclass() {
            wait.until(ExpectedConditions.elementToBeClickable(customerclass)).sendKeys("Carpentry Service");
        }

        public void clickSave() {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        }

        public boolean Verifydispatchcreated() {

            wait.until(ExpectedConditions.textToBePresentInElement(Verifyingdispatchcreated, "DSP: Ready for Dispatch"));
            return true;
        }
        public void clickAssignTechnician() {
            wait.until(ExpectedConditions.elementToBeClickable(assignTechnician)).click();
        }
        public void searchPerson() {
            wait.until(ExpectedConditions.elementToBeClickable(searchingPerson)).sendKeys("Brian Wright");
        }

        public void selectPerson() {
            wait.until(ExpectedConditions.elementToBeClickable(selectingPerson)).click();
        }
        public void AssignTech() {
            wait.until(ExpectedConditions.elementToBeClickable(AssignTechButton)).click();
        }
        public void verifyassignedTech() {
            WebElement cell = driver.findElement(By.cssSelector("td.person"));
            boolean namePresent = cell.getText().contains("Brian Wright");
            System.out.println(namePresent);
        }

        public void finalassign() {
            wait.until(ExpectedConditions.elementToBeClickable(finalassignbtn)).click();
        }
        
    }
    

}
