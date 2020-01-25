package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper {

    FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void login(String user, String password) {
        wd.findElementByCssSelector("form#LoginForm input[name = 'user']").sendKeys(user);
        wd.findElementByCssSelector("form#LoginForm input[name = 'pass']").sendKeys(password);
        wd.findElementByCssSelector("form#LoginForm input[value = 'Login']").click();
    }



}
