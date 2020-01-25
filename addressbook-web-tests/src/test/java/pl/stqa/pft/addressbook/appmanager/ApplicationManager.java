package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    FirefoxDriver wd;

    GroupHelper groupHelper;
    SessionHelper sessionHelper;
    NavigationHelper navigationHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        groupHelper = new GroupHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);

        wd.get("http://127.0.0.1/addressbook/");
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        wd.quit();
        wd = null;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public  SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }



}
