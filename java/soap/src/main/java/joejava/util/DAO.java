/** 
 *  Copyright BDP International, Inc.
 */
package joejava.util;

import java.io.Reader;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * Class: DAO
 *
 * Description:  This is the single class to access the Database.  All database
 * calls must be made using the <b>client</b> member of this class.
 * \
 * @see http://ibatis.apache.org/onlinehelp.html
 * 
 * Created on Jul 15, 2008
 *
 * @author Adam Stokar
 * @version 
 */
public class DAO {
	
	private static Logger logger = Logger.getLogger(DAO.class);
	
	public static SqlMapClient client;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("ibatis/config/config.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		}
		catch (Throwable t){
			logger.error(t.getMessage());
			t.printStackTrace();
		}		
	}
}
