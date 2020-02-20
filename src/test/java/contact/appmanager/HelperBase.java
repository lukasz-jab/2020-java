package contact.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!existingText.equals(text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }

    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public List<WebElement> finds(By locator) {
        return wd.findElements(locator);
    }

    public WebElement find(By locator) {
        return wd.findElement(locator);
    }

    public void get(String url) {
        wd.get(url);
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}
