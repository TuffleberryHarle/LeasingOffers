package sources;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.Props;

import java.util.ArrayList;

public class OfferTest {
    public static ConstructPage constructPage;
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

    public int ITEM = 4, MFR = 1, MOD = 1, MODIF = 24;

    @Test
    public void test() throws Exception {
        ArrayList<String> failures = new ArrayList<>();
        constructPage.requestPage().fillAndSend(ITEM, MFR, MOD, MODIF);
        constructPage.requestPage().offerCheck(ITEM, MFR, MOD, MODIF, failures);
        Thread.sleep(5000);
        System.out.println(failures);
    }
}
