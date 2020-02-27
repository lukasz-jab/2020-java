package contact.tests;

import contact.contactdata.ContactData;
import contact.contactdata.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletingTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().mainPage();

        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("new Name " + Math.random()).withLastname("new Lastname " + Math.random()).
                    withAddress("new ADDRESS ADDRESS ADDRESS " + Math.random()).withHome("" + Math.random()).withGroup("[none]"));
            app.goTo().mainPage();

        }

    }

    @Test
    public void testsContactDeleting() {

        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().selectById(deletedContact);
        app.contact().delete();
        app.goTo().mainPage();

        Contacts after = app.db().contacts();

        Assert.assertEquals(before.size(), after.size() + 1);

        //before.remove(deletedContact);
        assertThat(after, equalTo(before.without(deletedContact)));


    }

}
