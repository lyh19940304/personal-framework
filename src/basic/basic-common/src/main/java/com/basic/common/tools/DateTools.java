package com.basic.common.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.basic.common.exception.CoreException;
import com.basic.common.res.SystemEnums;

/**
 * 
 * @desc 日期工具类 
 * @author Liuyh
 * @date 2019年10月11日下午2:59:43
 */
public class DateTools {

	/** 年月日时分秒 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/** 年月日 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * 
	 * @desc 字符串转换成日期类型
	 * @author Liuyh
	 * @date 2019年2月26日下午5:52:36
	 * @param date
	 * @return
	 */
	public static LocalDate toLocalDate(String date) {
		if (ObjectTools.isNotBlank(date)) {
			try {
				return LocalDate.parse(date, DATE_TIME_FORMATTER_YYYYMMDD);
			} catch (Exception e) {
				throw new CoreException(SystemEnums.DATE_DATA_FORMAT_FAIL);
			}
		}
		return null;
	}

	/**
	 * 
	 * @desc 字符串转换成时间类型
	 * @author Liuyh
	 * @date 2019年2月26日下午5:52:43
	 * @param dateTime
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(String dateTime) {
		if (ObjectTools.isNotBlank(dateTime)) {
			try {
				return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER_YYYYMMDDHHMMSS);
			} catch (Exception e) {
				throw new CoreException(SystemEnums.TIME_DATA_FORMAT_FAIL);
			}
		}
		return null;
	}
}