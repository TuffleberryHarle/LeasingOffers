package sources;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.Props;

public class OfferTest {
    private static ConstructPage constructPage;
    private static FirefoxDriver webDriver;

    @BeforeClass
    public static void setUp() throws InterruptedException{
        System.setProperty(Props.getProperty("GECKO"), Props.getProperty("GECKO_PATH"));
        webDriver = new FirefoxDriver();
        constructPage = new ConstructPage(webDriver);
        webDriver.get(Props.getProperty("ZERO_STEP"));
        webDriver.manage().window().maximize();
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
        constructPage.requestPage().fillAndSend();
        constructPage.requestPage().offerCheck();
        Thread.sleep(5000);
    }
}