package sources;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.Props;

import java.util.ArrayList;
import java.util.List;

public class OfferTest {
    private static ConstructPage constructPage;
    private static FirefoxDriver webDriver;

    @BeforeClass
    public static void setUp() throws InterruptedException{
        System.setProperty(Props.getProperty("GECKO"), Props.getProperty("GECKO_PATH"));
        webDriver = new FirefoxDriver();
        constructPage = new ConstructPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get(Props.getProperty("ZERO_STEP1"));
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
        ArrayList<String> failures = new ArrayList<String>();
        int ITEM = 4, MFR = 1, MOD = 1, MODIF = 1;
        constructPage.requestPage().fillAndSend(ITEM, MFR, MOD, MODIF);
        constructPage.requestPage().offerCheck(ITEM, MFR, MOD, MODIF, failures);
        Thread.sleep(5000);
    }
}
