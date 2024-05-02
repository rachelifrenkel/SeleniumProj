import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TablePage extends BasePage {

    public TablePage(WebDriver driver) {
        super(driver);
    }

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {

        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElements(table.findElements(By.tagName("tr"))));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            if (cells.size() > searchColumn) {
                WebElement cell = cells.get(searchColumn);
                if (cell.getText().equals(searchText)) {
                    if (cells.size() > returnColumnText) {
                        return cells.get(returnColumnText).getText();
                    } else {
                        throw new IllegalArgumentException("Invalid return column index");
                    }
                }
            }
        }
        return null;
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String resultText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        return resultText.equals(expectedText);
    }

    public boolean verifyTableCellTextWrapper(String tableName, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(tableName)));
        return verifyTableCellText(table, searchColumn, searchText, returnColumnText, expectedText);
    }

    public String getTableCellTextByXpathWrapper(String tableName, int searchColumn, String searchText, int returnColumnText) {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(tableName)));
        return getTableCellTextByXpath(table, searchColumn, searchText, returnColumnText);
    }

    public String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) {

        String xpath = ".//tr[.//td[" + (searchColumn + 1) + "][contains(., '" + searchText + "')]]";

        WebElement row = table.findElement(By.xpath(xpath));

        String cellXpath = ".//td[" + (returnColumnText + 1) + "]";

        WebElement returnCell = row.findElement(By.xpath(cellXpath));

        return returnCell.getText();
    }
}

