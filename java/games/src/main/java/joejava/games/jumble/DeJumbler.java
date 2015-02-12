package joejava.games.jumble;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import joejava.util.*;

public class DeJumbler extends JPanel implements ActionListener{

	JLabel inputLabel,wordCountLabel;
	JTextField inputText;
	JTextArea outputText;
	JButton start;
	String hand;
	JPanel inputPanel, outputPanel;
	String[] bank;
	int count=0;
	JScrollPane ansPane;
	JScrollBar vBar;
	boolean autoScroll;
	
	public static void main (String [] args) {
		new DeJumbler ();
   }
	
	public DeJumbler(){
				
		inputLabel = new JLabel ("Enter your jumbled word:");
		inputLabel.setFont(new Font("Verdana",Font.BOLD,14));
		
		inputText = new JTextField(10);
		outputText = new JTextArea(10,20);
		start = new JButton("Find possible words!");
		start.setFont(new Font("Verdana",Font.BOLD,14));
		
		inputText.setEditable(true);
		outputText.setLineWrap(true);
		outputText.setEditable(true);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.add(new JLabel("Jumbled word"));
		ansPane =new JScrollPane(inputText);
		inputPanel.add(ansPane);
		
		outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.add(new JLabel("Dejumbled word(s)"));
		outputPanel.add(new JScrollPane(outputText));

		setLayout(new FlowLayout());
		
		add(inputLabel);
		add(inputPanel);
		add(outputPanel);
		add(start);		
		
		setBackground(Color.cyan);

		start.addActionListener(this);start.setMnemonic('F');
			
		wordCountLabel = new JLabel ("You have "+count+" matches");
		wordCountLabel.setFont(new Font("Verdana",Font.BOLD,14));
		add(wordCountLabel);
		
		inputText.requestFocus();
	}		

	public void printMatches(String hand){
		ArrayList<String> matches = LangUtility.getMatches(hand);
		wordCountLabel.setText("You have "+matches.size()+" matches");
		for(String s : matches){
			SwingUtility.printNow(0,wordCountLabel);
			outputText.append(s+ "\n");
			outputText.setCaretPosition(outputText.getDocument().getLength());
			SwingUtility.printNow(0,outputText);
		}
	}
		
	public void getMatches(){
		SortUtility.sortAlpha(hand);		//MUST be done after srt.handTest(sortAlpha makes all upperCase)

		/* Scrabble blank section
		//One blank
		if(hand.substring(0,1).equals("0")==true && hand.substring(1,2).equals("0")==false){
			for(int m=0;m<26;m++){
				String word=alpha.alpha[m]+hand.substring(1,7);
				printMatches(word);
		 	}
		}
		//Two blanks	
		if(hand.substring(0,2).equals("00")){
			for(int j=0;j<26;j++){
				for(int k=0;k<26;k++){
					String word=Character.toString(alpha.alpha[k])+Character.toString(alpha.alpha[j])+hand.substring(2,7);
					printMatches(word);
			 	}
			}
		}
		*/
		//No blanks	
		printMatches(hand);
	}

	public void actionPerformed (ActionEvent evt){	
		count=0;	wordCountLabel.setText("You have "+count+" matches");
		outputText.setText(null); SwingUtility.printNow(1,outputText);SwingUtility.printNow(1,wordCountLabel); //erases answers when search is rerun
		
		if(evt.getSource()==start){
			hand=inputText.getText().toLowerCase();
			getMatches();
		}

		inputText.requestFocus();	
	}
}	
		
	
