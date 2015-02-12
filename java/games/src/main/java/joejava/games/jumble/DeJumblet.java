package joejava.games.jumble;

import java.awt.Container;

import javax.swing.JApplet;

public class DeJumblet extends JApplet{

	Container c;
	
	public void init(){
		setSize(600,400);
		c=getContentPane();
		c.add(new DeJumbler());
		c.validate();
	}
}