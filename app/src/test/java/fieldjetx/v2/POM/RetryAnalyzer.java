package fieldjetx.v2.POM;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;
   
/*  This method is an override of the 'retry' method from the IRetryAnalyzer interface.
The purpose of this method is to retry a failed test case if the test case failed and the number of retries is less than MAX_RETRY_COUNT.*/
@Override
public boolean retry(ITestResult result) {
    if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) { // If the test case failed and number of retries is less than MAX_RETRY_COUNT
        retryCount++; // Increment the number of retries
        try {
            TimeUnit.SECONDS.sleep(5); // Add a delay of 5 seconds between test retries
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true; // Retry the test case
    } else if (!result.isSuccess() && retryCount == MAX_RETRY_COUNT) { // If the test case failed and number of retries is equal to MAX_RETRY_COUNT
        retryCount++; // Increment the number of retries
        String testName = result.getName(); // Get the name of the failed test case
        WebDriver driver = new ChromeDriver(); // Create a new instance of the ChromeDriver
        try {
            String screenshotPath = captureScreenshot(testName, driver); // Capture a screenshot of the failed test case
            System.out.println("Screenshot captured: " + screenshotPath); // Print the path of the screenshot
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage()); // Print an error message if failed to capture screenshot
        } finally {
            driver.quit(); // Quit the ChromeDriver
        }
    }
    return false; // Do not retry the test case
}


    private String captureScreenshot(String testName, WebDriver driver) throws IOException {
        // Get the current timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        // Create a screenshot file with the test name and timestamp
        String screenshotName = testName + " " + timestamp + ".png";
        String dest = "screenshots/" + screenshotName;
        File screenshotFile = new File(dest);

        // Take a screenshot and save it to the screenshot file
        takeScreenshot(screenshotFile, driver);

        return dest;
    }

    private void takeScreenshot(File screenshotFile, WebDriver driver) {
        try {
            // Take a screenshot using the WebDriver interface
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File tempFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Copy the temporary file to the screenshot file
            ImageIO.write(ImageIO.read(tempFile), "png", screenshotFile);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        } finally {
            // Quit the WebDriver instance
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
    