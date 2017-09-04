package org.jeecgframework.web.system.util;

import java.text.SimpleDateFormat;

public class DbReaderUtil {
	public static String readDate(Object obj){ 
		if(obj==null){
			return "";
		}else{
		    SimpleDateFormat formatter; 
		    formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		    String ctime = formatter.format(obj); 
		    return ctime; 
		}
	} 
	public static String readDateTime(Object obj){ 
		if(obj==null){
			return "";
		}else{
		    SimpleDateFormat formatter; 
		    formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
		    String ctime = formatter.format(obj); 
		    return ctime; 
		}
	} 
	public static Double readDouble(Object obj) {
		if(obj==null) {
			return 0.00;
		}else {
			return Double.parseDouble(obj.toString());
		}
	}
	public static Integer readInteger(Object obj) {
		if(obj==null) {
			return 0;
		}else {
			return Integer.parseInt(obj.toString());
		}
	}
	public static String readString(Object obj) {
		if(obj==null) {
			return "";
		}else {
			return obj.toString();
		}
	}
}
