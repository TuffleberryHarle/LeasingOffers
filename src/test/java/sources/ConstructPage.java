package sources;

import org.openqa.selenium.WebDriver;

public class ConstructPage {
    public WebDriver webDriver;

    public ConstructPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public LoginPage loginPage(){
        return new LoginPage(webDriver);
    }

    public ProductsPage productsPage(){
        return new ProductsPage(webDriver);
    }

    public RequestPage requestPage(){
        return new RequestPage(webDriver);
    }
}
