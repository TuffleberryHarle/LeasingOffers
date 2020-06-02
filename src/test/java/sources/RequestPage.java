package sources;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Props;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RequestPage {
    public WebDriver webDriver;
    public WebDriverWait wait;

    public RequestPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 20, 500);
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
        // Pick a vehicle
        dropDownPick(ITEM, "Item", leasItemMenu);
        dropDownPick(MFR, "Mfr", leasMfrMenu);
        dropDownPick(MOD, "Model", leasModMenu);
        modifIsNotEmptyWait(MODIF);
        dropDownPick(MODIF, "Modification", leasModifMenu);
        // Go to the next block
        nextButtonClick();
        /**
         * Finances block of accordion
         */
        sendButtonWait();
        // Pick a tax method
        taxPick();
        // Set sum, term and advance
        financesConditions(sumField, "SUM");
        financesConditions(termField, "TERM");
        financesConditions(advField, "ADV");
        // Send request
        sendButtonClick();
    }

    public void offerCheck(int ITEM, int MFR, int MOD, int MODIF, ArrayList failures) throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By
                    .cssSelector("button[data-test-id='leasing-download-pdf--button']")));
        }
        catch (Exception e){
            failures.add(String.valueOf(ITEM)+String.valueOf(MFR)+String.valueOf(MOD)+String.valueOf(MODIF));
            e.printStackTrace();
            webDriver.get(Props.getProperty("ZERO_STEP1"));
            PageFactory.initElements(webDriver, this);
            createNewRequestClick();
            fillAndSend(ITEM, MFR, MOD, MODIF+1);
            offerCheck(ITEM, MFR, MOD, MODIF+1, failures);
            return;
        }
        changeConditions(ITEM, MFR, MOD, MODIF, failures);
        Thread.sleep(3000);
    }

    public void dropDownPick(int CONST, String DROPDOWN, WebElement dropDownMenu){
        wait.until(ExpectedConditions.visibilityOf(dropDownMenu));
        dropDownMenu.click();
        webDriver.findElement(By
                .xpath("//div[@data-test-id='leasingSelect"+DROPDOWN+"--selectList']/div["+CONST+"]")).click();
    }

    public void financesConditions(WebElement field, String prop) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Props.getProperty(prop));
        field.sendKeys(Keys.TAB);
        field.sendKeys(Keys.TAB);
    }

    public void taxPick(){
        taxMenu.click();
        webDriver.findElement(By.xpath(Props.getProperty("1stTAX"))).click();
    }

    public void modifIsNotEmptyWait(int MODIF){
        //wait for modifications list to appear
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By
                .xpath(Props.getProperty("MODIF_PATH")), By.xpath(Props.getProperty("1stMOD"))));
        //check how much modifications we have
        WebElement tableElement = webDriver.findElement(By.xpath(Props.getProperty("MODIF_PATH")));
        List<WebElement> records = tableElement.findElements(By.tagName("div"));
        try {
            assert(records.size() >= MODIF);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("You've checked each of modifications for this model");
        }
    }

    public void nextButtonClick(){
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
    }

    public void sendButtonWait(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[data-analytics-label='accordion Отправить на рассмотрение']")));
    }

    public void sendButtonClick(){
        webDriver.findElement(By
                .xpath(Props.getProperty("SEND_BUTTON"))).click();
    }

    public void createNewRequestClick(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[data-analytics-label='Create new request']")));
        webDriver.findElement((By
                .cssSelector("button[data-analytics-label='Create new request']"))).click();
    }

    public void changeConditions(int ITEM, int MFR, int MOD, int MODIF, ArrayList failures) throws Exception {
        webDriver.findElement(By
                .xpath("button[data-test-id='leasing-decline-link--button']")).click();
        // ADD JUMPING TO THE FIRST BLOCK OF ACCORDION
        fillAndSend(ITEM, MFR, MOD, MODIF+1);
        offerCheck(ITEM, MFR, MOD, MODIF+1, failures);
    }
}
