package joejava.games.sudoku;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

import java.util.Arrays;
import javax.swing.border.Border;

public class SudokuApp extends JFrame implements ActionListener{
	
	Container c = new Container();
	JLabel start = new JLabel("Sudoku!!!");
	Sudoku su = new Sudoku();
	int[][]grid={{8,5,7,3,2,4,6,9,1},{0,0,0,0,0,5,8,0,3},{9,0,0,8,0,0,5,0,0},{2,8,0,5,1,0,4,7,0},{7,0,0,0,3,8,5,0,0},{0,0,5,4,7,0,0,0,8},{1,3,8,7,4,2,9,6,5},{6,2,9,3,5,1,4,8,7},{7,5,4,6,8,9,2,0,0}};
//	int[][]grid=new int[9][9];		BLANK
	boolean add=false;
	int rows=3;
	int cols=3;
	int index=0;
	int newEntry=99;
	int[] newPt=new int[2];
	
	JPanel[] subPanels=new JPanel[9];
	JPanel[] box=new JPanel[81]; 
	JLabel[] boxLabel=new JLabel[81];;
	JTextField[] empty = new JTextField[81]; 
    Font font = new Font("Helvetica",Font.BOLD,30); 
	
	Border outerBorder = BorderFactory.createLineBorder(Color.black, 5);
	Border innerBorder = BorderFactory.createLineBorder(Color.black, 2);
	Border boxBorder   = BorderFactory.createLineBorder(Color.black, 1);

	public static void main(String[] args){
		new SudokuApp();
	}
	
	public SudokuApp(){
		c=getContentPane();
		c.add(BorderLayout.NORTH,start);
		c.add(BorderLayout.CENTER,Grid(grid));
		c.validate();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setVisible(true);
  		setSize(600,600);
	}
	
//Shows grid
	public JPanel Grid(int[][] input){
	index=0;
	
	JPanel mainPanel = new JPanel(new GridLayout(rows, cols));
	mainPanel.setBorder(outerBorder);
	subPanels = new JPanel[rows*cols];

		for (int x=0;x<9;x++) {
		subPanels[x] = new JPanel(new GridLayout(rows, cols));
		subPanels[x].setBorder(innerBorder);
		mainPanel.add(subPanels[x]);
		}
 
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
			empty[index]=new JTextField(1);
			empty[index].setText(null);
						
			box[index] = new JPanel();
			boxLabel[index]=new JLabel(""+input[i-1][j-1]);	boxLabel[index].setFont(font);
			
			if(input[i-1][j-1]!=0) 
			box[index].add(boxLabel[index]);
			else{
			empty[index].setBackground(Color.cyan);empty[index].setFont(font);
			empty[index].addActionListener(this);
			box[index].add(empty[index]);
			}
			
			box[index].setBorder(boxBorder);
			subPanels[i-1].add(box[index]);
			
			index++;
			}
		}	
		
		System.out.println();

	return mainPanel;
	}
	
	public void redraw(){
		c.removeAll();
		c.add(BorderLayout.NORTH,start);
		c.add(BorderLayout.CENTER,Grid(grid));
		c.validate();
	}	
	
	
	public void actionPerformed(ActionEvent evt) {
		JTextField num = (JTextField)evt.getSource();
		newEntry=su.getIndex(empty);
		newPt=su.getGridLoc(newEntry);
		grid[newPt[0]][newPt[1]]=Integer.valueOf(num.getText());
		System.out.println(su.isLegal(newPt,grid));
		empty[newEntry].setText(null);
		redraw();
		
//		for(int i=0;i<9;i++)
//		System.out.print(Arrays.toString(grid[i])+" ");
	}
	
}