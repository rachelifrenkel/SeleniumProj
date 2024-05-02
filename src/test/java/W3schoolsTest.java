import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class W3schoolsTest extends BaseTest {

    private final TablePage tablePage;
    private static final ConfigReader configReader;
    private static final String tableName;
    private static final String searchColumn;
    private static final String searchText;
    private static final String returnColumnText;
    private static final String expectedText;

    static {
        configReader = new ConfigReader("src/main/resources/tableData.properties");
        tableName = configReader.getProperty("table_name");
        searchColumn = configReader.getProperty("search_column");
        searchText = configReader.getProperty("search_text");
        returnColumnText = configReader.getProperty("return_column_text");
        expectedText = configReader.getProperty("expected_text");
    }

    @BeforeAll
    public void beforeAll(){
        super.beforeAll();
        final String url = urlConfigProp.getProperty("w3schools_base_url");
        driver.get(url);
    }

    public W3schoolsTest() {
        tablePage = new TablePage(driver);
    }


    @Test
    @DisplayName("get table index and column,check the cell, verify get the expected text")
    public void testTable() {
        boolean isTableCellCorrect = tablePage.verifyTableCellTextWrapper(tableName, Integer.parseInt(searchColumn),
                searchText, Integer.parseInt(returnColumnText),
                expectedText);
        assertTrue(isTableCellCorrect, "verify get the expected text");
    }

    @Test
    @DisplayName("get table index and column,check the cell by xpath, verify get the expected text")
    public void testTableXpath() {
        String TableCellCorrect;
        TableCellCorrect = tablePage.getTableCellTextByXpathWrapper(tableName, Integer.parseInt(searchColumn),
                searchText, Integer.parseInt(returnColumnText));
        assertEquals(TableCellCorrect, expectedText, "verify get the expected text");
    }

}
