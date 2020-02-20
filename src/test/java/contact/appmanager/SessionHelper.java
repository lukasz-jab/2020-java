package contact.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


    SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String login, String password) {

        type(By.cssSelector("form#LoginForm input[name = 'user']"), login);
        type(By.cssSelector("form#LoginForm input[name = 'pass']"), password);
        click(By.cssSelector("form#LoginForm input[value = 'Login']"));
    }
}
