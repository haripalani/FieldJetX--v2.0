package fieldjetx.v2.POM;

import com.aventstack.extentreports.ExtentReports;
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

    public static ExtentReports getExtentReport() {
        return extent;
    }

    public static String getReportName() {
        return reportName;
    }
}
