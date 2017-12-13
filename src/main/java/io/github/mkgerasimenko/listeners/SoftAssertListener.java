package io.github.mkgerasimenko.listeners;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

@Slf4j
@SuppressWarnings("JavadocType")
public class SoftAssertListener implements IInvokedMethodListener {

    private static final ThreadLocal<SoftAssertions> THREAD_LOCAL_CONTAINER_FOR_SOFT_ASSERTIONS = new ThreadLocal<>();

    @Override
    public void beforeInvocation(final IInvokedMethod method, final ITestResult testResult) {
        THREAD_LOCAL_CONTAINER_FOR_SOFT_ASSERTIONS.set(new SoftAssertions());
    }

    @Override
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult) {
        if (method.getTestMethod().isTest() && testResult.getStatus() == ITestResult.SUCCESS) {
            try {
                getSoftAssert().assertAll();
            } catch (AssertionError e) {
                testResult.getTestContext().getPassedTests().removeResult(testResult.getMethod());
                testResult.setStatus(TestResult.FAILURE);
                testResult.setThrowable(e);
            }
        }
    }

    public static SoftAssertions getSoftAssert() {
        return THREAD_LOCAL_CONTAINER_FOR_SOFT_ASSERTIONS.get();
    }
}
