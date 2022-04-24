package base;

import constants.urls.Urls;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class BaseTest {
    private RemoteWebDriver driver;
    protected HomePage homePage;

    /*
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Urls.SUT_URL);

        homePage = new HomePage(driver);
    }
    */

    @BeforeClass
    @Parameters("browser")
    public void setUpWebDriver(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);
        driver.get(Urls.SUT_URL);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void navigateToHomePage() {
        driver.get(Urls.SUT_URL);
    }

    @AfterMethod
    public void recordFailure(ITestResult iTestResult) {
        if (ITestResult.FAILURE == iTestResult.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("resources\\screenshots\\" + iTestResult.getName() + ".png");
            try {
                Files.copy(screenshot.toPath(), destinationFile.toPath());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
