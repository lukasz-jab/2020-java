package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String user, String password) {
        type(By.cssSelector("form#LoginForm input[name = 'user']"), user);
        type(By.cssSelector("form#LoginForm input[name = 'pass']"), password);
        click(By.cssSelector("form#LoginForm input[value = 'Login']"));
    }


}
