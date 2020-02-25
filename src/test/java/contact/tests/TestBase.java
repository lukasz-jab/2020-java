package contact.tests;

import contact.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {


    static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }


    @AfterSuite(alwaysRun = true)
    public void TearDown() {
        app.stop();
    }

    @BeforeMethod
    public void testLogStart(Method m, Object[] p) {
        logger.info("Start tests " + m.getName() + " with parameters: " +Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void testLogStop(Method m, Object[] p) {
        logger.info("Stop Contact creation test" + m.getName() + Arrays.asList(p));
    }

}



