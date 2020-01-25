package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {

    FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }


    public void gotoGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }

    public void returnGroupPage() {
        wd.findElementByCssSelector("div#nav a[href = 'group.php']").click();
    }
}
