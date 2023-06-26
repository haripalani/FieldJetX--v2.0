package fieldjetx.v2.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
        PageFactory.initElements(driver, locators);
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

    public Object[][] getDispatchValue(String filePath, String sheetName) throws IOException {
    // Read the data from the Excel sheet
    FileInputStream fis = new FileInputStream(filePath);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    Sheet sheet = workbook.getSheet(sheetName);

    // Get a random row from the sheet
    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
    int random = (int) (Math.random() * (rowCount - 1) + 1);
    Row row = sheet.getRow(random);

    // Get the dispatch values from the row
    String customerName = row.getCell(0).getStringCellValue();
    String customerLocation = row.getCell(1).getStringCellValue();
    String customerClass = row.getCell(2).getStringCellValue();

    // Close the workbook
    workbook.close();

    // Return the dispatch values as a 2D object array
    return new Object[][] {{customerName, customerLocation, customerClass}};
    }

 
}
