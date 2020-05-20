package sources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    public WebDriver webDriver;
    private WebDriverWait wait;

    public ProductsPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30, 500);
    }

    /**
     * Find the button to close the email requirement window
     */
    @FindBy(css = "button[title='Закрыть']")
    private static WebElement closeButton;
    /**
     * Find the Leasing Product button
     */
    @FindBy(css = "button[data-test-id='button-more--button']")
    private static WebElement leasProdButton;
    /**
     * Find the button to create brand new request
     */
    @FindBy(css = "button[data-analytics-label='Action.CREATE']")
    private static WebElement createNewRequest;

    public void closeButtonClick(){

        closeButton.click();
    }

    public void leasProdButtonClick(){
        leasProdButton.click();
    }

    public void createNewRequestClick(){
        createNewRequest.click();
    }
}

