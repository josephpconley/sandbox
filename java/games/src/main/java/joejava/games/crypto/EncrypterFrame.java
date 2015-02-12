package joejava.games.crypto;

import java.awt.Container;

import javax.swing.JFrame;

public class EncrypterFrame extends JFrame {

	Container c;
	
	public static void main (String [] args) {
		double startTime,endTime,time;
		startTime=System.currentTimeMillis();
		new EncrypterFrame();
		
		endTime=System.currentTimeMillis();
		time=(endTime-startTime)/1000;		
								
		System.out.println("Program took " + time  +" seconds.");
    }
	
	public EncrypterFrame(){
		setSize(600,600);
		setTitle("The Encrypter!!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);
  		
  		c=getContentPane();
  		c.add(new Encrypter());
  		c.validate();
	}
}
