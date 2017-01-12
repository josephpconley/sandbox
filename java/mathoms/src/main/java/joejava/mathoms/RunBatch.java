package joejava.mathoms;


//Code to run batch files or programs that can be started
//	using the command line

public class RunBatch {
	public static void main (String[] args){
		try{
			//Batch
			//Runtime.getRuntime().exec("out/test.bat");
			
			//Program
			Runtime.getRuntime().exec("C:/Program Files/Microsoft Office/OFFICE11/excel.exe");
	 		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
