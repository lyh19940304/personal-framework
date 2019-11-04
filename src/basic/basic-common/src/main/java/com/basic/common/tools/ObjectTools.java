package com.basic.common.tools;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.basic.common.data.Page;

/**
 * 
 * @desc 基础工具类
 * @author Liuyh
 * @date 2018年9月4日 上午11:55:38
 */
public class ObjectTools {
	private static final String UNDERFINED = "underfined";
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public static final String POINT = ".";
	public static final String COLON = ":";
	public static final String STRING_NULL="null";
	
	public static final String PATTERN_NUMBER="^[-\\+]?[\\d]*$";
	
	public static final List<String> LETTER=Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
	
	
	/** 返回对象是否为null */
	public static boolean isNull(Object value) {
		return value == null;
	}

	/** 返回对象是否不为null */
	public static boolean isNotNull(Object value) {
		return !isNull(value);
	}

	/**
	 * 
	 * @desc 返回字符串是否为empty
	 * @author Liuyh
	 * @date 2018年8月28日上午10:03:14
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return isNull(value) || value.length() == 0;
	}

	/**
	 * 
	 * @desc 返回字符串是否不为empty
	 * @author Liuyh
	 * @date 2018年8月28日上午10:03:32
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	/**
	 * 
	 * @desc 返回字符串是否为blank
	 * @author Liuyh
	 * @date 2018年8月28日上午10:03:53
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return isEmpty(value) || value.trim().length() == 0;
	}

	/**
	 * 
	 * @desc 返回字符串是否不为blank
	 * @author Liuyh
	 * @date 2018年8月28日上午10:04:25
	 * @param value
	 * @return
	 */
	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	/**
	 * 
	 * @desc 包含underfined(前端的未定义的值)
	 * @author Liuyh
	 * @date 2018年8月28日上午10:04:57
	 * @param value
	 * @return
	 */
	public static boolean isBlankIncludeUnderfined(String value) {
		return isBlank(value) || UNDERFINED.equals(value.trim());
	}

