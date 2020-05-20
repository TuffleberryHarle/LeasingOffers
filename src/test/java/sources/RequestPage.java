package sources;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Props;

public class RequestPage {
    public WebDriver webDriver;
    public WebDriverWait wait;

    public RequestPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30, 500);
    }

    @FindBy(css = "div[data-test-id='leasingSelectItem--selectDropDown']")
    protected WebElement leasItemMenu;

    @FindBy(css = "div[data-test-id='leasingSelectMfr--selectDropDown']")
    protected WebElement leasMfrMenu;

    @FindBy(css = "div[data-test-id='leasingSelectModel--selectDropDown']")
    protected WebElement leasModMenu;

    @FindBy(css = "div[data-test-id='leasingSelectModification--selectDropDown']")
    protected WebElement leasModifMenu;


    public void fillIn() throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("div[class='accordion-name']")));
        leasItemMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("PASSENGER"))).click();
        leasMfrMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("AUDI"))).click();
        leasModMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("A1"))).click();
        if (webDriver.findElement(By.xpath(Props.getProperty("MODIF_PATH")))
                .toString().isEmpty()) {
            wait(2000);
        }
        leasModifMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("1stMOD"))).click();
    }
}
