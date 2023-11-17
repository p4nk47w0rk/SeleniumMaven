package test;

import configuration.Config;
import configuration.DriverHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.GoogleSearchPage;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestGoogleSearchPage {

    WebDriver driver;

    private String browser;
    private String expectedBrowserTitle;
    private String searchFieldInput;

    public TestGoogleSearchPage(String ProjectName, String browser, String expectedTitle, String searchFieldInput) {

        this.browser = browser;
        this.expectedBrowserTitle = expectedTitle;
        this.searchFieldInput = searchFieldInput;
    }



    @Before
    public void setUp() throws InterruptedException, MalformedURLException {
        System.out.println(browser);
        driver = DriverHelper.getDriver(browser);

        // maximize the window
        driver.manage().window().maximize();

        // implicitlyWait wait for a gen
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get(Config.getBaseUrl());


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch(){
        // Arrange
        String expectedTitle = expectedBrowserTitle;
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.acceptCookies();

        // Act
        googleSearchPage.setSearchField(searchFieldInput);
        googleSearchPage.clickSearchButton();
        googleSearchPage.clickFirstLink();

        // Assert
        assertEquals(expectedTitle, driver.getTitle());
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> provideTestData() throws Exception {
        Collection<Object[]> collection;
        Object[][] data = {
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 1", "firefox" , "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"},
                {"Paramtrisierter Test 2", "chrome", "A – Wikipedia" , "a"}
        };
        List<Object[]> listObjects = Arrays.asList(data);

        collection = new ArrayList<Object[]>(listObjects);

        return collection;

    };


    

}
