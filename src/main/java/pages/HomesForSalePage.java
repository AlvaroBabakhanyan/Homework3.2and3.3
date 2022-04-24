package pages;

import constants.locators.HomesForSalePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HelperTools.ElementPresenceChecker;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomesForSalePage {
    private WebDriver driver;
    private By priceFilterButton = By.xpath("//body/div[@id='content']/div[8]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]");
    private By priceRangeInputMin = By.xpath("//body/div[@id='content']/div[8]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/span[1]/div[1]/input[1]");
    //private By priceRangeInputMax = By.cssSelector("body.customer-facing.photosMode.route-SearchPage:nth-child(2) div.map.showFullPageListView.collapsedList:nth-child(2) div.WideSidepaneHeader--container:nth-child(3) div.Photo.WideSidepaneHeader div.stickExposedFilterAndDescription.hideOnScroll div.desktopExposedSearchFiltersContainer form.ActionEmittingForm.form.decorate-required div.CustomFilter.inline-block.desktopExposedSearchFilter.desktopExposedPriceFilter.showPriceFilterHistogram:nth-child(2) div.Flyout.standard.v83.position-right.alignment-below.CustomFilter__flyout.transparent.standard div.flyout div.container div.CustomFilter__container div.SearchFormSection.Price div.SearchFormSection__container div.flex.align-center.inputRangeAfterHistogram span.field.text.Text.label-none.optional:nth-child(3) span.input div.value.text > input.text");
    //private By sortButton = By.xpath("//body/div[@id='content']/div[8]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/button[1]");

    private By sortBySquareFeetButton = By.xpath("//thead/tr[@id='tableView-table-header']/div[1]/th[7]/button[1]");
    private By tableButton = By.cssSelector("body.customer-facing.photosMode.route-SearchPage:nth-child(2) div.map.showFullPageListView.collapsedList:nth-child(2) div.WideSidepaneHeader--container:nth-child(3) div.Photo.WideSidepaneHeader div.stickExposedFilterAndDescription.hideOnScroll div.descriptionAndModeContainer div.ModeToggler.displayModeToggler button.ModeOption.button-text:nth-child(2) > span.modeOptionInnard.table");
    private By photosButton = By.xpath("//span[contains(text(),'Photos')]");
    public HomesForSalePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTableButton() {
        if (!driver.findElements(By.xpath("//body/div[@id='content']/div[8]/div[2]/div[1]/div[1]/div[1]/div[1]/*[1]")).isEmpty() &&
            !driver.findElement(By.xpath("//body/div[@id='content']/div[8]/div[2]/div[1]/div[1]/div[1]/div[1]/*[1]")).getText().contains("isClosed")) {
            driver.findElement(By.xpath("//body/div[@id='content']/div[8]/div[2]/div[1]/div[1]/div[1]/div[1]/*[1]")).click();
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
        return we.findElement(By.className(HomesForSalePageConstants.FIRST_HOME_CARD_ADDRESS)).getText(); //problemmmmmmmmmmmmmmmmmmmmm
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

    public void clickPriceFilterButton() {
        driver.findElement(priceFilterButton).click();
    }

    public void setMinPrice(int minPrice) {
        Select price = new Select(driver.findElement(priceFilterButton));
        if (price.isMultiple()) {
            price.selectByVisibleText("Enter min");
        }
    }

    /*
    public HomesForSalePage clickSortBySqFt() {
        ElementPresenceChecker.waitUntilVisible(driver, sortButton);
        driver.findElement(sortButton).click();
        //ElementPresenceChecker.waitUntilVisible(driver, sortBySquareFeetButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.findElement(sortOptions).findElement(By.linkText("Square Feet"));
        driver.findElement(sortBySquareFeetButton).click();
        return new HomesForSalePage(driver);
    }
    */
/*
    public HomesForSalePage clickSortBy() {
        driver.findElement(sortBySquareFeetButton).click();
        return new HomesForSalePage(driver);
    }
*/
}

