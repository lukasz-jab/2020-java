package contact.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Properties properties;

    private WebDriver wd;
    private String browser;
    //private HelperBase helperBase;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target) )));
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }
        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl","http://127.0.0.1/addressbook/"));
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        // helperBase = new HelperBase(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin","admin"),
                properties.getProperty("web.adminPass", "secret"));


    }

    public void stop() {
        wd.quit();
        wd = null;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

}
