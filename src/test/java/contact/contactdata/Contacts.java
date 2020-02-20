package contact.contactdata;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {


    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);

    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    @Override
    protected Set delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {

        Contacts contacs = new Contacts(this);

        contacs.add(contact);
        return contacs;


    }

    public Contacts without(ContactData contact) {

        Contacts contacs = new Contacts(this);

        contacs.remove(contact);
        return contacs;


    }

}
