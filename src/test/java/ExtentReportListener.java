import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.extension.*;

public class ExtentReportListener implements TestWatcher, BeforeAllCallback, BeforeEachCallback, AfterAllCallback {
    private ExtentReports extent;
    private ExtentSparkReporter spark;
    private ExtentTest extentTest;


    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("target/" + context.getTestClass().get().getName() + ".html");
        extent.attachReporter(spark);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        extentTest = extent.createTest(context.getDisplayName());
    }

    public void testSuccessful(ExtensionContext context) {
        extentTest.log(Status.PASS, "test pass successfully");
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        String errorMessage = "Test Failed";
        if (context.getExecutionException().isPresent()) {
            errorMessage = context.getExecutionException().get().getMessage();
        }
        extentTest.log(Status.FAIL, errorMessage);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        extent.flush();
    }

}
