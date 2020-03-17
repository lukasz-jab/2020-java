package contact.tests;

import contact.appmanager.ApplicationManager;
import contact.contactdata.ContactData;
import contact.contactdata.Contacts;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(MyTestListener.class)
public class TestBase {


    static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp(ITestContext context) throws IOException {
        app.init();
        context.setAttribute("app", app);
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

    public void verifyContactsKistInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            app.goTo().mainPage();
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(dbContacts, equalTo(uiContacts));
            //assertThat(uiContacts, equalTo(dbContacts
            //        .stream().map((c)->new ContactData().withId(c.getId()).withFirstname(c.getFirstname()))
            //        .collect(Collectors.toSet())));
        }
    }

}




