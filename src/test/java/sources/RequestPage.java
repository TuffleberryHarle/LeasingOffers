package sources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RequestPage {
    public WebDriver webDriver;
    public WebDriverWait wait;

    public RequestPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 30, 500);
    }

    public void fillIn(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                .cssSelector("div[class='accordion-name']")));
    }
}
