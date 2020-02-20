package contact.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import contact.contactdata.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Liczba kontaktów do stworzenia")
    public Integer count = 1;

    @Parameter(names = "-file", description = "lokalizacja / nazwa pliku")
    public String file;

    @Parameter(names = "-format", description = "Format danych")
    public String format;


    public static void main(String[] args) throws IOException {
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);

        ContactDataGenerator main = new ContactDataGenerator();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }
    public void run() throws IOException {
       List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXml(contacts, new File(file) );
        } else {
            System.out.println("Unrecognized format");
        }


    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);

        Writer writer = new FileWriter(file);

        String xml = xstream.toXML(contacts);
        writer.write(xml);
        writer.close();
    }

    public static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File photo = new File ("src/test/resources/phot.png");
        for (int i =0; i < count; i++) {
            ContactData contact = new ContactData().withFirstname(" new Firstname " + Math.random()).withLastname("new Lastname " + Math.random()).
                    withAddress("new ADDRESS ADDRESS ADDRESS " + Math.random()).withHome("" + Math.random())
                    .withPhoto(photo)
                    .withWork("" + Math.random()).withGroup("[none]");

            contacts.add(contact);
        }
        return contacts;
    }

    public static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
            Writer writer = new FileWriter(file);
            for (ContactData contact : contacts) {
                writer.write(contact.getFirstname() + "," + contact.getLastname() + "," + contact.getAddress()
                        + "," + contact.getHome() + "," + contact.getWork() + "," + contact.getGroup() + "," + contact.getPhoto());
                writer.write(String.format("%s\n", ""));
            }
            writer.close();

    }
}
