package joejava.xfire;

import java.sql.SQLException;

import joejava.xfire.Contact;

public interface ContactService {

	public Contact getContact(String name) throws SQLException;
}
