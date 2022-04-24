package pages;

import constants.locators.AffordabilityCalculatorPageConstants;
import constants.locators.HomePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

// Home -> Affordability Calculator -> See House on the map -> Property Page
// Check if this 4 pages are a good idea
public class AffordabilityCalculatorPage {
    private WebDriver driver;
    private By locationInput = By.xpath(AffordabilityCalculatorPageConstants.INPUT_LOCATION);
    private By firstLocationSuggestion = By.cssSelector(AffordabilityCalculatorPageConstants.FIRST_LOCATION_SUGGESTION);
    private By viewAllHomesOnMapButton = By.cssSelector(AffordabilityCalculatorPageConstants.VIEW_ALL_HOMES_ON_MAP_BUTTON);
    private By maxEstimatedPrice = By.cssSelector("body.customer-facing.route-MerchPage:nth-child(2) section.HomeAffordabilityCalculatorWidget.padding-top-largest.hac-sell-info-variant div.HomeAffordabilityCalculator div.row-center:nth-child(1) div.calculator-container.col-12 div.right-panel:nth-child(2) div.AffordabilityEstimation.hac-sell-info-variant div.affordability-estimation-price-section div.estimated-price-container div.EstimatedPrice.calculation-result > span.estimated-price-value.value");

    public AffordabilityCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getMaxEstimatedPrice() {
        String price = driver.findElement(maxEstimatedPrice).getText();
        return Integer.parseInt(price.replaceAll("[\\D]", ""));
    }

    public void inputLocation(String searchText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locationInput));
        driver.findElement(locationInput).sendKeys(searchText);
    }

    public void searchFirstSuggestionOfInputtedLocation() {
        driver.findElement(locationInput).sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(firstLocationSuggestion));
        driver.findElement(firstLocationSuggestion).click();
    }

    public HomesForSalePage clickViewAllHomesOnMapButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(viewAllHomesOnMapButton));
        driver.findElement(viewAllHomesOnMapButton).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        System.out.println(driver.getCurrentUrl());
        return new HomesForSalePage(driver);
    }
}
