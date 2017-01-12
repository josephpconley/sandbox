package joejava.mathoms;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class WritePDF {
	  static String para =

		  "Lightning flashed across the sky. Thunder rumbled in the distance. " +
		  "The wind began to moan, as if in pain. The good people of the village " +
		  "huddled together in the common room. Fear glistened in their eyes and " +
		  "their hearts pounded with terror. Evil was descending into their " +
		  "valley, and there was nothing they could do to save their lives ...";

	  public static void main (String [] args) throws Exception  {
		  
		  
		  Document doc = new Document ();
		  PdfWriter.getInstance (doc, new FileOutputStream ("descent.pdf"));
		  doc.open ();

		  Paragraph p = new Paragraph (para);
		  p.setAlignment (Element.ALIGN_JUSTIFIED);	
		  doc.add (p);

		  doc.close ();
		  
	  }
}
