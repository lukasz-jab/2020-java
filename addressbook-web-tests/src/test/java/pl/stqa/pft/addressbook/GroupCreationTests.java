package pl.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        gotoGroupPage();
        fillGroupForm(new GroupData("new group " + Math.random(), "new group HEADER HEADER HEADER " + Math.random(),
                "new group FOOTER FOOTER FOOTER " + Math.random()));
        submitGroupForm();
        returnGroupPage();
    }


}
