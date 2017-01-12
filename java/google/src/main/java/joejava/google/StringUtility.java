package joejava.google;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtility {
	
	public static Date getDate(String day, String month, String year) {
		DateFormat dFormat = new SimpleDateFormat("ddMMMyyyy");
		Date d = null;
		try {
			d = dFormat.parse(day + month + year);
		} catch (ParseException e) {}
		return d;
	}
	
	public static String stripPunc(String s){
		String result = "";
		
		for(int i=0;i<s.length();i++){
			if(!isPunc(s.charAt(i))){
				result += s.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * Determines if the given character is punctuation
	 */
	public static boolean isPunc(char ch){

		if(!Character.isLetter(ch) && ch != (" ").charAt(0) &&
							ch != ("-").charAt(0) && ch != ("'").charAt(0) )	
			return true;
		else
			return false;
	}
	
	public static String printBean(Object bean)	{
		
		StringBuffer buffer=new StringBuffer();
		Class objClass=bean.getClass();
		Method[] methods=objClass.getMethods();
		buffer.append(bean.getClass().getName()+"[");
		
		for (Method method : methods){
			if (method.getName().startsWith("get") && method.getParameterTypes().length==0){
				String name=method.getName();
				buffer.append(name);
				buffer.append("=");
				try	{
					buffer.append(method.invoke(bean));
				}catch(Exception exp){
					exp.printStackTrace();
				}
				buffer.append(" ");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
}
