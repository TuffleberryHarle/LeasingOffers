package sources;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.Props;

public class LoginTest {
    private static ConstructPage constructPage;
    private static FirefoxDriver webDriver;

    @BeforeClass
    public static void setUp(){
        System.setProperty(Props.getProperty("GECKO"), Props.getProperty("GECKO_PATH"));
        webDriver = new FirefoxDriver();
        constructPage = new ConstructPage(webDriver);
        webDriver.get(Props.getProperty("PREFILLED_PROD_PAGE"));
    }

    @AfterClass
    public static void tearDown(){
        if (webDriver != null)
            webDriver.quit();
    }

    @Test
    public void loginTest() throws InterruptedException {
        constructPage.loginPage().loginInput();
        constructPage.loginPage().psswdInput();
        constructPage.loginPage().submitClick();
        Thread.sleep(5000);
    }
}
