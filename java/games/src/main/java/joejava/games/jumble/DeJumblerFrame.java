package joejava.games.jumble;

import java.awt.Container;

import javax.swing.JFrame;

public class DeJumblerFrame extends JFrame{

	Container c;
	
	public static void main (String [] args) {
		new DeJumblerFrame();
	}
		
	public DeJumblerFrame(){
		setSize(600,400);
		setTitle("The DeJumbler!!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);
  		
  		c=getContentPane();
  		c.add(new DeJumbler());
  		c.validate();
	}
}

