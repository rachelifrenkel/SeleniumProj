import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Guru99Test extends BaseTest {
    private final MenuPage menuPage;
    private static final ConfigReader configReader;
    private static final String topMenu;
    private static final String subMenu;
    private static final String topSubMenu;

    static {
        configReader = new ConfigReader("src/main/resources/menuData.properties");
        topMenu = configReader.getProperty("top_menu");
        subMenu = configReader.getProperty("sub_menu");
        topSubMenu = configReader.getProperty("top_sub_menu");
    }

    @BeforeAll
    public void beforeAll() {
        super.beforeAll();
        final String url = urlConfigProp.getProperty("guru99_base_url");
        driver.get(url);
    }

    public Guru99Test() {
        menuPage = new MenuPage(driver);
    }


    @Test
    @DisplayName("click on top & sub menu, verify the url change")
    public void testMenu() {
        String previousUrl = menuPage.getCurrentUrl();
        menuPage.selectMenuItems(topMenu, subMenu);
        assertTrue(menuPage.isSubMenuLoad(previousUrl), "verify the url change");
    }

    @Test
    @DisplayName("click on top & sub menu - send in one string , verify the url change")
    public void testMenuOneString() {
        String previousUrl = menuPage.getCurrentUrl();
        menuPage.selectMenuItems(topSubMenu);
        assertTrue(menuPage.isSubMenuLoad(previousUrl), "verify the url change");
    }

    @AfterEach
    public void afterEach(){
        driver.navigate().back();
    }


}
