package pl.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {

    FirefoxDriver wd;

    @BeforeTest
    public void setUp() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        wd.get("http://127.0.0.1/addressbook/");
        login("admin", "secret");
    }

    @AfterTest
    public void tearDown() {
        wd.quit();
        wd = null;
    }


    public void login(String user, String password) {
        wd.findElementByCssSelector("form#LoginForm input[name = 'user']").sendKeys(user);
        wd.findElementByCssSelector("form#LoginForm input[name = 'pass']").sendKeys(password);
        wd.findElementByCssSelector("form#LoginForm input[value = 'Login']").click();
    }

    public void gotoGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }

    public void fillGroupForm(GroupData group) {
        wd.findElementByCssSelector("div#container div#content input[name = 'new']").click();
        wd.findElementByCssSelector("div#container div#content input[name = 'group_name']")
                .sendKeys(group.getName());
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_header']")
                .sendKeys(group.getHeader());
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_footer']")
                .sendKeys(group.getFooter());
    }

    public void submitGroupForm() {
        wd.findElementByCssSelector("div#container div#content input[name = 'submit']").click();
    }

    public void returnGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }

    public void selectGroup() {
        wd.findElementByCssSelector("div#container div#content input[name = 'selected[]']");
    }

    public void deleteGroup() {
        wd.findElementByCssSelector("div#container div#content input[name = 'delete'][type = 'submit']");
    }


}
