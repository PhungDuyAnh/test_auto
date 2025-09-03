package anhtester.utils;

import anhtester.base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        System.out.println("Failure reason: " + result.getThrowable().getMessage());

        // Take screenshot on failure
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;
            if (baseTest.driver != null) {
                String screenshotPath = TestUtils.takeScreenshot(
                        baseTest.driver,
                        result.getMethod().getMethodName() + "_FAILED"
                );
                if (screenshotPath != null) {
                    System.out.println("Screenshot saved: " + screenshotPath);
                    // Set screenshot path in TestNG result for reporting
                    System.setProperty("screenshot.path", screenshotPath);
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println("Starting test suite: " + context.getName());
        System.out.println("Test started at: " + TestUtils.getCurrentTimestamp());

        // Create screenshots directory
        TestUtils.createTestDataDirectory(System.getProperty("user.dir") + "/screenshots");
        TestUtils.createTestDataDirectory(System.getProperty("user.dir") + "/test-output");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println("Finished test suite: " + context.getName());
        System.out.println("Test completed at: " + TestUtils.getCurrentTimestamp());

        // Print test summary
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        System.out.println("=== Test Summary ===");
        System.out.println("Total tests: " + total);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Skipped: " + skipped);
        System.out.println("Success rate: " + (passed * 100 / total) + "%");
    }
}
