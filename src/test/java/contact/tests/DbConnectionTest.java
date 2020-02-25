package contact.tests;

import contact.contactdata.ContactData;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/addressbook","root","password");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, firstname, lastname, address, home FROM addressbook WHERE deprecated='0000-00-00 00:00:00'");
            List<ContactData>contacts = new ArrayList<>();
            while(rs.next()) {
                contacts.add(new ContactData().withId(rs.getInt("id")).withFirstname(rs.getString("firstname"))
                        .withLastname(rs.getString("lastname")).withAddress(rs.getString("address"))
                        .withHome(rs.getString("home")));
            }
            rs.close();
            st.close();
            con.close();

            for (ContactData c : contacts) {
                System.out.println(c);
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
