package fieldjetx.v2.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import fieldjetx.v2.Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Testcase01 {
    private WebDriver driver;
    private Login loginPage;

    @BeforeClass
    public void setUp() {
        // Set up the driver
        System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Java/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();

        // Initialize the Login page
        loginPage = new Login(driver);
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

    @DataProvider
    public Iterator<Object[]> getLoginData() throws IOException {
        // Read the data from the Excel sheet
        FileInputStream fis = new FileInputStream("C:/Users/TLTUser/Desktop/QA Automation/FieldJetX--v2.0/app/src/test/java/fieldjetx/v2/Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("login details");


        // Store the data in a List of Object arrays
        ArrayList<Object[]> loginData = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            String username = formatter.formatCellValue(row.getCell(0));
            String password = formatter.formatCellValue(row.getCell(1));
            loginData.add(new Object[]{username, password});
        }

        // Return an Iterator over the List of Object arrays
        return loginData.iterator();
    }
}
