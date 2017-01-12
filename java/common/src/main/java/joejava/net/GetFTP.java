package joejava.mathoms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

public class GetFTP {
	//FTP Configuration
	private static final String username = "a5882300";
	private static final String password = "iGE6JP";
	private static final String server = "ftp.jolt.890m.com";
	
	public static void main(String[] args){
		
//Send the file to the ISF Customs directory
		
		FTPClient client = new FTPClient();
		String ftpDir = "public_html";
		
		
		try {
			client.connect(server);
			System.out.println("Connected to "+server);
			client.login(username, password);

			client.changeWorkingDirectory(ftpDir);
			client.enterLocalPassiveMode();
			
			//Text file
			client.retrieveFile("test.txt",new FileOutputStream(new File("joe.txt")));
			
			//Only works for text files so far; no luck with excel workbooks or
			//jpeg's
			
			//JPEG
			//client.retrieveFile("bombadil.jpg",new FileOutputStream(new File("pic.jpg")));
			
			client.logout();
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		
	}
}
