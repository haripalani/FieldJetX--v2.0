package fieldjetx.v2.POM;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Utility {
    private static ExtentReports extent;
    private static String reportName;

    public static void initExtentReport(String reportName) {
        // Set the report name
        Utility.reportName = reportName;

        // Create an instance of the ExtentReports class
        extent = new ExtentReports();

        // Create an instance of the ExtentHtmlReporter class, which will create the HTML report file
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportName);

        // Attach the HTML report file to the ExtentReports instance
        extent.attachReporter(htmlReporter);

        // Add system information to the report
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public static void addScreenshot(ExtentTest test, WebDriver driver) throws IOException {
        // Get the current timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        // Create a screenshot file with the test name and timestamp
        String screenshotName = test.getModel().getName() + " " + timestamp + ".png";
        Path screenshotPath = Paths.get("screenshots", screenshotName);
        Files.createDirectories(screenshotPath.getParent());
        File screenshotFile = screenshotPath.toFile();

        // Take a screenshot and save it to the screenshot file
        takeScreenshot(screenshotFile, driver);

        // Add the screenshot to the test report
        test.log(Status.INFO, "Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath.toString()).build());
    }

    private static void takeScreenshot(File screenshotFile, WebDriver driver) {
        try {
            // Take a screenshot using the WebDriver interface
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File tempFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Copy the temporary file to the screenshot file
            ImageIO.write(ImageIO.read(tempFile), "png", screenshotFile);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "screenshots/" + UUID.randomUUID().toString() + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }

    public static ExtentReports getExtentReport() {
        return extent;
    }

    public static String getReportName() {
        return reportName;
    }
}
