package fieldjetx.v2.POM;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Utility {
    private static int passedTests = 0;
    private static int failedTests = 0;
    private static int skippedTests = 0;

    @BeforeSuite
    public void setUp() {
        // Set up the report
        Reporter.log("<html><head><title>TestNG Report</title></head><body>", true);
    }

    @Test
    public void test1() {
        // Some test code
        Assert.assertTrue(true);
        passedTests++;
    }

    @Test
    public void test2() {
        // Some test code
        Assert.assertTrue(false);
        failedTests++;
    }

    @Test(enabled = false)
    public void test3() {
        // Some test code
        skippedTests++;
    }

    @AfterSuite
    public void tearDown() {
        // Generate the report
        Reporter.log("<h1>Test Results:</h1>", true);
        Reporter.log("<p>Passed Tests: " + passedTests + "</p>", true);
        Reporter.log("<p>Failed Tests: " + failedTests + "</p>", true);
        Reporter.log("<p>Skipped Tests: " + skippedTests + "</p>", true);
        Reporter.log("</body></html>", true);
    }
}
