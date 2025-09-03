package demoblaze.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static volatile ExtentReports extent;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    private ExtentManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentManager.class) {
                if (extent == null) {
                    ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
                    spark.config().setTheme(Theme.DARK);
                    spark.config().setDocumentTitle("DemoBlaze Test Report");
                    spark.config().setReportName("Automation Test Results");
                    spark.config().setEncoding("utf-8");
                    extent = new ExtentReports();
                    extent.attachReporter(spark);
                }
            }
        }
        return extent;
    }

    public static void close() {
        if (extent != null) {
            extent.flush();
            extent = null;
        }
    }
}