package fieldjetx.v2.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import fieldjetx.v2.Login;
import java.io.FileInputStream;
import java.io.IOException;
// import java.util.ArrayList;
// import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testcase01 {
    private WebDriver driver;
    private Login loginPage;
    private Object[][] loginData;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // Set up the driver
        System.setProperty("webdriver.chrome.driver",
                "C:/Program Files (x86)/Java/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();

        // Initialize the Login page
        loginPage = new Login(driver);
    }

    @BeforeSuite
    public void loadData() throws IOException {
        // Call the getLoginData method and store the data in loginData variable
        loginData = getLoginData();
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver
        driver.quit();
    }

    @Test(dataProvider = "getLoginData", retryAnalyzer = RetryAnalyzer.class)
    public void testLogin(String username, String password) throws InterruptedException {
        // Navigate to the login page
        loginPage.navigateToLoginPage();

        // Perform login with the given username and password
        boolean isLoginSuccessful = loginPage.performlogin(username, password);

        // Verify that the login was successful
        Assert.assertTrue(isLoginSuccessful, "Login was not successful.");
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

    // Loop through each row and store the username and password in the object array
    for (int i = 0; i < rowCount; i++) {
        Row row = sheet.getRow(i);
        loginData[i][0] = row.getCell(0).getStringCellValue(); // Username is in column A
        loginData[i][1] = row.getCell(1).getStringCellValue(); // Password is in column B
    }

    // Close the workbook
    workbook.close();
    return loginData;
}

}
