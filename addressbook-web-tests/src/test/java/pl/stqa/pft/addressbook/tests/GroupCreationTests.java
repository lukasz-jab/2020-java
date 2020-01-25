package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import pl.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.fillGroupForm(new GroupData("new group " + Math.random(), "new group HEADER HEADER HEADER " + Math.random(),
                "new group FOOTER FOOTER FOOTER " + Math.random()));
        app.submitGroupForm();
        app.returnGroupPage();
    }


}
