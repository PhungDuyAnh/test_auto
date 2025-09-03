package demoblaze.test.order;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demoblaze.utils.ExtentManager;

public class TestListener implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Tao một test mới trong ExtentReports cho mỗi test method
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Khi test chạy thành công, ghi log với trạng thái PASS vào report.
        test.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Khi test bị lỗi, ghi log với trạng thái FAIL và thông tin lỗi.
        test.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        // Đính kèm screenshot nếu có
        Object testInstance = result.getInstance();
        try {
            Class<?> baseTestClass = Class.forName("demoblaze.base.BaseTest");
            if (baseTestClass.isInstance(testInstance)) {
                String screenshotPath = (String) baseTestClass.getMethod("takeScreenshot", String.class)
                        .invoke(testInstance, result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            test.get().log(Status.WARNING, "Không thể chụp screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Khi test bị skip, ghi log với trạng thái SKIP và lý do skip
        test.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Khi toàn bộ test trong suite kết thúc, gọi extent.flush() để ghi dữ liệu ra file report HTML
        extent.flush();
    }
}
