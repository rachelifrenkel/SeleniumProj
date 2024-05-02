import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void selectMenuItems(String topMenu, String subMenu) {
        if (subMenu != null && !subMenu.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable
                    (By.xpath("//a[@class='dropdown-toggle' and contains(text(),'" + topMenu + "')]")))
                    .click();

            wait.until(ExpectedConditions.elementToBeClickable
                            (By.xpath("//a[contains(text(),'" + subMenu + "')]")))
                    .click();
        } else wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(text(),'" + topMenu + "')]"))).click();

    }

    public void selectMenuItems(String menuAndSubMenu) {
        String[] menuItems = menuAndSubMenu.split(";");
        selectMenuItems(menuItems[0].trim(), menuItems[1].trim());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isSubMenuLoad(String previousUrl) {
        return !getCurrentUrl().equals(previousUrl);
    }

}
