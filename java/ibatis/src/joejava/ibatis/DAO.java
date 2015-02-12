/** 
 *  Copyright BDP International, Inc.
 */
package joejava.ibatis;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DAO {
	
	public static SqlMapClient client;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("ibatis/config/config.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		}
		catch (Throwable t){
			t.printStackTrace();
		}		
	}
}
