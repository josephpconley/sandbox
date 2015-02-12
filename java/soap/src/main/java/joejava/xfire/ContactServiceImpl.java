package joejava.xfire;

import java.sql.SQLException;

import joejava.util.DAO;
import joejava.xfire.Contact;

public class ContactServiceImpl implements ContactService {

	private Contact me;
	
	public ContactServiceImpl(){}
	
	public Contact getContact(String name) throws SQLException {
		me = (Contact)DAO.client.queryForObject("getContactById", name);
		return me;
	}
}
