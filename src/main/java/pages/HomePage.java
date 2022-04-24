package pages;

import constants.locators.HomePageConstants;
import constants.urls.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HelperTools.ElementPresenceChecker;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By searchBar = By.id(HomePageConstants.SEARCH_BAR);
    private By searchButton = By.cssSelector(HomePageConstants.SEARCH_BUTTON);
    private By buySubmenu = By.cssSelector(HomePageConstants.BUY_SUBMENU);
    private By unsuccessfulSearchWindow = By.cssSelector("body.customer-facing.transparent-header.route-Homepage.dialog-shown:nth-child(2) div.dialogsContainer div.dialog-wrap div.Dialog.v83.shown.fixed.searchDisambigDialog.standard div.cell > div.guts");
    private By firstSuggestion = By.xpath("//body/div[@id='content']/div[8]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/a[1]");
    private String unsuccessfulSearchMessage;

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
        return unsuccessfulSearchMessage;
    }
}