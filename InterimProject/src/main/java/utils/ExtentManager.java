package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/ExtentReport.html");

            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("OrangeHRM Tests");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}