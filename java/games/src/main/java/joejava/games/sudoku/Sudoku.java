package joejava.games.sudoku;

import javax.swing.JTextField;

import joejava.util.ArrayUtility;
import joejava.util.MathUtility;

public class Sudoku{
	

//checks to see if an entry in any spot of a Soduku puzzle is legal (between 0 and 9)

	public boolean isNumLegal(int n){
		if(n<1 || n>9)
		return false;
		else
		return true;
	}
	/////////////////////////////////////


//determines number of empty blocks in a quadrant

	public int numEmpty (int quad,int[][] grid)
	{
		//quad should be 0 thru 8
	int count=0;
			
		for(int i=0;i<9;i++){
			if(grid[quad][i]==0)
			count++;
		}	
		
	return count;
	}

//Given an index from 0 to 80, returns the appropriate point
	public int[] getGridLoc(int index){
		int j=0;
		int pt[]=new int[2];
	
		while((index-j)%9!=0)
		j++;
				
		pt[0]=(index-j) / 9;
		pt[1]=j;

		return pt;
	}

//Given an array of JTextFields, finds one(SHOULD ONLY BE ONE) with text and returns its index

	public int getIndex(JTextField[] array){
	
		int index=99;
		for(int i=0;i<array.length;i++){
			if(array[i].getText().equals("")==false)
			index=i;
		}
		
		return index;
	}		
	
//Returns the coordinates of the point in the grid based on row and col

	public int[] getPt(int row, int col){
	int[] pt = new int[2];
	
		if(row< 3 && col < 3){
		pt[0]=0;	pt[1]=(row*3)+col;
		}
		
		if(row< 3 && col>2 && col<6){
		pt[0]=1; pt[1]=(row*3)+(col-3);
		}
		
		if(row< 3 && col>5){
		pt[0]=2; pt[1]=(row*3)+(col-6);
		}
		
		if(row>2 && row<6 && col < 3){
		pt[0]=3;	pt[1]=((row-3)*3)+col;
		}
		
		if(row>2 && row<6 && col>2 && col<6){
		pt[0]=4; pt[1]=((row-3)*3)+(col-3);
		}
		
		if(row>2 && row<6 && col>5){
		pt[0]=5; pt[1]=((row-3)*3)+(col-6);
		}
		
		if(row>5 && col < 3){
		pt[0]=6;	pt[1]=((row-6)*3)+col;
		}
		
		if(row>5 && col>2 && col<6){
		pt[0]=7; pt[1]=((row-6)*3)+(col-3);
		}
		
		if(row>5 && col>5){
		pt[0]=8; pt[1]=((row-6)*3)+(col-6);				
		}
		
	return pt;
	}


//Returns the number row of an entry

	public int getRowNum(int[] pt)	{
	
	int row=99;
	
		if(pt[0]< 3 && pt[1]< 3)
		row=0;
		
		if(pt[0]< 3 && pt[1]>2 && pt[1]<6)
		row=1;
		
		if(pt[0]< 3 && pt[1]>5)
		row=2;
				
		if(pt[0]> 2 && pt[0]<6 && pt[1]< 3)
		row=3;
		
		if(pt[0]> 2 && pt[0]<6 && pt[1]>2 && pt[1]<6)
		row=4;
		
		if(pt[0]> 2 && pt[0]<6 && pt[1]>5)
		row=5;
		
		if(pt[0]> 5 && pt[1]< 3)
		row=6;
		
		if(pt[0]> 5 && pt[1]>2 && pt[1]<6)
		row=7;
		
		if(pt[0]> 5 && pt[1]>5)
		row=8;
		
		return row;
	}
	
//Returns the row an entry is in

	public int[] getRow(int[] pt,int[][] grid )	{
	
	int[] row=new int[9];
	int nRow=getRowNum(pt);
	
		for(int i=0;i<9;i++){
		pt=getPt(nRow,i);
		row[i]=grid[pt[0]][pt[1]];
		}
	
	return row;
	}		
	 	
//Returns the number of  the column of an entry

	public int getColNum(int[] pt)	{
	
	int col=99;
	
		if( (pt[0]==0 || pt[0]==3 || pt[0]==6) && (pt[1]==0 || pt[1]==3 || pt[1]==6) )
		col=0;
		
		if( (pt[0]==0 || pt[0]==3 || pt[0]==6) && (pt[1]==1 || pt[1]==4 || pt[1]==7) )
		col=1;
		
		if( (pt[0]==0 || pt[0]==3 || pt[0]==6) && (pt[1]==2 || pt[1]==5 || pt[1]==8) )
		col=2;
		
		if( (pt[0]==1 || pt[0]==4 || pt[0]==7) && (pt[1]==0 || pt[1]==3 || pt[1]==6) )
		col=3;
		
		if( (pt[0]==1 || pt[0]==4 || pt[0]==7) && (pt[1]==1 || pt[1]==4 || pt[1]==7) )
		col=4;
		
		if( (pt[0]==1 || pt[0]==4 || pt[0]==7) && (pt[1]==2 || pt[1]==5 || pt[1]==8) )
		col=5;
		
		if( (pt[0]==2 || pt[0]==5 || pt[0]==8) && (pt[1]==0 || pt[1]==3 || pt[1]==6) )
		col=6;
		
		if( (pt[0]==2 || pt[0]==5 || pt[0]==8) && (pt[1]==1 || pt[1]==4 || pt[1]==7) )
		col=7;
		
		if( (pt[0]==2 || pt[0]==5 || pt[0]==8) && (pt[1]==2 || pt[1]==5 || pt[1]==8) )
		col=8;		
			
		return col;
	}	

//Returns the column an entry is in

