package com.demo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {// "yyyy-MM-dd HH:mm:ss
	public static final String ChineseFormart = "yyyy年MM月dd日";
	public static final String CommonFormart = "yyyy-MM-dd";
	public static final String WholeCommonFormart = "yyyy-MM-dd HH:mm:ss";

	
	public static String getFormatDate(Date date, String format) {// 格式化日期，返回String类型的字符串
		if (date == null) {
			date = new Date();
		}
		if (StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(date);
	}
	public static String getFormatDate(Date date) {// 格式化日期，返回String类型的字符串
		if (date == null) {
			date = new Date();
		}
		String format = "yyyy-MM-dd";
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(date);
	}

	public static String getFormatDate() {// 格式化日期，返回String类型的字符串
		return getFormatDate(new Date(), "yyyy-MM-dd");
	}

	public static Date getFormatDate(String date, String format) {// 字符型日期转化成Date类型日期，可以指定格式
		if (StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sd = new SimpleDateFormat(format);
		Date rtnDate = null;
		try {
			rtnDate = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rtnDate;
	}

	public static Integer getMaxDayOfMonth(Integer year, Integer month) {// 获得某年某月的天数
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		int day = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	 /** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }

	public static String getFirstDayOfMonth(Integer month) {// 获得当前日期 所属月的第一天
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		if (month != null) {
			gcLast.set(Calendar.MONTH, month);
		}
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		return day_first;
	}
	public static String getFirstDayOfMonth(String month) {// 获得当前日期 所属月的第一天
		return getFirstDayOfMonth(Integer.valueOf(month));
	}

	public static String getLastDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		return s;
	}
	public static boolean isFirstDayOfMonth(String dateStr) {
		Date date = getFormatDate(dateStr, "yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(day == 1){
			return true;
		}
		return false;
	}
	public static Integer getFieldOfDate(Date date, String field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (StringUtils.isBlank(field)) {
			field = "YEAR";
		}

		if (field.equalsIgnoreCase("MONTH")) {
			return calendar.get(Calendar.MONTH) + 1;// 月份要加一
		}
		if (field.equalsIgnoreCase("DAY")) {
			return calendar.get(Calendar.DAY_OF_MONTH);
		}
		if (field.equalsIgnoreCase("HOUR")) {
			return calendar.get(Calendar.HOUR_OF_DAY);
		}
		if (field.equalsIgnoreCase("SECOND")) {
			return calendar.get(Calendar.SECOND);
		}
		return calendar.get(Calendar.YEAR);
	}

	public static String getFieldStrOfDate(Date date, String field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (StringUtils.isBlank(field)) {
			field = "YEAR";
		}

		if (field.equalsIgnoreCase("MONTH")) {
			Integer month = calendar.get(Calendar.MONTH) + 1;
			if (month < 10) {
				return "0" + month;
			} else {
				return String.valueOf(month);
			}
		}
		if (field.equalsIgnoreCase("DAY")) {
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			if (day < 10) {
				return "0" + day;
			} else {
				return String.valueOf(day);
			}
		}
		if (field.equalsIgnoreCase("HOUR")) {
			Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour < 10) {
				return "0" + hour;
			} else {
				return String.valueOf(hour);
			}
		}
		if (field.equalsIgnoreCase("MINUTE")) {
			Integer minute = calendar.get(Calendar.MINUTE);
			if (minute < 10) {
				return "0" + minute;
			} else {
				return String.valueOf(minute);
			}
		}
		if (field.equalsIgnoreCase("SECOND")) {
			Integer second = calendar.get(Calendar.SECOND);
			if (second < 10) {
				return "0" + second;
			} else {
				return String.valueOf(second);
			}
		}
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	public static Date getSysTime(boolean isEntire) {
		Date nowTime = null;
		Calendar c = Calendar.getInstance();
		String strTime = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " "
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		SimpleDateFormat format;
		if (isEntire) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			format = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			nowTime = format.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nowTime;
	}

	public static String getSysDateStr() {
		Calendar c = Calendar.getInstance();

		String strtime = c.get(Calendar.YEAR) + "-" + formatStr((c.get(Calendar.MONTH) + 1)) + "-"
				+ formatStr(c.get(Calendar.DATE));

		return strtime;
	}

	public static String getSysDateTimeStr() {
		Calendar c = Calendar.getInstance();
		String strtime = c.get(Calendar.YEAR) + "-" + formatStr((c.get(Calendar.MONTH) + 1)) + "-"
				+ formatStr(c.get(Calendar.DATE)) + " " + formatStr(c.get(Calendar.HOUR_OF_DAY)) + ":"
				+ formatStr(c.get(Calendar.MINUTE)) + ":" + formatStr(c.get(Calendar.SECOND));

		return strtime;
	}

	private static String formatStr(int input) {
		if (input >= 10) {
			return Integer.toString(input);
		}
		return "0" + Integer.toString(input);
	}

	public static Date parse(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		} else {
			Date date = null;
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	}

	public static String getSysDateYear() {
		Calendar c = Calendar.getInstance();

		String strtime = String.valueOf(c.get(Calendar.YEAR));

		return strtime;
	}

	public static String getSysDateMonth() {
		Calendar c = Calendar.getInstance();

		String strtime = String.valueOf(c.get(Calendar.MONTH) + 1);

		return strtime;
	}

	public static String getSysDateNextMonth() {
		Calendar c = Calendar.getInstance();

		String strtime = String.valueOf(c.get(Calendar.MONTH) + 2);

		return strtime;
	}

	public static String getNextYear(String year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.add(Calendar.YEAR, 1);
		year = String.valueOf(c.get(Calendar.YEAR));
		return year;
	}

	public static String getLastYear(String year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.add(Calendar.YEAR, -1);
		year = String.valueOf(c.get(Calendar.YEAR));
		return year;
	}

	/**
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            加减数目
	 * @param type
	 *            加减的类型，比如：Calendar.DAY_OF_MONTH
	 * @return
	 */
	public static Date getCalculateDate(Date date, Integer num, Integer type) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.set(type, calendar.get(type) + num);
		calendar.add(type, num);
		return calendar.getTime();
	}

	/**
	 * 
	 * @param day
	 *            表示跟周几比，去Calendar.Monday等
	 * @param num
	 *            表示本周，上周，下周，0，-1,1
	 * @param date
	 *            表示传入的日期跟周一相比较
	 * @return 获得今天是（本周/上周/下周）周几的第几天，星期日是第一天，星期一是第二天......按照中国惯例得到
	 */
	public static int getWeekPlus(Integer day, Integer num, Date date) {
		// 2,3,4,5,6,7,1
		// 1,2,3,4,5,6,7
		Calendar cd = Calendar.getInstance();
		if (date != null) {
			cd.setTime(date);
		}
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);// 今天周几
		if (dayOfWeek == 1) {// 今天周日
			dayOfWeek = 8;
		}
		if (day == 1) {// 如果跟周日比
			num += 1;
		}
		return dayOfWeek - day - num * 7;
	}

	/**
	 * 返回指定日期内的日子字符串集合 @param begin @param end @return List<String> @exception
	 */
	public static List<String> dateRange(Date begin, Date end) {
		return dateRange(begin, end, "yyyy-MM-dd");
	}

	/**
	 * 返回指定日期内的日子字符串集合 @param begin @param end @return List<String> @exception
	 */
	public static List<String> dateRange(Date begin, Date end, String format) {
		if (begin == null || end == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		List<String> resultList = new ArrayList<String>();
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		if (between < 0) {
			return null;
		}
		double day = between / (24 * 3600);

		for (int i = 0; i <= day; i++) {
			Calendar cd = Calendar.getInstance();
			cd.setTime(begin);
			cd.add(Calendar.DATE, i);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
			resultList.add(sdf.format(cd.getTime()));
		}
		return resultList;
	}

	public static List<String> dateRange(String begin, String end) {
		if (StringUtils.isEmpty(begin) || StringUtils.isEmpty(end)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateRange(sdf.parse(begin), sdf.parse(end));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer compareDate(String begin, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return compareDate(sdf.parse(begin), sdf.parse(end));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 判断两个日期大小
	 * 如果为1代表begin大于end，如果为0代表begin等于end，如果为-1代表begin小于end
	 * @param begin
	 * @param end
	 * @return
	 */
	public static Integer compareDate(Date begin, Date end) {
		double between = end.getTime() - begin.getTime();
		return between < 0 ? 1 : (between == 0 ? 0 : -1);
	}

	/**
	 * @param formatDate @param calculateDate @return List<String> @exception
	 */
	public static List<String> dateRangeWeek(Date begin, Date end) {
		if (begin == null || end == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> resultList = new ArrayList<String>();

		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		double week = between / (7 * 24 * 3600);

		for (int i = 0; i <= week; i++) {
			Calendar cd = Calendar.getInstance();
			cd.setTime(begin);
			cd.add(Calendar.WEEK_OF_YEAR, i);// 增加1周
			// cd.add(Calendar.MONTH, n);//增加一个月
			resultList.add(sdf.format(cd.getTime()));
		}
		return resultList;

	}

	/**
	 * @param formatDate @param calculateDate @return List<String> @exception
	 */
	public static List<String> dateRangeMonth(Date begin, Date end) {

		if (begin == null || end == null) {
			return null;
		}
		int len = getMonthSpace(begin, end);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> resultList = new ArrayList<String>();

		for (int i = 0; i <= len; i++) {
			Calendar cd = Calendar.getInstance();
			cd.setTime(begin);
			cd.add(Calendar.MONTH, i);// 增加一个月
			resultList.add(sdf.format(cd.getTime()));
		}

		return resultList;
	}

	/**
	 * @param formatDate @param calculateDate @return List<String> @exception
	 */
	public static List<String> dateRangeMonth(String year) {
		List<String> dates = new ArrayList<String>();
		Integer workYear = Integer.parseInt(year);
		for (int i = workYear; i <= workYear + 1; i++) {
			if (i == workYear) {
				for (int j = 9; j <= 12; j++) {
					dates.add(i + (j == 9 ? "-09" : "-" + j));
				}
			} else {
				for (int j = 1; j <= 8; j++) {
					dates.add(i + "-0" + j);
				}
			}
		}
		return dates;
	}

	public static List<String> dateRangeDay(String workYear, String month) {
		Calendar calendar = Calendar.getInstance();
		Date theDate = getFormatDate(workYear + month + "01", "yyyyMMdd");
		calendar.setTime(theDate);

		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		Date startDate = calendar.getTime();

		// 上个月最后一天
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为该月最后一天
		Date endDate = calendar.getTime();

		return dateRange(startDate, endDate);
	}

	public static long minusDate(String s1, String s2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(s1);
			Date d2 = df.parse(s2);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			return days;
		} catch (Exception e) {

		}
		return -1000;
	}

	/**
	 * 
	 * @param date1
	 *            <String>
	 * @param date2
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return getMonthSpace(sdf.parse(date1), sdf.parse(date2));
	}

	private static int getMonthSpace(Date begin, Date end) {
		int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(begin);
		c2.setTime(end);

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result);
	}


	public static void main(String[] args) throws ParseException {
		/*
		 * Calendar cal = Calendar.getInstance(); int
		 * maxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH); SimpleDateFormat
		 * formatter3=new SimpleDateFormat("yyyy-MM-"+maxDay);
		 * System.out.println(formatter3.format(cal.getTime()));
		 * 
		 * SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); Date date =
		 * df.parse("2012-12-31"); cal.clear(); cal.setTime(date);
		 * cal.add(Calendar.MONTH,2);
		 * System.out.println(df.format(cal.getTime()));
		 * System.out.println(DateUtils.getFieldStrOfDate(new Date(), "day"));
		 * System.out.println(DateUtils.getFormatDate("2010-09-25 11:16",
		 * null)); System.out.println(DateUtils.getFieldStrOfDate(new Date(),
		 * "MINUTE")); System.out.println(".............");
		 * System.out.println(DateUtils.getCalculateDate(new Date(), -1,
		 * Calendar.SECOND)); System.out.println(DateUtils.getCalculateDate(new
		 * Date(), -1, Calendar.MINUTE));
		 * System.out.println(DateUtils.getCalculateDate(new Date(), -1,
		 * Calendar.HOUR_OF_DAY));
		 * System.out.println(DateUtils.getCalculateDate(new Date(), -1,
		 * Calendar.DAY_OF_MONTH));
		 * System.out.println(DateUtils.getCalculateDate(new Date(), -1,
		 * Calendar.MONTH)); System.out.println(DateUtils.getCalculateDate(new
		 * Date(), -1, Calendar.YEAR));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.MONDAY,-1));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.MONDAY,0));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.MONDAY,1));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.SUNDAY,-2));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.SUNDAY,0));
		 * System.out.println(DateUtils.getWeekPlus(Calendar.SUNDAY,1));
		 * System.out.println(DateUtils.getSysDateTimeStr().substring(10));
		 */
		// List<String> list = dateRange("2014-11-12", "2014-11-14");
		/*
		 * List<String> list =
		 * DateUtils.dateRange(DateUtils.getFormatDate("2014-11-12",
		 * null),DateUtils.getCalculateDate(DateUtils.getFormatDate(
		 * "2014-11-30", null),1, Calendar.DAY_OF_YEAR)); for(String s : list){
		 * System.out.println(s); }
		 * System.out.println(DateUtils.getSysDateTimeStr());
		 */
		System.out.println(minusDate("2015-09-13", "2015-09-12"));
		System.out.println(getFirstDayOfMonth(getFieldOfDate(new Date(), "MONTH")));
	}
	
	public static List<String> getOpTimes(String opTime){
		List<String> opTimes = new ArrayList<String>();
		String month = opTime.split("-")[1];
		Integer year = Integer.parseInt(opTime.split("-")[0]);
		String monthStr = "09-30,10-31,11-30,12-31,01-31,02-28,03-31,04-30,05-31,06-30,07-31,08-31,";
		if((year%400==0)||((year%100!=0)&&(year%4==0))){
			monthStr = monthStr.replace("02-28,", "02-29,");
		}
		if(Integer.parseInt(month)<9){
			year--;
		}
		monthStr = monthStr.split(month+"-")[0]+opTime.split("-")[1]+"-"+opTime.split("-")[2];
		String[] months = monthStr.split(",");
		for (int i = 0; i < months.length; i++) {
			if(Integer.parseInt(months[i].split("-")[0])<9){
				opTimes.add((year+1)+"-"+months[i]);
			}else{
				opTimes.add((year)+"-"+months[i]);
			}
		}
		return opTimes;
	}
	private static Map<String, String> maxDayMap = new HashMap<String, String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1002478086099991499L;
		{
			put("09", "30");
			put("10", "31");
			put("11", "30");
			put("12", "31");
			put("01", "31");
			put("03", "31");
			put("04", "30");
			put("05", "31");
			put("06", "30");
			put("07", "31");
			put("08", "31");
		}
	};
	public static String getMaxDayOfMonthStr(String monthValue) {
		String[] monthValueArray = monthValue.split("-");
		String  maxDay = "";
		if(monthValueArray[1].equals("02")){
			int year = Integer.parseInt(monthValueArray[0]);
			if((year%400==0)||((year%100!=0)&&(year%4==0))){
				maxDay = "29";
			}
			else{
				maxDay = "28";
			}
		}
		else{
			maxDay = maxDayMap.get(monthValueArray[1]);
		}
		return monthValue + "-" + maxDay;
	}
	
	/** 
     * 判断时间格式 格式必须为“YYYY-MM-dd” 
     * 2004-2-30 是无效的 
     * 2003-2-29 是无效的 
     * @param sDate 
     * @return 
     */  
    public static boolean isValidDate(String str) {  
        //String str = "2007-01-02";  
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        try{  
            Date date = (Date)formatter.parse(str);  
            return str.equals(formatter.format(date));  
        }catch(Exception e){  
            return false;  
        }  
    }  
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s,String format){
        String res="";
        if(StringUtils.isNotBlank(s)){
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        	Date date = Timestamp.valueOf(s);
        	res = simpleDateFormat.format(date);
        }
        return res;
    }
    
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s,String format) throws ParseException{
        String res="";
        if(StringUtils.isNotBlank(s)){
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        	Date date = simpleDateFormat.parse(s);
        	long ts = date.getTime();
        	res = String.valueOf(ts);
        }
        return res;
    }
    
    
    
    
    
    
    
    
    
    
}