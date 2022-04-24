package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HelperTools.ElementPresenceChecker;

public class PropertyDetailsPage {
    private WebDriver driver;
    private By propertyValue = By.className("statsValue");

    public PropertyDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getPropertyValue() {
        ElementPresenceChecker.waitUntilVisible(driver, propertyValue);
        String price = driver.findElement(propertyValue).getText();
        return Integer.parseInt(price.replaceAll("[\\D]", ""));
    }
}
