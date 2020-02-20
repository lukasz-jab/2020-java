package contact.appmanager;

import contact.contactdata.ContactData;
import contact.contactdata.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;


    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        if (finds(By.cssSelector("div#container div#content input[name = 'quickadd']")).size() != 0) {
            click(By.cssSelector("div#container div#content input[name = 'quickadd']"));
        }
        if (wd.findElements((By.cssSelector("select[name='new_group']"))).size() != 0) {
            new Select(wd.findElement(By.cssSelector("select[name='new_group']"))).selectByVisibleText(contact.getGroup());
        }
        type(By.cssSelector("div#container div#content input[name = 'firstname']"), contact.getFirstname());
        type(By.cssSelector("div#container div#content input[name = 'lastname']"), contact.getLastname());
        type(By.cssSelector("div#container div#content textarea[ name= 'address']"), contact.getAddress());
        type(By.cssSelector("div#container div#content input[name= 'home']"), contact.getHome());
        type(By.cssSelector("div#container div#content input[name= 'work']"), contact.getWork());
        type(By.cssSelector("div#container div#content input[name= 'photo']"), contact.getPhoto().getAbsolutePath());

    }


    public ContactData allInfoContactForm(ContactData contact, boolean creation) {
        if (finds(By.cssSelector("div#container div#content input[name = 'quickadd']")).size() != 0) {
            click(By.cssSelector("div#container div#content input[name = 'quickadd']"));
        }
        if (wd.findElements((By.cssSelector("select[name='new_group']"))).size() != 0) {
            new Select(wd.findElement(By.cssSelector("select[name='new_group']"))).selectByVisibleText(contact.getGroup());
        }
        String firstname = find(By.cssSelector("div#container div#content input[name = 'firstname']")).getAttribute("value");
        String lastname = find(By.cssSelector("div#container div#content input[name = 'lastname']")).getAttribute("value");
        String address = find(By.cssSelector("div#container div#content textarea[ name= 'address']")).getAttribute("value");
        String home = find(By.cssSelector("div#container div#content input[name= 'home']")).getAttribute("value");
        String work = find(By.cssSelector("div#container div#content input[name= 'work']")).getAttribute("value");

        return new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withHome(home).withWork(work);
    }

    public void submitContactForm() {
        if (finds(By.cssSelector("div#container div#content input[name = 'submit']")).size() != 0) {
            click(By.cssSelector("div#container div#content input[name = 'submit']"));
        }
        if (finds(By.cssSelector("div#container div#content input[name = 'update']")).size() != 0) {
            click(By.cssSelector("div#container div#content input[name = 'update']"));
        }

    }

    public void modify(ContactData modifyedcontact, ContactData contactD) {
        selectById(modifyedcontact);
        editSelectedContactById(modifyedcontact);

        create(contactD);
        gotoMainPage();

        contactCache = null;
    }

    private void gotoMainPage() {
        get("http://127.0.0.1/addressbook/index.php");
    }

    public void selectById(ContactData contactData) {
        int index = contactData.getId();
        click(By.cssSelector("div#container div#content input[id = '" + index + "'"));

    }

    public void editSelectedContactById(ContactData contact) {
        int index = contact.getId();
        click(By.xpath("//div[@id='container']//div[@id='content']//a[@href = 'edit.php?id=" + index + "']"));

    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactForm();
        contactCache = null;
    }

    public void delete() {
        click(By.cssSelector("div#container div#content input[onclick = 'DeleteSel()']"));
        acceptAlert();
        contactCache = null;

    }

    public Contacts all() {

        if (contactCache != null) {
            return new Contacts(contactCache);
        } else

            contactCache = new Contacts();
        List<WebElement> contactsInput;
        contactsInput = finds(By.cssSelector("div#container div#content input[name = 'selected[]']"));
        for (WebElement e : contactsInput) {
            contactCache.add(new ContactData().withId(Integer.valueOf(e.getAttribute("value")))
                    .withFirstname(e.getAttribute("title")));
        }
        return new Contacts(contactCache);
    }

    public String getAllPhonesMainPage(ContactData contact) {
        int index = contact.getId();
        WebElement row = wd.findElement(By.cssSelector("div#container div#content input[id = '" + index + "'"));
        return row.findElement(By.xpath("./../../td[6]")).getText();
    }

    public String getPhonesFromEditPage(ContactData contact) {
        int index = contact.getId();
        click(By.xpath("//div[@id='container']//div[@id='content']//a[@href = 'edit.php?id=" + index + "']"));
        if (finds(By.cssSelector("div#container div#content input[name = 'quickadd']")).size() != 0) {
            click(By.cssSelector("div#container div#content input[name = 'quickadd']"));
        }
        if (wd.findElements((By.cssSelector("select[name='new_group']"))).size() != 0) {
            new Select(wd.findElement(By.cssSelector("select[name='new_group']"))).selectByVisibleText(contact.getGroup());
        }
        /*String firstname = find(By.cssSelector("div#container div#content input[name = 'firstname']")).getAttribute("value");
        String lastname = find(By.cssSelector("div#container div#content input[name = 'lastname']")).getAttribute("value");
        String address = find(By.cssSelector("div#container div#content textarea[ name= 'address']")).getAttribute("value");*/
        String home = find(By.cssSelector("div#container div#content input[name= 'home']")).getAttribute("value");
        String work = find(By.cssSelector("div#container div#content input[name= 'work']")).getAttribute("value");

        return home + work;
    }
    }

   /*public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> contactsInput;
        contactsInput = finds(By.cssSelector("div#container div#content input[name = 'selected[]']"));
        //System.out.println("CONTACTSiNPUTS SIZEEEEEEEEE:" +contactsInput.size());
        for (WebElement e : contactsInput) {
            contacts.add(new ContactData().withId(Integer.valueOf(e.getAttribute("value")))
                    .withFirstname(e.getAttribute("title")));
        }
        return contacts;
    }*/


/* public void editSelectedContact() {
     click(By.xpath("//div[@id='container']//div[@id='content']//a[contains(@href,'edit.php')]"));
 }*/
   /* public void select(int index) {
        List<WebElement> contacts = finds(By.cssSelector("div#container div#content input[name = 'selected[]']"));
        contacts.get(index).click();
        //click(By.cssSelector("div#container div#content input[name = 'selected[]']"));

    }
*/

