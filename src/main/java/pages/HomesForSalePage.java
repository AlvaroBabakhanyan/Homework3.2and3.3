package pages;

import constants.locators.HomesForSalePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HelperTools.ElementPresenceChecker;

import java.util.ArrayList;
import java.util.List;

public class HomesForSalePage {
    private WebDriver driver;
    private By sortBySquareFeetButton = By.xpath(HomesForSalePageConstants.SORT_BY_SQUARE_FEET_BUTTON);
    private By tableButton = By.cssSelector(HomesForSalePageConstants.TABLE_BUTTON);
    private By photosButton = By.xpath(HomesForSalePageConstants.PHOTOS_BUTTON);
    private By mapTabButton = By.xpath(HomesForSalePageConstants.MAP_TAB_BUTTON);

    public HomesForSalePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTableButton() {
        if (!driver.findElements(mapTabButton).isEmpty() &&
            !driver.findElement(mapTabButton).getText().contains("isClosed")) {
            driver.findElement(mapTabButton).click();
        }
        ElementPresenceChecker.waitUntilVisible(driver, tableButton);
        driver.findElement(tableButton).click();
    }

    public void clickTableSortBySqFtButton() {
        ElementPresenceChecker.waitUntilVisible(driver, sortBySquareFeetButton);
        driver.findElement(sortBySquareFeetButton).click();
    }

    public void clickPhotosButton() {
        ElementPresenceChecker.waitUntilVisible(driver, photosButton);
        driver.findElement(photosButton).click();
    }

    public String getHomeCardAddress(int index) {
        By homeCard = By.id(HomesForSalePageConstants.HOME_CARD + index);
        ElementPresenceChecker.waitUntilVisible(driver, homeCard);
        WebElement we = driver.findElement(homeCard);
        return we.findElement(By.className(HomesForSalePageConstants.HOME_CARD_ADDRESS)).getText();
    }

    public int getHomeCardSquareFeet(int index) {
        ElementPresenceChecker.waitUntilVisible(driver, By.id(HomesForSalePageConstants.HOME_CARD + index));
        By homeCard = By.id(HomesForSalePageConstants.HOME_CARD + index);

        List<WebElement> stats = driver.findElement(homeCard).findElements(By.className("stats"));
        String areaSqFt = stats.get(2).getText();
        return Integer.parseInt(areaSqFt.replaceAll("[\\D]", ""));
    }

    public PropertyDetailsPage clickHomeCard(int index) {
        By homeCard = By.id(HomesForSalePageConstants.HOME_CARD + index);
        ElementPresenceChecker.waitUntilVisible(driver, homeCard);
        driver.findElement(homeCard).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(1));

        return new PropertyDetailsPage(driver);
    }
}

