package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchPage {

    WebDriver webDriver;
    WebDriverWait wait;
    @FindBy(id = "APjFqb")
    private WebElement searchField;

    @FindBy(css = "#L2AGLb > div:nth-child(1)")
    private WebElement cookiesButton;

    @FindBy(css = "#search #rso>div.MjjYud a")
    private WebElement firstLink;

    public GoogleSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;


        // example for explicit wait for a specific purpose
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    public void acceptCookies() {
        cookiesButton.click();
    }

    public void setSearchField(String text) {
        searchField.sendKeys(text);
    }

    public void clickSearchButton() {
        searchField.submit();
    }

    public void clickFirstLink() {
        wait.until(ExpectedConditions.elementToBeClickable(firstLink));
        firstLink.click();
    }


    public String getTitle() {
        return webDriver.getTitle();
    }

}
