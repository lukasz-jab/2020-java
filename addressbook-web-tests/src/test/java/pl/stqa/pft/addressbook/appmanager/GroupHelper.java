package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {

    FirefoxDriver wd;

    public GroupHelper(FirefoxDriver wd) {
        this.wd = wd;
    }



    public void fillGroupForm(GroupData group) {
        wd.findElementByCssSelector("div#container div#content input[name = 'new']").click();
        wd.findElementByCssSelector("div#container div#content input[name = 'group_name']")
                .sendKeys(group.getName());
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_header']")
                .sendKeys(group.getHeader());
        wd.findElementByCssSelector("div#container div#content textarea[ name= 'group_footer']")
                .sendKeys(group.getFooter());
    }

    public void submitGroupForm() {
        wd.findElementByCssSelector("div#container div#content input[name = 'submit']").click();
    }

    public void selectGroup() {
        wd.findElementByCssSelector("div#container div#content input[name = 'selected[]']");
    }

    public void deleteGroup() {
        wd.findElementByCssSelector("div#container div#content input[name = 'delete'][type = 'submit']");
    }

}