	public int[] getCol(int[] pt,int[][] grid )	{
	
	int[] col=new int[9];
	int nCol=getColNum(pt);

		for(int i=0;i<9;i++){
		pt=getPt(i,nCol);
		col[i]=grid[pt[0]][pt[1]];
		}

		return col;
	}		
	 	

//checks to see if an entry is legal based on the other entries in that row

	public boolean isRowLegal(int[] pt, int[][] grid)
	{
	boolean bool=true;
	int[] row=getRow(pt,grid);
	int nCol =getColNum(pt);	
	
		for(int j=0;j<9;j++){
		if(grid[pt[0]][pt[1]]==row[j] && j!=nCol)
		bool=false;
		}
	return bool;
	}	
	
//checks to see if an entry is legal based on the other entries in that column

	public boolean isColLegal(int[] pt, int[][] grid)
	{
	boolean bool=true;
	int[] col=getCol(pt,grid);
	int nRow =getRowNum(pt);	
	
		for(int j=0;j<9;j++){
		if(grid[pt[0]][pt[1]]==col[j] && j!=nRow)
		bool=false;
		}
	return bool;
	}	

//used to determine if an entry is legal based on members of the same quadrant

	public boolean isBoxLegal(int[] pt, int[][] grid)
	{
	boolean bool=true;
		for(int i=0;i<9;i++){
			if(grid[pt[0]][pt[1]]==grid[pt[0]][i] && pt[1]!=i)
			bool=false;
		}
	return bool;
	}
		
	
//enumerates possibilities for an empty (0) space

	public int[] initPtPoss(int pt[], int grid[][]){	//if pt in grid is blank, will return poss
																//else, will return contents of pt
	int[] nums = new int[9];
	int index=0;
	
	if(grid[pt[0]][pt[1]]!=0){
	nums[0]=0;	index++;}
	else{
		for(int i=1;i<10;i++){
		grid[pt[0]][pt[1]]=i;
		
			if(isRowLegal(pt,grid)==true && isColLegal(pt,grid)==true && isBoxLegal(pt,grid)==true){	//INITIAL POSSIBILITIES
			nums[index]=i;
			index++;
			}	
		}
		grid[pt[0]][pt[1]]=0;		//reset empty space to original contents
	}
	return ArrayUtility.trim(nums,index);	
	}
	///////////////////////////////////////	
	
	public int[] totalBoxPoss(int box,int[][]grid){	//where box is in [0,8]
	
	int[][] allPoss=new int[9][];
	int[]pt = new int[2];
	pt[0]=box;
		
	for(int i=0;i<9;i++){
	pt[1]=i;
	allPoss[i]=initPtPoss(pt,grid);
	}
	
	int[]poss = MathUtility.union(allPoss);
	int[] bad={0};
	
	poss = MathUtility.subtractArray(poss,bad);
	
	return poss;
	}

//factor in rest of logic (implied runs, only one poss per row,col,or box)

//booleans to help with trickier logic stuff

	//see if a number poss is in only one certain row of a quadrant (0,1,2) (for implied runs)

/*
	public boolean rowRun (int num, int x, int[][]grid){
	return bool;
	}	
	

	//implied runs (should return number, column/row, number of column/row)

	public int[][]impliedRuns (int quad, int[][]grid)
	{
		//quadrant should be 1 thru 9
		
	int[] orig = quadOrig(quad);
	int[] pt = new int[2];
	int[][]poss = new int[numEmpty(quad,grid)][];		
	int index=0;		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				pt[0]=orig[0]+i;	pt[1]=orig[1]+j;
				
				if(grid[pt[0]][pt[1]]==0)
				poss[index]=sqPoss(pt,grid);
			}	
		}
	int[] union = math.union(poss);
	
	return poss;
	}
	
*/	
	
	
	
	
//Combining all elements of logic, checks to see if entry is legal	
	public boolean isLegal(int[] pt, int[][]grid){
	
		boolean test=false;
		
		if(isRowLegal(pt,grid) && isColLegal(pt,grid) && isBoxLegal(pt,grid) && isNumLegal(grid[pt[0]][pt[1]]))
		test=true;
			
		return test;
	}

		
}