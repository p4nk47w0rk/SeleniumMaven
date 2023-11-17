package stepdefinitions;

import configuration.DriverHelper;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import io.cucumber.junit.Cucumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.GoogleSearchPage;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;


import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class GoogleSearchPageStepDefinitions {


    WebDriver driver;
    GoogleSearchPage googleSearchPage;
    private String browserName;

    private static final Logger logger = LogManager.getLogger(GoogleSearchPageStepDefinitions.class.getName());


    @Angenommen("der Nutzer öffnet den {string}")
    public void derNutzerÖffnetDen(String browser) throws MalformedURLException {
        this.browserName = browser;
        logger.info("Nutzer öffnet den Browser: " + browser);
        driver = DriverHelper.getDriver(browser);
        // maximize the window
        driver.manage().window().maximize();
        // implicitlyWait wait for a gen
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Und("der Benutzer befindet sich auf {string}")
    public void derBenutzerBefindetSichAuf(String websiteURL){
        logger.warn("#################################:" + browserName);
        logger.info("Benutzer befindet sich auf: " + websiteURL);
        driver.get(websiteURL);
        googleSearchPage =  new GoogleSearchPage(driver);
    }

    @Und("der Nutzer akzeptiert die Cookie-Meldung")
    public void derNutzerAkzeptiertDieCookieMeldung() {
        logger.info("Nutzer akzeptiert die Cookie-Meldung");
        googleSearchPage.acceptCookies();

    }

    @Angenommen("der Nutzer tippt folgenden Suchbegriff in die Suchzeile ein: {string}")
    public void der_nutzer_tippt_folgenden_suchbegriff_in_die_suchzeile_ein(String searchTerm) {
        logger.info("Nutzer tippt folgenden Suchbegriff in die Suchzeile ein: " + searchTerm);
        googleSearchPage.setSearchField(searchTerm);

    }
    @Angenommen("der Nutzer klickt auf suchen")
    public void der_nutzer_klickt_auf_suchen() {
        logger.info("Nutzer klickt auf suchen");
        googleSearchPage.clickSearchButton();
    }

    @Wenn("der Nutzer das erste Ergebnis der in der Ergebnisliste wählt")
    public void derNutzerDasErsteErgebnisDerInDerErgebnislisteWaehlt() {
        logger.info("Nutzer wählt das erste Ergebnis aus der Ergebnisliste");

        googleSearchPage.clickFirstLink();
    }

    @Dann("soll geprüft werden ob der Title der Seite folgender ist: {string}")
    public void soll_geprüft_werden_ob_der_title_der_seite_folgender_ist(String expectedTitle) {

        logger.info("Soll geprüft werden ob der Title der Seite folgender ist: " + expectedTitle);
        takeScreenshot(driver);
        assertEquals(expectedTitle, driver.getTitle());

        tearDown();
    }

    @After
    public void tearDown() {
        logger.info("Browser wird geschlossen");
        driver.quit();
    }


    private void takeScreenshot(WebDriver driver) {
        try {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Path srcPath = srcFile.toPath();
            Path targetPath = new File("target/screenshot_"+driver.getTitle()+"_.png").toPath();
            Files.copy(srcPath, targetPath, StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        }
    }



}
