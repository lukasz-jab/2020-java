package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }


    public void gotoGroupPage() {
        click(By.cssSelector("div#nav a[href = 'group.php']"));
    }

    public void returnGroupPage() {
        click(By.cssSelector("div#nav a[href = 'group.php']"));
    }
}
