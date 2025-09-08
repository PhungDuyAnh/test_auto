package demoblaze.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    // Cung cấp 1 instance duy nhất (singleton) của ExtentReports để dùng chung trong toàn bộ test run
    private static volatile ExtentReports extent;

    // Đường dẫn
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    private ExtentManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentManager.class) {
                if (extent == null) {
                    // ExtentSparkReporter là reporter tạo báo cáo HTML
                    ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
                    spark.config().setTheme(Theme.DARK);
                    spark.config().setDocumentTitle("DemoBlaze Test Report");
                    spark.config().setReportName("Automation Test Results");
                    spark.config().setEncoding("utf-8");

                    // Nối reporter với ExtentReports
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
            // Khai báo null để lần sau gọi getInstance() có thể tái tạo report mới (ví dụ giữa các test run)
            extent = null;
        }
    }
}