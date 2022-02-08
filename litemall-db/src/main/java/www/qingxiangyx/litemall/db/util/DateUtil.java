package www.qingxiangyx.litemall.db.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Eleven.Wang
 * on 2017/8/18.
 */
@Slf4j
public class DateUtil {

	private static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyyMMdd");

	private static final DateTimeFormatter formatters2 = DateTimeFormatter.ofPattern("yyyyMM");

	private static final DateTimeFormatter yearFormatters = DateTimeFormatter.ofPattern("yyyy");

	private static final DateTimeFormatter formatters3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter formatters4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static long dayZeroTimestamp(LocalDate localDate){
		Calendar calendar = Calendar.getInstance();
		calendar.set(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth(),0,0,0);
		return calendar.getTime().getTime();
	}


	public static String formatLocalDate(LocalDate date) {
		return date.toString().replaceAll("-", "");
	}

	public static String getYYYYMMDD(LocalDate date){
		return date.format(formatters);
	}

	public static LocalDate parse(String dt){
		if(StringUtils.isBlank(dt)){
			return null;
		}
		try{
			return LocalDate.parse(dt,formatters3);
		}catch (Exception e){
			log.info("parse dt {} error",dt,e);
			return null;
		}
	}

	public static LocalDate parseYYYYMMDD(String dt){
	    if(StringUtils.isBlank(dt)){
	        return null;
        }
		try{
	        return LocalDate.parse(dt,formatters);
        }catch (Exception e){
            log.info("parse dt {} error",dt,e);
	        return null;
        }
	}

	public static LocalDate parseYYYYMM(String dt){
		if (StringUtils.isEmpty(dt)){
			return null;
		}
		try {
			return LocalDate.parse(dt+"01",formatters);
		} catch (Exception e){
			log.info("parse dt {} error",dt,e);
			return null;
		}
	}

	public static String getLocalString(LocalDate date){
		return date.format(formatters3);
	}

	public static String getMD(LocalDate date) {
		return String.format("%d-%d",date.getMonthValue(),date.getDayOfMonth());
	}

	public static String getYYYYMM(LocalDate date){
		return date.format(formatters2);
	}

	public static String getYYYY(LocalDate date){
		return date.format(yearFormatters);
	}

