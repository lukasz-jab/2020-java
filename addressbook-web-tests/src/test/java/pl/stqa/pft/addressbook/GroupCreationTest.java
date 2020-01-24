package pl.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GroupCreationTest {

    FirefoxDriver wd;

    @BeforeTest
    public void setUp() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        wd.get("http://127.0.0.1/addressbook/");
       login();
    }

    @Test
    public void testGroupCreation() {

        gotoGroupPage();
        fillGroupForm();
        submitGroupForm();
        returnGroupPage();
    }

    @AfterTest
    public void tearDown() {
        wd.quit();
        wd=null;
    }

    public void login() {
        wd.findElementByCssSelector("form#LoginForm input[name = 'user']").sendKeys("admin");
        wd.findElementByCssSelector("form#LoginForm input[name = 'pass']").sendKeys("secret");
        wd.findElementByCssSelector("form#LoginForm input[value = 'Login']").click();
    }

    public void gotoGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }

    public void fillGroupForm() {
        wd.findElementByCssSelector("div#container div#content input[name = 'new']").click();
        wd.findElementByCssSelector("div#container div#content input[name = 'group_name']")
                .sendKeys("new group 23.01.2020");
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_header']")
                .sendKeys("new group HEADER HEADER HEADER 23.01.2020");
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_footer']")
                .sendKeys("new group FOOTER FOOTER FOOTER 23.01.2020");
    }

    public void submitGroupForm() {
        wd.findElementByCssSelector("div#container div#content input[name = 'submit']").click();
    }

    public void returnGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }


}
