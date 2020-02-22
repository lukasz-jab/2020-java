package contact.tests;

import contact.contactdata.ContactData;
import contact.contactdata.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().mainPage();

        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            ContactData contact = new ContactData().withFirstname("new Name " + Math.random()).withLastname("new Lastname " + Math.random()).
                    withAddress("new ADDRESS ADDRESS ADDRESS " + Math.random()).withHome("" + Math.random()).withGroup("[none]");
            app.contact().create(contact);
            app.goTo().mainPage();

        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modyfiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modyfiedContact.getId()).withFirstname("Modifying Name " + Math.random())
                .withLastname("Modifying Lastname " + Math.random()).withAddress("Modifying ADDRESS ADDRESS ADDRESS " + Math.random())
                .withHome("" + Math.random()).withGroup("[none]").withPhoto(new File("src/test/resources/phot.png"));

        app.contact().modify(modyfiedContact, contact);

        Contacts after = app.contact().all();

        // before.remove(modyfiedContact);
        // before.add(contact);
        //There is a bug: Application deleting instead updating contact
        assertThat(after, equalTo(before.without(modyfiedContact).withAdded(contact)));

        //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }


}
