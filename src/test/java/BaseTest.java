import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({ExtentReportListener.class})
public class BaseTest {
    protected static final ConfigReader urlConfigProp;

    static {
        urlConfigProp = new ConfigReader("src/main/resources/urlDriver.properties");
    }

    public WebDriver driver;

    public BaseTest() {
        driver = new ChromeDriver();
    }

    @BeforeAll
    public void beforeAll() {
        driver.manage().window().maximize();
    }


    @AfterAll
    public void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

}

