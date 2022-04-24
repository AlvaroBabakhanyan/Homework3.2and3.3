package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomesForSalePage;

public class HomesForSalePageTests extends BaseTest {

    @Test
    public void testSort() {
        HomesForSalePage homesForSalePage = homePage.inputSearchKeywordAndClickFirstSearchSuggestion("New York");

        homesForSalePage.clickTableButton();
        //sort by ascending order
        homesForSalePage.clickTableSortBySqFtButton();
        //sort by descending order
        homesForSalePage.clickTableSortBySqFtButton();

        homesForSalePage.clickPhotosButton();

        int sqFt0 = homesForSalePage.getHomeCardSquareFeet(0);
        int sqFt1 = homesForSalePage.getHomeCardSquareFeet(1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(sqFt0 >= sqFt1);
        System.out.println();
    }
}
