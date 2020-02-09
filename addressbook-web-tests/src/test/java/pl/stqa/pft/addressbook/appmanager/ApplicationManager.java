package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    WebDriver wd;

    GroupHelper groupHelper;
    SessionHelper sessionHelper;
    NavigationHelper navigationHelper;

    public void init() {
       if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }


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

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }


}