	public static LocalDate getLocalDateOfTimestamp(long timestamp) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone).toLocalDate();
	}

	public static List<String> getBetweenY(LocalDate startDate,LocalDate endDate){
		if(endDate.isBefore(startDate)){
			return null;
		}
		List<String> list = new ArrayList<>();
		for(int i= startDate.getYear();i<=endDate.getYear();i++){
			list.add(String.valueOf(i));
		}
		return list;
	}

	public static List<String> getBetweenYearIndex(String indexPrefix,LocalDate startDate,LocalDate endDate){
		if(endDate.isBefore(startDate)){
			return null;
		}
		List<String> list = new ArrayList<>();
		for(int i= startDate.getYear();i<=endDate.getYear();i++){
			list.add(indexPrefix+String.valueOf(i)+"*");
		}
		return list;
	}

	public static List<String> getBetweenYM(LocalDate startDate,LocalDate endDate){
		if(endDate.isBefore(startDate)){
			return null;
		}
		List<String> list = new ArrayList<>();
		int betweenMonth =(endDate.getYear()-startDate.getYear())*12+(endDate.getMonthValue() -startDate.getMonthValue());
		for(int i=0;i<=betweenMonth;i++){
			list.add(getYYYYMM(startDate.plusMonths(i)));
		}
		return list;
	}

	public static Long getBetweenMonths(LocalDate startDate,LocalDate endDate){
		return ChronoUnit.MONTHS.between(startDate, endDate);
	}

	public static LocalDate getLocalDateByESDate(int esDate) {
		return LocalDate.parse(String.valueOf(esDate), DateTimeFormatter.ofPattern("yyyyMMdd"));
	}

	public static long toEpochMilli(int esDate){
		LocalDate localDate = getLocalDateByESDate(esDate);
		return toEpochMilli(localDate);
	}

    public static long monthToEpochMilli(int esMonth){
        LocalDate localDate = getLocalDateByESDate(esMonth*100+1);
        return toEpochMilli(localDate);
    }

	public static long dayEndTimestamp(int esDate){
		LocalDate localDate = getLocalDateByESDate(esDate).plusDays(1);
		return toEpochMilli(localDate)-1;
	}

	public static List<Integer> getBetweenYMD(LocalDate startDate,LocalDate endDate){
		if(endDate.isBefore(startDate)){
			return new ArrayList<>(0);
		}
		int year = endDate.getYear();
		int month = endDate.getMonthValue();
		int day = endDate.getDayOfMonth();
		List<Integer> days = new ArrayList<>();
		days.add((year * 100 + month) * 100 + day);
		while(year != startDate.getYear() || month != startDate.getMonthValue() || day != startDate.getDayOfMonth()) {
			if(day > 1) {
				day --;
			} else {
				if(month > 1) {
					day = Month.of(--month).length(Year.isLeap(year));
				} else {
					month = Month.DECEMBER.getValue();
					year --;
					day = Month.JANUARY.length(false);
				}
			}
			days.add(0, (year * 100 + month) * 100 + day);
		}
		return days;
	}

	public static Long getIntervalDays(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}

	public static List<LocalDate> getBetweenWeeks(LocalDate startDate, LocalDate endDate) {
		if(endDate.isBefore(startDate)){
			return new ArrayList<>(0);
		}
		List<LocalDate> result = new ArrayList<>();
		result.add(startDate);
		LocalDate currentDate  = startDate.plusDays(1);
		while (currentDate.isBefore(endDate)) {
			DayOfWeek weekDay = currentDate.getDayOfWeek();
			if (weekDay == DayOfWeek.MONDAY || weekDay == DayOfWeek.SUNDAY) {
				result.add(currentDate);
			}
			if (weekDay == DayOfWeek.MONDAY) {
				currentDate  = currentDate.plusDays(6);
			}
			currentDate  = currentDate.plusDays(1);
		}
		result.add(endDate);

		return result;
	}

	public static List<LocalDate> getBetweenWeekends(LocalDate startDate, LocalDate endDate) {
		if(endDate.isBefore(startDate)){
			return new ArrayList<>(0);
		}
		List<LocalDate> result = new ArrayList<>();
		LocalDate currentDate  = startDate;
		while (currentDate.isBefore(endDate)) {
			DayOfWeek weekDay = currentDate.getDayOfWeek();
			if (weekDay == DayOfWeek.SUNDAY) {
				result.add(currentDate);
			}
			currentDate  = currentDate.plusDays(1);
		}
		DayOfWeek endDay = endDate.getDayOfWeek();
		if (endDay == DayOfWeek.SUNDAY) {
			result.add(endDate);
		}
		return result;
	}

	public static List<EsDate> getEsBetween(LocalDate startDate, LocalDate endDate){
		List<EsDate> list = new ArrayList<>();
		long sum = endDate.toEpochDay()-startDate.toEpochDay();
		int num=0;
		int[] array={29,6,0};
		while(endDate.toEpochDay()-startDate.toEpochDay()>=0){
			for(int temp:array){
				if(endDate.toEpochDay()-startDate.toEpochDay()>=0){
					if(num+temp<=sum) {
						num = num + temp;
						startDate = startDate.plusDays(temp);
						list.add(new EsDate(getYYYYMMDD(startDate),temp,getYYYYMM(startDate)));
						startDate = startDate.plusDays(1);
						num=num+1;
						break;
					}
				}
			}

		}
		return list;
	}

	public static long toEpochMilli(LocalDate localDate){
		return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
	}

	public static Integer endOfMonth(Integer currentMonth) {
		LocalDate currentDay;
		if(currentMonth.toString().length() == 8){
			 currentDay = DateUtil.getLocalDateByESDate(currentMonth);
		}else if(currentMonth.toString().length() == 6){
			currentDay = LocalDate.parse(currentMonth +"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
		}else{
			return null;
		}

		LocalDate lastDay =  currentDay.with(TemporalAdjusters.lastDayOfMonth());

		int year = lastDay.getYear();
		int month = lastDay.getMonthValue();
		int day = lastDay.getDayOfMonth();
		return (year * 100 + month) * 100 + day;
	}

	public static LocalDate monthStartDay(LocalDate localDate){
		return LocalDate.of(localDate.getYear(),localDate.getMonthValue(),1);
	}

	public static LocalDate getYesterday() {
		return LocalDate.now().minusDays(1);
	}

	public static int getYesterdayEsDate() {
		LocalDate localDate =  LocalDate.now().minusDays(1);
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		return (year * 100 + month) * 100 + day;
	}

	/**
	 * localDate 转换为 esDate
	 * @param localDate
	 * @return
	 */
	public static Integer getEsDateByLocalDate(LocalDate localDate) {
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		return (year * 100 + month) * 100 + day;

	}

	public static int getWeekOfYear(LocalDate localDate) {
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,7);
		return localDate.get(weekFields.weekOfWeekBasedYear());
	}

	public static long getWeeksBetween(LocalDate start, LocalDate end) {
		return ChronoUnit.WEEKS.between(start,end);
	}

	public static LocalDate getMin(LocalDate date1, LocalDate date2) {
		return date1.isBefore(date2)?date1:date2;
	}

	public static class EsDate{
		private String dt;
		private int between;
		private String yyyymm;

		public String getDt() {
			return dt;
		}

		public void setDt(String dt) {
			this.dt = dt;
		}

		public int getBetween() {
			return between;
		}

		public void setBetween(int between) {
			this.between = between;
		}

		public String getYyyymm() {
			return yyyymm;
		}

		public void setYyyymm(String yyyymm) {
			this.yyyymm = yyyymm;
		}

		public EsDate(String dt, int between, String yyyymm) {
			this.dt = dt;
			this.between = between;
			this.yyyymm = yyyymm;
		}
	}

    public static LocalDate toLocalDate(long ts) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts));
        return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
    }


	public static LocalDate endOfWeek(LocalDate localDate){
		return localDate.plusDays((long)DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue());
	}


	public static boolean dateValidation(LocalDate start, LocalDate end, int threshold) {
		long gap = ChronoUnit.DAYS.between(start, end) + 1;
		LocalDate minStartDate = LocalDate.now().plusYears(-2);
		return gap > 0 && gap <= threshold && !start.isBefore(minStartDate) && !end.isAfter(LocalDate.now());
	}

	public static boolean weekDateValidation(LocalDate start, LocalDate end, int threshold) {
		if(!Objects.equals(start.getDayOfWeek(),DayOfWeek.MONDAY)){
			return false;
		}
		long gap = ChronoUnit.WEEKS.between(start, end) + 1;
		LocalDate minStartDate = LocalDate.now().plusYears(-2);
		return gap > 0 && gap <= threshold && !start.isBefore(minStartDate) && !end.isAfter(LocalDate.now());
	}

	public static boolean monthDateValidation(LocalDate start, LocalDate end, int threshold) {
		if(!Objects.equals(start.getDayOfMonth(),1)){
			return false;
		}
		long gap = ChronoUnit.MONTHS.between(start, end) + 1;
		LocalDate minStartDate = LocalDate.now().plusYears(-2);
		return gap > 0 && gap <= threshold && !start.isBefore(minStartDate) && !end.isAfter(LocalDate.now());
	}

	private static final String EMPTYSTR = "?";
	public static Long convertStringToLong(String dateStr) {
		if(StringUtils.isEmpty(dateStr) || Objects.equals(EMPTYSTR, dateStr)){
			return null;
		}
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime();
		} catch (ParseException e) {
			log.info("convertStringToLong error for:{}",dateStr,e);
			e.printStackTrace();
		}
		return null;
	}

	public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
		return Date.from( localDateTime.toInstant(ZoneOffset.ofHours(8)));
	}

	public static boolean isStartDate(LocalDate localDate){
		return Objects.equals(LocalDate.of(1990,1,1),localDate);
	}

	public static String localDateTimeToString(LocalDateTime localDateTime){
		return localDateTime.format(formatters4);
	}
	public static void main(String[] args) {
		System.out.println("args = " + UUID.randomUUID());
	}
}
