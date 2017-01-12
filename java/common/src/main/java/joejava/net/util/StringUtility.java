/** 
 *  Copyright BDP International, Inc.
 */
package joejava.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class: StringUtility
 *
 * Description:  
 * Created on Aug 28, 2008
 *
 * @author Adam Stokar
 * @version 
 */
public class StringUtility {
	
	private static String goodChars = 
		" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\n!@#$%^&*()_+-=<>?:\"{}|,./;'[]\\`~'";
	
	private static String alphaNumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static boolean checkEmail(String email) {
		if(email.indexOf(" ") > -1) {
			return false;
		}
        String filter = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.(?:[a-z]{2}|com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum)$";
        if(!email.toLowerCase().matches(filter))    { 
            return false;
        }else {
            return true;
        }
    }
	
	public static String padRight(String str, int length) {
		if(str == null) {str = "";}
		if(str.length() > length){return str.substring(0,length);}
		while(str.length() < length) {
			str += " ";
		}
		return str;
	}
	
	public static String padLeft(String str, int length) {
		if(str == null) {str = "";}
		if(str.length() > length){return str.substring(0,length);}
		String padding = "";
		while(str.length() + padding.length() < length) {
			padding += "0";
		}
		return padding + str;
	}
	
	public static String stripBadChars(String s) {
		String result = "";
		for(int i=0; i<s.length(); i++) {
			if(goodChars.indexOf(s.charAt(i)) >= 0) {
				result += s.charAt(i);
			}
		}
		return result;
	}
	
	public static String escapeQuote(String s){
		String result = "";
		String quote = "\'";
		String slash = "\\";
		for(int i=0;i<s.length();i++){
			if(quote.indexOf(s.charAt(i)) == 0 &&
					slash.indexOf(s.charAt(i-1)) != 0){
				result += "\\\'";
			}else{
				result += s.charAt(i);
			}
		}
		return result;
	}
	
	public static String stripEscapeQuote(String s){
		String result = "";
		String quote = "\'";
		String slash = "\\";
		for(int i=0;i<s.length();i++){
			if(quote.indexOf(s.charAt(i)) == 0 &&
					slash.indexOf(s.charAt(i-1)) == 0){
				result = result.substring(0, i-1);	//remove backslash when saving to database
			}
			result += s.charAt(i);
		}
		return result;
	}
	
	public static String getAlphanumeric(String s) {
		String result = "";
		for(int i=0; i<s.length(); i++) {
			if(alphaNumeric.indexOf(s.charAt(i)) >= 0) {
				result += s.charAt(i);
			}
		}
		return result;		
	}
	
	public static int getInt(String value) {
		int num = 0;
		try {
			num = Integer.parseInt(value);
		} catch (NumberFormatException e) {}
		return num;
	}
	
	public static double getDouble(String value) {
		double num = 0.0;
		try {
			num = Double.parseDouble(value);
		} catch (NumberFormatException e) {}
		return num;
	}
	
	public static Date getDate(String day, String month, String year) {
		DateFormat dFormat = new SimpleDateFormat("ddMMMyyyy");
		Date d = null;
		try {
			d = dFormat.parse(day + month + year);
		} catch (ParseException e) {}
		return d;
	}
	
	public static Date getDateTime(String value) {
		DateFormat tFormat = new SimpleDateFormat("dMMMyyyy hh:mm a");
		Date d = null;
		try {
			d = tFormat.parse(value);
		} catch (ParseException e) {}
		return d;
	}
	
	public static String getImporterRefFormat(String refNo){
		if("-".equals(refNo.substring(2,3))){
			return "NN-NNNNNNNXX";
		}else if("-".equals(refNo.substring(6,7)) &&
					!"-".equals(refNo.substring(3,4))){
			return "YYDDPP-NNNNN";
		}else if("-".equals(refNo.substring(3,4)) && 
				"-".equals(refNo.substring(6,7))){
			return "NNN-NN-NNNN";
		}else{
			return "NNNNNNNNN";
		}
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
