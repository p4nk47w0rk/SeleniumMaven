package configuration;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class DriverHelper {

    public static void throwExceptionWhenNull(String browser) {
        if(browser == null){
            throw new RuntimeException("Es wurde kein WebDriver angegeben.");
        }
    }
    public static WebDriver getDriver(String browser) throws MalformedURLException {
        AvailableBrowser availableBrowser = AvailableBrowser.valueOf(browser.toUpperCase());
        WebDriver driver;
        driver = availableBrowser.createDriver();
        return driver;
    }
}
