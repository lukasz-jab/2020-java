package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pl.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeTest
    public void setUp() {
        app.init();
    }

    @AfterTest
    public void tearDown() {
        app.stop();
    }

}
