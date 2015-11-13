package com.pingan.cong.utils;

public class StringUtil {

	public static boolean isBlank(String str) {
		
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 首字母转大写
	 * @param s
	 * @return
	 */
    public static String toUpperCaseFirstOne(String s) {
    	
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
	
	  /** 
     * 使用java正则表达式去掉多余的.与0  例：1.0--->1  1.10------>1.1
     * @param s 
     * @return  
     */  
    public static String subZeroAndDot(String s){  
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }

}
