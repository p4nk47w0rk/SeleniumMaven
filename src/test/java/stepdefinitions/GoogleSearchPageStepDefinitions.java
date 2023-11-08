package stepdefinitions;

import configuration.DriverHelper;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import io.cucumber.junit.Cucumber;
import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.GoogleSearchPage;

import java.net.MalformedURLException;
import java.time.Duration;


import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class GoogleSearchPageStepDefinitions {


    WebDriver driver;
    GoogleSearchPage googleSearchPage;





    @Angenommen("der Nutzer öffnet den {string}")
    public void derNutzerÖffnetDen(String browser) throws MalformedURLException {
        driver = DriverHelper.getDriver(browser);
        // maximize the window
        driver.manage().window().maximize();
        // implicitlyWait wait for a gen
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Und("der Benutzer befindet sich auf {string}")
    public void derBenutzerBefindetSichAuf(String websiteURL){
        driver.get(websiteURL);
        googleSearchPage =  new GoogleSearchPage(driver);
    }

    @Und("der Nutzer akzeptiert die Cookie-Meldung")
    public void derNutzerAkzeptiertDieCookieMeldung() {
        googleSearchPage.acceptCookies();

    }

    @Angenommen("der Nutzer tippt folgenden Suchbegriff in die Suchzeile ein: {string}")
    public void der_nutzer_tippt_folgenden_suchbegriff_in_die_suchzeile_ein(String searchTerm) {
        googleSearchPage.setSearchField(searchTerm);

    }
    @Angenommen("der Nutzer klickt auf suchen")
    public void der_nutzer_klickt_auf_suchen() {
        googleSearchPage.clickSearchButton();
    }

    @Wenn("der Nutzer das erste Ergebnis der in der Ergebnisliste wählt")
    public void derNutzerDasErsteErgebnisDerInDerErgebnislisteWaehlt() {
        googleSearchPage.clickFirstLink();
    }

    @Dann("soll geprüft werden ob der Title der Seite folgender ist: {string}")
    public void soll_geprüft_werden_ob_der_title_der_seite_folgender_ist(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());

        tearDown();
    }

    @After
    public void tearDown() {
        driver.quit();
    }



}
