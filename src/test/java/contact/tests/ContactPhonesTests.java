package contact.tests;

import contact.contactdata.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhonesTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().mainPage();

        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("new Name " + Math.random()).withLastname("new Lastname " + Math.random()).
                    withAddress("new ADDRESS ADDRESS ADDRESS " + Math.random()).withHome("" + Math.random()).withGroup("[none]"));
            app.goTo().mainPage();

        }
    }

    @Test
    public void testContactsPhones() {
        app.goTo().mainPage();
        ContactData contact = app.contact().all().iterator().next();
        String phonesFromMainPage = app.contact().getAllPhonesMainPage(contact);
        String phonesFromContactEditPage = app.contact().getPhonesFromEditPage(contact);

        cleaned(phonesFromContactEditPage);
        cleaned(phonesFromMainPage);
        //mergePhones(contact);
        MatcherAssert.assertThat((phonesFromMainPage), CoreMatchers.equalTo(phonesFromContactEditPage));

    }

    public static void cleaned(String phone) {
        phone.replaceAll("[-()]]","")
        .replaceAll("[\\+]", "").replaceAll("[\\.]", "").replaceAll("\\s", "");
    }

   //public String mergePhones (String contact){
    // return contact.stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
   //}


}
