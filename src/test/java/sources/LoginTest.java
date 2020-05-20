package sources;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.Props;

public class LoginTest {

    @BeforeClass
    public static void setUp(){
        System.setProperty(Props.getProperty("GECKO"), Props.getProperty("GECKO_PATH"));
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get(Props.getProperty("LOGIN_PAGE"));
    }
}
