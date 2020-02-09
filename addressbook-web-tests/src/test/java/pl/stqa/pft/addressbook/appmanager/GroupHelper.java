package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void fillGroupForm(GroupData group) {
        click(By.cssSelector("div#container div#content input[name = 'new']"));
        type(By.cssSelector("div#container div#content input[name = 'group_name']"), group.getName());
        type(By.cssSelector("div#container div#content textarea[ name= 'group_header']"), group.getHeader());
        type(By.cssSelector("div#container div#content textarea[ name= 'group_footer']"), group.getFooter());
    }

    public void submitGroupForm() {
        click(By.cssSelector("div#container div#content input[name = 'submit']"));
    }

    public void selectGroup() {
        click(By.cssSelector("div#container div#content input[name = 'selected[]']"));
    }

    public void deleteGroup() {
        click(By.cssSelector("div#container div#content input[name = 'delete'][type = 'submit']"));
    }


}
