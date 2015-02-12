package joejava.games.crypto;

import java.awt.Container;

import javax.swing.JApplet;

// Encryptlet.java
//
//Applet designed to translate any text (entered in CAPS) into a cryptogram (single substitution cypher)


public class Encryptlet extends JApplet{

	Container c;
	
    public void init(){
    	setSize(550,300);
    	
    	c=getContentPane();
    	c.add(new Encrypter());
    	c.validate();
    }
}