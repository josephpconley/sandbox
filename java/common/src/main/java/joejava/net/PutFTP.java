package joejava.mathoms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class PutFTP {
	//FTP Configuration
	private static final String username = "a5882300";
	private static final String password = "iGE6JP";
	private static final String server = "ftp.jolt.890m.com";
	
	public static void main(String[] args){
		
//Send the file to the ISF Customs directory
		
		FTPClient client = new FTPClient();
		String inDir = "public_html";
		
		File file = new File("out/Rejects.xls");
		
		try {
			client.connect(server);
			System.out.println("Connected to "+server);
			client.login(username, password);

			client.changeWorkingDirectory(inDir);
			client.enterLocalPassiveMode();
			
			StringBuffer sb = new StringBuffer();
			sb.append("Test file");
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
			
			//Text file
			client.storeFile("test.txt",in);
			
			//Excel file
			client.storeFile("Rejects.xls",new FileInputStream(file));
			
			in.close();
			
			client.logout();
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		
	}
}
