package fieldjetx.v2.Testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
// import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
// import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import fieldjetx.v2.Login;
import fieldjetx.v2.POM.Utility;
public class Testcase01 {
    private WebDriver driver;
    private Login loginPage;
    private Object[][] loginData;
    private ExtentReports extent;
    private ExtentTest test;

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

        // Initialize the ExtentReports instance using the Utility class
        Utility.initExtentReport("test-output/emailable-report.html");
        extent = Utility.getExtentReport();

        // Add system information to the report
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @AfterClass
    public void tearDown() {
        // Quit the driver
        driver.quit();
    
        // Generate the report
        extent.flush();
    
        // Get the status of the test suite execution
        if (Reporter.getOutput().contains("FAILURES!!!")) {
            // If any test has failed, set the exit status to 1
            System.exit(1);
        } else {
            // If all tests have passed, set the exit status to 0
            System.exit(0);
        }
    
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    

    @Test(dataProvider = "getLoginData", retryAnalyzer = RetryAnalyzer.class)
    public void testLogin(String username, String password) throws InterruptedException, IOException {
        // Create a new test in the report using the Utility class
        test = extent.createTest("Login Test: " + username);

        // Navigate to the login page
        loginPage.navigateToLoginPage();
        test.log(Status.INFO, "Navigated to the login page.");

        // Perform login with the given username and password
        boolean isLoginSuccessful = loginPage.performlogin(username, password);

        // Verify that the login was successful
        if (isLoginSuccessful) {
            test.pass("Login was successful.");
        } else {
            test.fail("Login was not successful.");
        }
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


