package pages;

import constants.locators.AffordabilityCalculatorPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HelperTools.ElementPresenceChecker;

import java.util.ArrayList;

public class AffordabilityCalculatorPage {
    private WebDriver driver;
    private By locationInput = By.xpath(AffordabilityCalculatorPageConstants.INPUT_LOCATION);
    private By firstLocationSuggestion = By.cssSelector(AffordabilityCalculatorPageConstants.FIRST_LOCATION_SUGGESTION);
    private By viewAllHomesOnMapButton = By.cssSelector(AffordabilityCalculatorPageConstants.VIEW_ALL_HOMES_ON_MAP_BUTTON);
    private By maxEstimatedPrice = By.cssSelector(AffordabilityCalculatorPageConstants.MAX_ESTIMATED_PRICE);
    public AffordabilityCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getMaxEstimatedPrice() {
        String price = driver.findElement(maxEstimatedPrice).getText();
        return Integer.parseInt(price.replaceAll("[\\D]", ""));
    }

    public void inputLocation(String searchText) {
        ElementPresenceChecker.waitUntilVisible(driver, locationInput);
        driver.findElement(locationInput).sendKeys(searchText);
    }

    public void searchFirstSuggestionOfInputtedLocation() {
        driver.findElement(locationInput).sendKeys(Keys.ENTER);
        ElementPresenceChecker.waitUntilVisible(driver, firstLocationSuggestion);
        driver.findElement(firstLocationSuggestion).click();
    }

    public HomesForSalePage clickViewAllHomesOnMapButton() {
        ElementPresenceChecker.waitUntilVisible(driver, viewAllHomesOnMapButton);
        driver.findElement(viewAllHomesOnMapButton).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        System.out.println(driver.getCurrentUrl());
        return new HomesForSalePage(driver);
    }
}
