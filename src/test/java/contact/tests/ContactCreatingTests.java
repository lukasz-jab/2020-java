package contact.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import contact.contactdata.ContactData;
import contact.contactdata.Contacts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> jsonValidContact() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cont.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> list = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return list.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }

    }

    @DataProvider
    public Iterator<Object[]> xmlValidContact() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cont.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cont.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                list.add(new Object[]{new ContactData().withFirstname(split[0] + Math.random()).withLastname(split[1] + Math.random()).
                        withAddress(split[2] + Math.random()).withHome(split[3] + Math.random())
                        .withPhoto(new File(split[6]))
                        .withWork(split[4] + Math.random()).withGroup(split[5])});
                line = reader.readLine();
            }

            return list.iterator();
        }
    }

    @Test(enabled = true, dataProvider = "jsonValidContact")
    public void testContactCreating(ContactData contact) {

        app.goTo().mainPage();
        Contacts before = app.db().contacts();

        app.goTo().contactPage();
        app.contact().create(contact);
        app.goTo().mainPage();

        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c1) -> (c1.getId())).max().getAsInt());
        before.add(contact);

        assertThat(after, equalTo(before.withAdded(contact)));

    }

    @Test(enabled = false)
    public void testBadContactCreating() {

        app.goTo().mainPage();
        Contacts before = app.db().contacts();

        File photo = new File("src/test/resources/phot.png");
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstname(" ' " + Math.random()).withLastname("new Lastname " + Math.random()).
                withAddress("new ADDRESS ADDRESS ADDRESS " + Math.random()).withHome("" + Math.random())
                .withPhoto(photo)
                .withWork("" + Math.random()).withGroup("[none]");
        app.contact().create(contact);
        app.goTo().mainPage();

        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before));

    }

    @Test(enabled = false)
    public void testCurrentDir() throws IOException {
        File currentDir = new File("src/test/resources/phot.png");
        System.out.println(currentDir.exists());
        System.out.println(currentDir.canExecute());
        System.out.println(currentDir.canRead());
        System.out.println(currentDir.canWrite());
        System.out.println(currentDir.getFreeSpace());
        System.out.println(currentDir.getTotalSpace());
        System.out.println(currentDir.getAbsolutePath());
        System.out.println(currentDir.getCanonicalPath());
    }

            /*


     //Assert.assertEquals(after, before);
        int max = 0;
        for(ContactData c : after) {
            if(c.getId() > max) {
                max = c.getId();
            }
        }
*/
       /* contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);

        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
*/
        /*after.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        before.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));

        Assert.assertEquals(before, after);
*/
}
