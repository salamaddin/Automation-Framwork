package Utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporter {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter htmlReporter;
    

    public Reporter(String reportPath) {
        htmlReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void createTest(String testName, String testDescription) {
        test = extent.createTest(testName, testDescription);
    }

    public static void pass(String message) {
        test.pass(message);
    }

    public static void fail(String message) {
        test.fail(message);
    }

    public static void info(String message) {
        test.info(message);
    }
    
    public static void attachScreenshot(String screenshotName, String description) throws IOException {
    	test.info("", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
        
    }

    public static void closeReport() {
        extent.flush();
    }
}
