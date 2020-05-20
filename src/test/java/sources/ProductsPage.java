package sources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
     * Find the button to create brand new request
     */
    @FindBy(css = "button[data-analytics-label='Create new request']")
    private static WebElement createNewRequest;

    public void closeButtonClick(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[title='Закрыть']")));
        closeButton.click();
    }

    public void createNewRequestClick(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("button[data-analytics-label='Create new request']")));
        createNewRequest.click();
    }
}

