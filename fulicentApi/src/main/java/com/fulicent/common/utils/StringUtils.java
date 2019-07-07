package com.fulicent.common.utils;

public class StringUtils {

	public static boolean IsEmpty(Object src){
		if(src!=null && !"".equals(src.toString())){
			return false;
		}
		return true;
	}
	
	public static boolean IsNumber (Object obj) {
		if (obj instanceof Number) {
			return true;
		} else if (obj instanceof String){
			try{
				Double.parseDouble((String)obj);
				return true;
			}catch (Exception e) {
				return false;
			}
		}
		return false;
	}
}
