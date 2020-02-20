package contact.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void contactPage() {
        click(By.cssSelector("div#nav a[href = 'edit.php']"));
    }

    public void mainPage() {
        get("http://127.0.0.1/addressbook/index.php");
    }
}
