package joejava.mathoms;

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMusic extends JApplet implements ActionListener{
	
	JButton play,stop;
	AudioClip audioClip;
	Container c = new Container();
	JTextField track = new JTextField(10);
	JPanel menu = new JPanel();
	String num="0";

	public void init(){
		track.setText(num);
	
		play = new JButton("  Play  ");
		add(play);		play.addActionListener(this);
		stop = new JButton("  Stop  ");
		add(stop);
		stop.addActionListener(this);
				
		menu.add(track);menu.add(play);menu.add(stop);
		
		c=getContentPane();
		
		c.add(menu,BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent ae){
		JButton source = (JButton)ae.getSource();
		num=track.getText();
		audioClip=getAudioClip(getCodeBase(), "mv"+num+".wav");
		
		if (source.getLabel() == "  Play  "){
			audioClip.play();
		}
		else if(source.getLabel() == "  Stop  "){
			audioClip.stop();
		}
	}
}