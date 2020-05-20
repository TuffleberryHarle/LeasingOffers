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
    public static void setUp() throws InterruptedException{
        System.setProperty(Props.getProperty("GECKO"), Props.getProperty("GECKO_PATH"));
        webDriver = new FirefoxDriver();
        constructPage = new ConstructPage(webDriver);
        webDriver.get(Props.getProperty("ZERO_STEP"));
        /**
         * Logging in part
         */
        constructPage.loginPage().loginInput();
        constructPage.loginPage().psswdInput();
        constructPage.loginPage().submitClick();
        /**
         * Creating a request part
         */
        constructPage.productsPage().closeButtonClick();
        constructPage.productsPage().createNewRequestClick();
    }

    @AfterClass
    public static void tearDown(){
        if (webDriver != null)
            webDriver.quit();
    }

    @Test
    public void test() throws Exception {
        constructPage.requestPage().fillIn();
        Thread.sleep(5000);
    }
}
