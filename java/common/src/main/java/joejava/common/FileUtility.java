package joejava.util;

import java.util.*;
import java.io.*;

import org.apache.commons.io.FileUtils;

public class FileUtility{

	public static void main(String[] args) throws IOException{
		List<String[]> lines = readDelimitedFile("list.txt", ":");
		for(String[] a : lines){
			System.out.println("<dependency>");
			System.out.println("  <groupId>" + a[0] + "</groupId>");
			System.out.println("  <artifactId>" + a[1] + "</artifactId>");
			System.out.println("  <version>" + a[3] + "</version>");
			System.out.println("</dependency>");
		}
	}
	
	public static List<String[]> readDelimitedFile(String file, String delim) throws IOException{
		List<String> allLines = FileUtils.readLines(new File(file));
		List<String[]> arrays = new ArrayList<String[]>();
		
		for(String line : allLines){
			String[] a = line.split(delim);
			arrays.add(a);
		}
		
		return arrays;
	}
	
//Adds a calendar record to a file (should be monthly, annual, weekly, or once)
	public void addEntry(String record,String file){
		try{	
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter outFile = new PrintWriter(bw);
	
	 	   outFile.println();
			outFile.append(record);
			outFile.close();
		}
	
		catch (IOException e){
		System.out.println(e);
		}
	}	

//Prints the contents of a text file (file = "file.txt")
  public void printFile(String file) {
		
   	FileInputStream fis = null;
   	BufferedInputStream bis = null;
   	DataInputStream dis = null;

    try {
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      while (dis.available() != 0)
      System.out.println(dis.readLine()+" "+dis.available());
		
     
      fis.close();
      bis.close();
      dis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
//Returns numbers of lines in a textfile
  public int countLines(String file) {
		
   	FileInputStream fis = null;
   	BufferedInputStream bis = null;
   	DataInputStream dis = null;
		String test;
		int count=0;

    try {
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      while (dis.available() != 0){
      test=dis.readLine();	//this reduces dis.available()
		count++;
		}
		     
      fis.close();
      bis.close();
      dis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
	 
	 return count;
  }
//Reads contents of a text file and places into an array (as seen in Dictionary)
	public String[] toArray(String file){
		String word;
		StringTokenizer tokenizer;
		int count=0;
		String[] fileArray=new String[countLines(file)];

		FileInputStream fis = null;
   	BufferedInputStream bis = null;
   	DataInputStream dis = null;

		try{	
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);
		
			while(dis.available() != 0){
				file=dis.readLine();
				tokenizer = new StringTokenizer(file);
				word = tokenizer.nextToken();
				fileArray[count]=word;
				count++;
			}
		}
	
		catch (IOException e){
		System.out.println(e);
		}
	
	return fileArray;
	}	 
 

 
}

	

		
