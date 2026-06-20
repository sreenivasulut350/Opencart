package utilities;

import org.testng.ITestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener
{
    private static ExtentReports extent;
    // Declared private to protect the reference; added final for best practice
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String reportFolderPath = System.getProperty("user.dir") + "\\reports\\";
        File folder = new File(reportFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        String timestap = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFilePath = reportFolderPath + "ExtentReport-" + timestap + ".html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);

        sparkReporter.config().setDocumentTitle("Automation Execution Report");
        sparkReporter.config().setReportName("Functional Test Metrics");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA Production-Ready");
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        
        String browser = context.getCurrentXmlTest().getParameter("browser");
        if (browser != null) {
            extent.setSystemInfo("Browser", browser);
        }
        
        List<String> includegroup = context.getCurrentXmlTest().getIncludedGroups();
        if (includegroup != null && !includegroup.isEmpty()) {
            extent.setSystemInfo("Groups", includegroup.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Appending method name makes the report view cleaner than just class name
        String testName = result.getTestClass().getName() + " -> " + result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);
        
        test.set(extentTest); // Save to thread sandbox
        extentTest.assignCategory(result.getMethod().getGroups());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, result.getName() + " Test Executed Successfully");
        test.remove(); // 🩹 Clean up thread memory safely
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getName() + " Test Failed: " + result.getThrowable());
        
        try {
            String imgPath = new BaseClass().captureScreenshot(result.getName());
            //  Fixed the compiler error by adding .get()
            test.get().addScreenCaptureFromPath(imgPath); 
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            test.remove(); // 🩹 Clean up thread memory safely even if screenshot fails
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, result.getName() + " Test Skipped: " + result.getThrowable());
        test.remove(); // 🩹 Clean up thread memory safely
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
