package fieldjetx.v2.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fieldjetx.v2.Actions.DispatchCreationflow;
import fieldjetx.v2.Actions.Loginactions;
import fieldjetx.v2.POM.RetryAnalyzer;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testcase01 {
    private WebDriver driver;
    private Loginactions loginPage;
    private DispatchCreationflow dispatchPage;

    private Object[][] loginData;
    private Object[][] dispatchData;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // Set up the driver
        System.setProperty("webdriver.chrome.driver",
                "C:/Program Files (x86)/Java/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();

        // Initialize the Login, Dispatch and Customer pages
        loginPage = new Loginactions(driver);
        dispatchPage = new DispatchCreationflow(driver);
        
    }

    @BeforeSuite
    public void loadData() throws IOException {
        // Call the getLoginData method and store the data in loginData variable
        loginData = getLoginData();
        // Call the getDispatchData method and store the data in dispatchData variable
        dispatchData = getDispatchData();
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver
        driver.quit();
    }

    @Test(dataProvider = "getLoginData", retryAnalyzer = RetryAnalyzer.class)
    public void testLoginAndCreateDispatch(String username, String password) throws InterruptedException {
        // Navigate to the login page
        loginPage.navigateToLoginPage();

        // Perform login with the given username and password
        boolean isLoginSuccessful = loginPage.performlogin(username, password);

        // Verify that the login was successful
        Assert.assertTrue(isLoginSuccessful, "Login was not successful.");

        // Get the customer name, location and class from the dispatch data
        String customerName = dispatchData[0][0].toString();
        String customerLocation = dispatchData[0][1].toString();
        String customerClass = dispatchData[0][2].toString();

        // Create a new dispatch
        dispatchPage.clickDispatch();
        dispatchPage.clickNewDispatch();
        dispatchPage.fillDispatchForm(customerName, customerLocation, customerClass);

        // Verify that the dispatch was created successfully
        boolean dispatchResult = dispatchPage.verifyDispatchCreated(customerName, customerLocation, customerClass);
        Assert.assertTrue(dispatchResult, "Dispatch creation failed");
    }

    @DataProvider(name = "getLoginData")
    public static Object[][] getLoginData() throws IOException {
        // Read the data from the Excel sheet
        FileInputStream fis = new FileInputStream("C:/Users/TLTUser/Desktop/QA Automation/FieldJetX--v2.0/app/src/test/resources/Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Get the number of rows in the sheet
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

        // Create a 2D object array to store the login data
        Object[][] loginData = new Object[rowCount][2];

        // Loop through each row and store the login data in the object array
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            loginData[i][0] = row.getCell(0).getStringCellValue(); // Username is in column A
             loginData[i][1] = row.getCell(1).getStringCellValue(); // Password is in column B
        }
// Close the workbook
workbook.close();
return loginData;
}

@DataProvider(name = "getDispatchData")
public static Object[][] getDispatchData() throws IOException {
// Read the data from the Excel sheet
FileInputStream fis = new FileInputStream("C:/Users/TLTUser/Desktop/QA Automation/FieldJetX--v2.0/app/src/test/resources/Data.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(fis);
Sheet sheet = workbook.getSheetAt(1);

// Get the number of rows in the sheet
int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

// Create a 2D object array to store the dispatch data
Object[][] dispatchData = new Object[rowCount][3];

// Loop through each row and store the dispatch data in the object array
for (int i = 0; i < rowCount; i++) {
    Row row = sheet.getRow(i);
    dispatchData[i][0] = row.getCell(0).getStringCellValue(); // Customer Name is in column A
    dispatchData[i][1] = row.getCell(1).getStringCellValue(); // Customer Location is in column B
    dispatchData[i][2] = row.getCell(2).getStringCellValue(); // Customer Class is in column C
}

// Close the workbook
workbook.close();
return dispatchData;
}
}