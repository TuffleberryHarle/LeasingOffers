package sources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    public WebDriver driver;

    public ProductsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
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

