package com.htkapp.core.utils;

import com.htkapp.core.utils.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  时间工具类
 */
public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static String getThisYM() {
		// 当年当月
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		return sdf.format(date);
		
		
	/*	String str = sdf.format(new Date());
		return str;*/
	}
	
	public static String getThisYLM() {
		// 当年当月减一月
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -2);
		Date date = calendar.getTime();
		return sdf.format(date);
	}

	public static String getLastYM() {
		// 去年当月
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -13);
		Date date = calendar.getTime();
		return sdf.format(date);
	}
	
	
	
	
	public static String getThisJ() {
//		当年一月
		return (getThisYM().substring(0, 4) + "01");
	}

	public static String getLastJ() {
//		去年一月
		return (getLastYM().substring(0, 4) + "01");
	}

	public static String getThisD() {
//		当年12月
		return (getThisYM().substring(0, 4) + "12");
	}

	public static String getLastD() {
//		去年12月
		return (getLastYM().substring(0, 4) + "12");
	}
	
	public static String getLMFirstD() {
		String firstDay;
		// 上月第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		firstDay = format.format(cal_1.getTime()) + " 00:00:00";
		return firstDay;
	}
	public static String getTMFirstD() {
//		本月第一天
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
         c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
         String first = format.format(c.getTime())+" 00:00:00";
		return first;
         
	}
	public static String getTMLastD() {
		        //本月最后一天
		         Calendar ca = Calendar.getInstance();    
		         ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		         String lastDay = format.format(ca.getTime())+ " 23:59:59";
		         return lastDay;
	}
	/**
	 * 获取当前时间到毫秒
	 * @return
	 */
	public static String getCurDateTime(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
	}
	
	/**
	 * 将字符型转成日期类型
	 * @param src 
	 * @param format
	 * @return
	 */
	public static Date  paseString2Date(String source,String format){
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd";
		}
		 DateFormat formatDate = new SimpleDateFormat(format);   
		 try {
			return formatDate.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	/**
	 * 将字符型转成日期类型  
	 * @param src 
	 * @return
	 */
	public static Date  paseString2Date(String source){
		 DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");   
		 try {
			return formatDate.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
//	public static void main(String[] args) {
//		 System.out.println(getThisYM());
//		 System.out.println(getThisYLM());
//		 System.out.println(getLastYM());
//	}
}
	
//
//	public static String getLMLastD() {
////		上月最後一天
//		String lastDay;
//		Calendar cale = Calendar.getInstance();
//		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
//		lastDay = format.format(cale.getTime()) + " 23:59:59";
//		return lastDay;
//	}

	
	

