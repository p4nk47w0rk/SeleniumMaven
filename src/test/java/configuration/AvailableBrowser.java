package configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public enum AvailableBrowser {

    FIREFOX{
        @Override
        public WebDriver createDriver() throws MalformedURLException {
            //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver");
            URL linkHub = new URL("http://localhost:4444/wd/hub");
            FirefoxOptions options = new FirefoxOptions();
            return new RemoteWebDriver(linkHub, options);
        }
    },
    CHROME{
        @Override
        public WebDriver createDriver() throws MalformedURLException {
            //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

            ChromeOptions options = new ChromeOptions();
            URL linkHub = new URL("http://localhost:4444/wd/hub");

            return new RemoteWebDriver(linkHub, options);
        }
    },
    ;

    public abstract WebDriver createDriver() throws MalformedURLException;

}
