package demoblaze.test.order;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    private String testName(ITestResult result) {
        // result.getName() trả về tên method, bạn có thể đổi sang result.getMethod().getMethodName()
        return result.getName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=== onTestStart ===");
        System.out.println("Test started: " + testName(result));
        if (result.getParameters() != null && result.getParameters().length > 0) {
            System.out.println("Parameters: " + Arrays.toString(result.getParameters()));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("=== onTestSuccess ===");
        System.out.println("The name of the testcase passed is : " + testName(result));
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("Duration (ms): " + duration);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("=== onTestFailure ===");
        System.out.println("The name of the testcase failed is : " + testName(result));
        Throwable t = result.getThrowable();
        if (t != null) {
            System.out.println("Failure message: " + t.getMessage());
            // Nếu muốn in full stack trace:
            t.printStackTrace(System.out);
        }
        if (result.getParameters() != null && result.getParameters().length > 0) {
            System.out.println("Parameters: " + Arrays.toString(result.getParameters()));
        }
        // Capture screenshot on failure if test class is BaseTest or subclass
        Object testInstance = result.getInstance();
        try {
            Class<?> baseTestClass = Class.forName("demoblaze.base.BaseTest");
            if (baseTestClass.isInstance(testInstance)) {
                String screenshotPath = (String) baseTestClass.getMethod("takeScreenshot", String.class)
                        .invoke(testInstance, testName(result));
                System.out.println("Screenshot captured: " + screenshotPath);
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("=== onTestSkipped ===");
        System.out.println("The name of the testcase Skipped is : " + testName(result));
        Throwable t = result.getThrowable();
        if (t != null) {
            System.out.println("Skip reason: " + t.getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("=== onTestFailedButWithinSuccessPercentage ===");
        System.out.println("Test failed but within success percentage: " + testName(result));
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== onStart ===");
        System.out.println("Starting test context: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== onFinish ===");
        System.out.println("Finished test context: " + context.getName());
        System.out.println("Total tests run: " + context.getAllTestMethods().length);
    }
}
