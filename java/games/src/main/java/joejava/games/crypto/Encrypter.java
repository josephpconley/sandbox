package joejava.games.crypto;

// Encrypter.java
//
//Applet designed to translate any text (entered in CAPS) into a cryptogram (single substitution cypher)

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import joejava.util.LangUtility;

public class Encrypter extends JPanel implements ActionListener{
	
    private JLabel inputLabel;
	private JTextArea text,ans;
	private JButton start;
	private CryptoUtility map = new CryptoUtility();
		
	public Encrypter(){
		
		inputLabel = new JLabel ("Enter plaintext(IN CAPS):");
		inputLabel.setFont(new Font("Verdana",Font.BOLD,14));
		
		text = new JTextArea (10,20);
		ans = new JTextArea (10,20);
		start = new JButton("Encrypt THIS");
		start.setFont(new Font("Verdana",Font.BOLD,14));
		start.addActionListener(this);
		start.setMnemonic('E');
		
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(true);
		ans.setLineWrap(true);
		ans.setWrapStyleWord(true);
		ans.setEditable(true);
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(new JLabel("Normal Text..."));
		left.add(new JScrollPane(text));
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(new JLabel("...Encoded Text"));
		right.add(new JScrollPane(ans));
		
		
		setLayout(new FlowLayout());
		
		add(inputLabel);
		add(left);
		add(right);
		add(start);		
		
		setBackground(Color.orange);


  }
  
   public void actionPerformed (ActionEvent event){
		if(event.getSource()==start){
			
			ans.setText(null);
			String english=text.getText();
			
			if(LangUtility.countLower(english)>0)
			JOptionPane.showMessageDialog(text,"I said only CAPS!!!");			
			else
			ans.setText(map.Code(english)+ "\n");
		}
	}
}