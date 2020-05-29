package sources;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.KeyInput;
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

        @FindBy(css = "button[data-test-id='forward-link--button']")
        protected WebElement nextButton;

        @FindBy(css = "div[data-test-id='taxMode--selectDropDown']")
        protected WebElement taxMenu;

        @FindBy(css = "input[data-test-id='sliderAmount--input']")
        protected WebElement sumField;

        @FindBy(css = "input[data-test-id='sliderTerm--input']")
        protected WebElement termField;

        @FindBy(css = "input[data-test-id='sliderAdvance--input']")
        protected WebElement advField;

    public void fillAndSend(int ITEM, int MFR, int MOD, int MODIF) throws Exception {
        /**
         * Form block of accordion
         */
        wait.until(ExpectedConditions.visibilityOf(leasItemMenu));

        // Pick a vehicle
        leasItemMenu.click();
        webDriver.findElement(By.xpath("//div[@data-test-id='leasingSelectItem--selectList']/div["+ITEM+"]")).click();

        wait.until(ExpectedConditions.visibilityOf(leasMfrMenu));
        leasMfrMenu.click();
        webDriver.findElement(By.xpath("//div[@data-test-id='leasingSelectMfr--selectList']/div["+MFR+"]")).click();

        wait.until(ExpectedConditions.visibilityOf(leasModMenu));
        leasModMenu.click();
        webDriver.findElement(By.xpath("//div[@data-test-id='leasingSelectModel--selectList']/div["+MOD+"]")).click();

        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By
                .xpath(Props.getProperty("MODIF_PATH")), By.xpath(Props.getProperty("1stMOD"))));
        leasModifMenu.click();
        webDriver.findElement(By.xpath("//div[@data-test-id='leasingSelectModification--selectList']/div["+MODIF+"]")).click();

        // Go to the next block
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
        /**
         * Finances block of accordion
         */
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[data-analytics-label='accordion Отправить на рассмотрение']")));

        // Pick a tax method
        taxMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("1stTAX"))).click();

        // Set sum, term and advance
        sumField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sumField.sendKeys(Props.getProperty("SUM"));
        sumField.sendKeys(Keys.RETURN);
//        Thread.sleep(3000);
        termField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        termField.sendKeys(Props.getProperty("TERM"));
        sumField.sendKeys(Keys.RETURN);
//        Thread.sleep(3000);
        advField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        advField.sendKeys(Props.getProperty("ADV"));
        sumField.sendKeys(Keys.RETURN);

        // Send request
        webDriver.findElement(By.xpath(Props.getProperty("SEND_BUTTON"))).click();
    }

    public void offerCheck() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[data-test-id='leasing-download-pdf--button']")));
        Thread.sleep(3000);
    }
}
