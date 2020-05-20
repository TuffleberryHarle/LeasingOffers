package sources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.Props;

public class LoginPage extends Props {
    public WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(id = "inputLogin")
    private WebElement loginField;

    @FindBy(id = "inputPassword")
    private WebElement psswrdField;

    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    public void loginInput(){
        loginField.clear();
        loginField.sendKeys(Props.getProperty("USER"));
    }

    public void psswdInput(){
        psswrdField.clear();
        psswrdField.sendKeys(Props.getProperty("PSSWRD"));
    }

    public void submitClick(){
        submit.click();
    }


}
