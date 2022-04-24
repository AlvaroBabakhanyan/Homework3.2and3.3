package pages;

import constants.locators.HomePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HelperTools.ElementPresenceChecker;


public class HomePage {
    private WebDriver driver;
    private By searchBar = By.id(HomePageConstants.SEARCH_BAR);
    private By searchButton = By.cssSelector(HomePageConstants.SEARCH_BUTTON);
    private By buySubmenu = By.cssSelector(HomePageConstants.BUY_SUBMENU);
    private By firstSuggestion = By.xpath(HomePageConstants.FIRST_SUGGESTION);
    private By unsuccessfulSearchMessage = By.cssSelector(HomePageConstants.UNSUCCESSFUL_SEARCH_MESSAGE);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputInSearchBar(String searchText) {
        ElementPresenceChecker.waitUntilClickable(driver, searchBar);
        driver.findElement(searchBar).sendKeys(searchText);
    }

    public HomesForSalePage clickSearchButton() {
        driver.findElement(searchButton).click();
        return new HomesForSalePage(driver);
    }

    public HomesForSalePage inputSearchKeywordAndClickFirstSearchSuggestion(String keyword) {
        driver.findElement(searchBar).sendKeys(keyword);
        ElementPresenceChecker.waitUntilClickable(driver, firstSuggestion);
        driver.findElement(firstSuggestion).click();
        return new HomesForSalePage(driver);
    }

    public AffordabilityCalculatorPage clickBuy_affordabilityCalculator() {
        Actions buy1 = new Actions(driver);
        WebElement buyMenu = driver.findElement(buySubmenu);
        buy1.moveToElement(buyMenu).perform();

        WebElement subMenu = driver.findElement(By.linkText("Affordability calculator"));
        buy1.moveToElement(subMenu);
        buy1.click().build().perform();

        return new AffordabilityCalculatorPage(driver);
    }

    public String getUnsuccessfulSearchMessage() {
        ElementPresenceChecker.waitUntilVisible(driver, unsuccessfulSearchMessage);
        return driver.findElement(unsuccessfulSearchMessage).getText();
    }
}