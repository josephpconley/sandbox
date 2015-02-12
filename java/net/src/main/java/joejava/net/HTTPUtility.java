package joejava.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPUtility {
	
	/**
	* Reads data from the data reader and posts it to a server via POST request.
	* data - The data you want to send
	* endpoint - The server's address
	* output - writes the server's response to output
	* @throws Exception
	*/
	public static void postData(Reader data, URL endpoint, Writer output) throws Exception{
		HttpURLConnection urlc = null;
		try{
			urlc = (HttpURLConnection) endpoint.openConnection();
			
			try{
				urlc.setRequestMethod("POST");
			} catch (ProtocolException e){
				throw new Exception("Shouldn't happen: HttpURLConnection doesn't support POST??", e);
			}
	
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);
			urlc.setAllowUserInteraction(false);
			urlc.setRequestProperty("Content-type", "application/xml;");	//"text/xml; charset=" + "UTF-8");
			OutputStream out = urlc.getOutputStream();
			
			try{
				Writer writer = new OutputStreamWriter(out, "UTF-8");
				pipe(data, writer);
				writer.close();
			} catch (IOException e){
				throw new Exception("IOException while posting data", e);
			} finally{
				if (out != null)
					out.close();
			}
			InputStream in = urlc.getInputStream();
			
			try{
				Reader reader = new InputStreamReader(in);
				pipe(reader, output);
				reader.close();
			} catch (IOException e){
				throw new Exception("IOException while reading response", e);
			} finally{
				if (in != null)
					in.close();
			}
		} catch (IOException e){
			throw new Exception("Connection error (is server running at " + endpoint + " ?): " + e);
		} finally{
			if (urlc != null)
				urlc.disconnect();
		}
	}
	/**
	* Pipes everything from the reader to the writer via a buffer
	*/
	private static void pipe(Reader reader, Writer writer) throws IOException{
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0)
		{
		writer.write(buf, 0, read);
		}
		writer.flush();
	}
	
	public static void postJSON(String json, URL endpoint, Writer output) throws Exception{
		HttpURLConnection urlc = null;
		try{
			urlc = (HttpURLConnection) endpoint.openConnection();
			
			try{
				urlc.setRequestMethod("POST");
			} catch (ProtocolException e){
				throw new Exception("Shouldn't happen: HttpURLConnection doesn't support POST??", e);
			}
	
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
			urlc.setUseCaches(false);
			urlc.setAllowUserInteraction(false);
			urlc.setRequestProperty("Content-type", "application/xml;");	//"text/xml; charset=" + "UTF-8");
			OutputStream out = urlc.getOutputStream();
			
			try{
				Writer writer = new OutputStreamWriter(out, "UTF-8");
				writer.write(json);
				writer.flush();
				writer.close();
			} catch (IOException e){
				throw new Exception("IOException while posting data", e);
			} finally{
				if (out != null)
					out.close();
			}
			
			InputStream in = urlc.getInputStream();
			try{
				Reader reader = new InputStreamReader(in);
				pipe(reader, output);
				reader.close();
			} catch (IOException e){
				throw new Exception("IOException while reading response", e);
			} finally{
				if (in != null)
					in.close();
			}			
		} catch (IOException e){
			throw new Exception("Connection error (is server running at " + endpoint + " ?): " + e);
		} finally{
			if (urlc != null)
				urlc.disconnect();
		}
	}
	
	public static void main(String[] args) throws Exception{
		FileReader xml = new FileReader("post.xml");
		URL endpoint = new URL("http://localhost:808080/hubbub/posts");
		Writer output = new PrintWriter("test.txt");
		
		//postData(xml,endpoint,output);
		String json = "{\"_id\":1,\"sourceId\":1,\"feed\":1,\"messages\":[{\"id\":1,\"from\":\"from\",\"to\":\"to\",\"body\":\"Hey\",\"timestamp\":\"Aug 29, 2011 1:32:23 PM\"},{\"id\":2,\"from\":\"from\",\"to\":\"to\",\"body\":\"Hey\",\"timestamp\":\"Aug 29, 2011 1:32:23 PM\"},{\"id\":3,\"from\":\"from\",\"to\":\"to\",\"body\":\"Hey\",\"timestamp\":\"Aug 29, 2011 1:32:23 PM\"}]}";
		postJSON(json, new URL("http://localhost:9000/conversation/save"), output);
	}
}