	/**
	 * 
	 * @desc 或的逻辑,有一个为null则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:05:12
	 * @param values
	 * @return
	 */
	public static boolean orNull(Object... values) {
		for (Object value : values) {
			if (isNull(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 或的逻辑,有一个不为null则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:05:40
	 * @param values
	 * @return
	 */
	public static boolean orNotNull(Object... values) {
		for (Object value : values) {
			if (isNotNull(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 或的逻辑,有一个为empty则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:06:03
	 * @param values
	 * @return
	 */
	public static boolean orEmpty(String... values) {
		for (String value : values) {
			if (isEmpty(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 或的逻辑,有一个不为empty则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:06:27
	 * @param values
	 * @return
	 */
	public static boolean orNotEmpty(String... values) {
		for (String value : values) {
			if (isNotEmpty(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 或的关系,有一个为blank则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:06:50
	 * @param values
	 * @return
	 */
	public static boolean orBlank(String... values) {
		for (String value : values) {
			if (isBlank(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 或的关系,有一个不为blank则返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:07:05
	 * @param values
	 * @return
	 */
	public static boolean orNotBlank(String... values) {
		for (String value : values) {
			if (isNotBlank(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 包含underfined(前端的未定义的值)
	 * @author Liuyh
	 * @date 2018年8月28日上午10:07:17
	 * @param values
	 * @return
	 */
	public static boolean orBlankIncludeUnderfined(String... values) {
		for (String value : values) {
			if (isBlankIncludeUnderfined(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 且的关系,所有值都为null返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:07:33
	 * @param values
	 * @return
	 */
	public static boolean andNull(Object... values) {
		for (Object value : values) {
			if (isNotNull(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @desc 且的关系,所有值都不为null返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:08:09
	 * @param values
	 * @return
	 */
	public static boolean andNotNull(Object... values) {
		return !andNull(values);
	}

	/**
	 * 
	 * @desc 且的关系,所有值都为empty返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:08:26
	 * @param values
	 * @return
	 */
	public static boolean andEmpty(String... values) {
		for (String value : values) {
			if (isNotEmpty(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @desc 且的关系,所有值都不为empty返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:08:42
	 * @param values
	 * @return
	 */
	public static boolean andNotEmpty(String... values) {
		return !andEmpty(values);
	}

	/**
	 * 
	 * @desc 且的关系,所有值都为blank返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:09:14
	 * @param values
	 * @return
	 */
	public static boolean andBlank(String... values) {
		for (String value : values) {
			if (!isBlank(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @desc 包含underfined(前端的未定义的值)
	 * @author Liuyh
	 * @date 2018年8月28日上午10:09:32
	 * @param values
	 * @return
	 */
	public static boolean andBlankIncludeUnderfined(String... values) {
		for (String value : values) {
			if (!isBlankIncludeUnderfined(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @desc 且的关系,所有值都不为blank返回true
	 * @author Liuyh
	 * @date 2018年8月28日上午10:09:49
	 * @param values
	 * @return
	 */
	public static boolean andNotBlank(String... values) {
		return !andBlank(values);
	}

	/**
	 * 
	 * @desc 判断两个字符串是否相等,不忽略大小写
	 * @author Liuyh
	 * @date 2018年8月28日上午10:10:04
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean equals(Object one, Object another) {
		return equals(one, another, false);
	}

	/**
	 * 
	 * @desc 判断两个字符串是否相等.忽略大小写
	 * @author Liuyh
	 * @date 2018年8月28日上午10:10:22
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean equalsIgnoreCase(Object one, Object another) {
		return equals(one, another, true);
	}

	/**
	 * 
	 * @desc 判断两个对象是否相等
	 * @author Liuyh
	 * @date 2018年8月16日下午12:13:08
	 * @param one
	 * @param another
	 * @param flag
	 * @return
	 */
	private static boolean equals(Object one, Object another, boolean flag) {
		if (one == null) {
			return another == null;
		}
		if (another == null) {
			return false;
		}
		if (flag) {
			return one.equals(another);
		} else {
			return one.toString().equalsIgnoreCase(another.toString());
		}
	}

	/**
	 * 
	 * @desc 对一个字符串指定方向添加指定字符到指定长度
	 * @author Liuyh
	 * @date 2018年8月9日上午11:40:19
	 * @param old       旧字符串
	 * @param fillChar  要填充的字符
	 * @param len       要添加多长
	 * @param direction 填充方向 0:首 其他值:末
	 * @return
	 */
	public static String fillChar(String old, char fillChar, int len, int direction) {
		if (isNull(old) || old.length() >= len) {
			return old;
		}
		int charLen = len - old.length();
		char[] chars = new char[charLen];
		for (int i = 0; i < charLen; i++) {
			chars[i] = fillChar;
		}

		StringBuilder sb = new StringBuilder();
		if (direction == 0) {
			sb.append(new String(chars)).append(old);
		} else {
			sb.append(old).append(new String(chars));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @desc 对一个字符串指定方向添加指定字符串直到大于等于指定长度
	 * @author Liuyh
	 * @date 2018年8月9日上午11:55:51
	 * @param old       旧字符串
	 * @param fillStr   要填充的字符串
	 * @param len       要大于等于的长度
	 * @param direction 填充方向 0:首 其他值:末
	 * @return
	 */
	public static String fillStr(String old, String fillStr, int len, int direction) {
		if (isNull(old) || old.length() >= len) {
			return old;
		}
		int strLen = len - old.length();
		int countStr = strLen % fillStr.length() == 0 ? strLen / fillStr.length() : strLen / fillStr.length() + 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < countStr; i++) {
			sb.append(fillStr);
		}

		StringBuilder result = new StringBuilder();
		if (direction == 0) {
			result.append(old).append(sb.toString());
		} else {
			result.append(sb.toString()).append(old);
		}
		return result.toString();
	}

	/**
	 * 
	 * @desc 获取文件的拓展名
	 * @author Liuyh
	 * @date 2018年8月14日下午2:55:05
	 * @param fileName
	 * @return
	 */
	public static String getExtendsName(String fileName) {
		if (!isBlank(fileName)) {
			int index = fileName.lastIndexOf(POINT);
			if (index > -1 && index < (fileName.length() - 1)) {
				return fileName.substring(index + 1);
			}
		}
		return fileName;
	}

	/**
	 * 
	 * @desc 获取不带后缀的文件名
	 * @author Liuyh
	 * @date 2018年8月14日下午2:55:15
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if (!isBlank(fileName)) {
			int index = fileName.lastIndexOf(POINT);
			if (index > 0) {
				return fileName.substring(0, index);
			}
		}
		return fileName;
	}

	/**
	 * 
	 * @desc 判断一个字符串是否是数值
	 * @author Taoym
	 * @date 2019年1月8日下午3:32:37
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(PATTERN_NUMBER);
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @desc 检查分页信息
	 * @author Liuyh
	 * @date 2019年3月11日下午2:35:09
	 * @param page
	 * @return
	 */
	public static boolean checkPage(Page page) {
		if(page==null) {
			return false;
		}
		if(page.getPageNumber()==null||page.getPageSize()==null) {
			return false;
		}
		return true;
	}

}