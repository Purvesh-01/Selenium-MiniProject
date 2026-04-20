package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    protected static ExtentReports extent;
    protected ExtentTest extentTest;

    @BeforeClass
    public void setUp() throws IOException {

        //  Initialize Extent Report
        extent = ExtentManager.getInstance();

        // Load config
        prop = new Properties();
        FileInputStream fis =
                new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);

        String browser = prop.getProperty("browser");

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void startTest(Method method) {
        //  Create ExtentTest per @Test method
        extentTest = extent.createTest(method.getName());
    }

    @AfterMethod
    public void updateTestResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test passed successfully");
        } else {
            extentTest.skip("Test skipped");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        extent.flush();
    }
}