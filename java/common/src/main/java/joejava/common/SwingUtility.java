package joejava.util;

import javax.swing.*;
import java.awt.*;

public class SwingUtility{

	public static Color felt = new Color(20,64,0);
	
	public SwingUtility(){}
	
//Prints the Swing JComponent immediately
	public static void printNow(int time,JComponent obj){
		Dimension dim=obj.getSize();
		obj.validate();
		obj.paintImmediately(0,0,dim.width,dim.height);
		try{
			Thread.sleep(time);
			}
			catch (InterruptedException e){
			}
	}
	
	public static void Delay(int delay){	//delays process 
	
		try{
		Thread.sleep(delay);
		}
		catch (InterruptedException e){
		}
	}
}
	