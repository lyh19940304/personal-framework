package com.basic.common.tools;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * 
 * @desc jdk8函数表达式工具类
 * @author Liuyh
 * @date 2018年8月20日 下午4:40:19
 */
public class FunctionTools {

	/**
	 * 
	 * @desc 获取集合中满足指定条件的元素集合
	 * @author Liuyh
	 * @date 2018年8月20日下午2:55:59
	 * @param data
	 * @param condition
	 * @return
	 */
	public static <T> List<T> filter(List<T> data, Predicate<T> condition) {
		return data.stream().filter((value) -> (condition.test(value))).collect(Collectors.toList());
	}

	/**
	 * 
	 * @desc 对Integer类型进行求和
	 * @author Liuyh
	 * @date 2018年8月20日下午3:28:43
	 * @param data
	 * @return
	 */
	public static Integer sumInteger(List<Integer> data) {
		return sumInteger(data, (num) -> true);
	}

	/**
	 * 
	 * @desc 对Double类型进行求和
	 * @author Liuyh
	 * @date 2018年8月20日下午3:28:58
	 * @param data
	 * @return
	 */
	public static Double sumDouble(List<Double> data) {
		return sumDouble(data, (num) -> true);
	}

	/**
	 * 
	 * @desc 对BigDecimal类型进行求和
	 * @author Liuyh
	 * @date 2018年8月20日下午3:29:07
	 * @param data
	 * @return
	 */
	public static BigDecimal sumBigDecimal(List<BigDecimal> data) {
		return data.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * 
	 * @desc 对Integer类型进行筛选及累加
	 * @author Liuyh
	 * @date 2018年8月20日下午3:37:21
	 * @param data
	 * @param condition
	 * @return
	 */
	public static Integer sumInteger(List<Integer> data, Predicate<Integer> condition) {
		return data.stream().filter(value -> condition.test(value)).reduce((sum, num) -> sum + num).get();
	}

	/**
	 * 
	 * @desc 对Double类型进行筛选及累加
	 * @author Liuyh
	 * @date 2018年8月20日下午3:43:57
	 * @param data
	 * @param condition
	 * @return
	 */
	public static Double sumDouble(List<Double> data, Predicate<Double> condition) {
		return data.stream().filter(value -> condition.test(value)).reduce((sum, num) -> sum + num).get();
	}

	/**
	 * 
	 * @desc 对BigDecimal类型进行筛选及累加
	 * @author Liuyh
	 * @date 2018年8月20日下午3:44:14
	 * @param data
	 * @param condition
	 * @return
	 */
	public static BigDecimal sumBigDecimal(List<BigDecimal> data, Predicate<BigDecimal> condition) {
		return data.stream().filter(value -> condition.test(value)).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * 
	 * @desc 对字符串进行拼接.默认以逗号分隔
	 * @author Liuyh
	 * @date 2018年8月20日下午3:52:17
	 * @param data
	 * @return
	 */
	public static String strConcat(List<String> data) {
		return strConcat(data, ObjectTools.COMMA);
	}

	/**
	 * 
	 * @desc 对字符串以指定字符串进行拼接
	 * @author Liuyh
	 * @date 2018年8月20日下午3:53:15
	 * @param data
	 * @param joiner
	 * @return
	 */
	public static String strConcat(List<String> data, String joiner) {
		return strConcat(data, joiner, (e) -> true);
	}

	/**
	 * 
	 * @desc 对字符串先筛选再以指定字符串进行拼接
	 * @author Liuyh
	 * @date 2018年8月20日下午4:00:03
	 * @param data
	 * @param joiner
	 * @param condition
	 * @return
	 */
	public static String strConcat(List<String> data, String joiner, Predicate<String> condition) {
		return Joiner.on(joiner).join(filter(data, condition));
	}

	/**
	 * 
	 * @desc 对数字进行拼接
	 * @author Liuyh
	 * @date 2018年8月20日下午4:12:45
	 * @param data
	 * @return
	 */
	public static String integerConcat(List<Integer> data) {
		return integerConcat(data, ObjectTools.COMMA);
	}

	/**
	 * 
	 * @desc 对数字以指定字符串进行拼接
	 * @author Liuyh
	 * @date 2018年8月20日下午4:13:40
	 * @param data
	 * @param joiner
	 * @return
	 */
	public static String integerConcat(List<Integer> data, String joiner) {
		return integerConcat(data, joiner, e -> true);
	}

	/**
	 * 
	 * @desc 对数字筛选后以指定字符串进行拼接
	 * @author Liuyh
	 * @date 2018年8月20日下午4:15:37
	 * @param data
	 * @param joiner    拼接符
	 * @param condition
	 * @return
	 */
	public static String integerConcat(List<Integer> data, String joiner, Predicate<Integer> condition) {
		List<Integer> result = filter(data, condition);
		List<String> strs = Lists.transform(result, Functions.toStringFunction());
		return strConcat(strs, joiner);
	}

	/**
	 * 
	 * @desc 对Integer集合进行聚合函数运算
	 * @author Liuyh
	 * @date 2018年8月20日下午5:03:31
	 * @param data
	 * @return
	 */
	public static IntSummaryStatistics calc(List<Integer> data) {
		return data.stream().mapToInt(x -> x).summaryStatistics();
	}

	/**
	 * 
	 * @desc 获得集合中所有元素的最大值
	 * @author Liuyh
	 * @date 2018年8月20日下午5:06:35
	 * @param data
	 * @return
	 */
	public static Integer calcMax(List<Integer> data) {
		return calc(data).getMax();
	}

	/**
	 * 
	 * @desc 获得集合中所有元素的最小值
	 * @author Liuyh
	 * @date 2018年8月20日下午5:09:19
	 * @param data
	 * @return
	 */
	public static Integer calcMin(List<Integer> data) {
		return calc(data).getMin();
	}

	/**
	 * 
	 * @desc 获得long类型的集合中所有元素的总和
	 * @author Liuyh
	 * @date 2018年8月20日下午5:09:30
	 * @param data
	 * @return
	 */
	public static long calcSum(List<Integer> data) {
		return calc(data).getSum();
	}

	/**
	 * 
	 * @desc 获得int类型的集合中所有元素的总和
	 * @author Liuyh
	 * @date 2018年8月20日下午5:11:32
	 * @param data
	 * @return
	 */
	public static Integer calcSumToInt(List<Integer> data) {
		return new Long(calcSum(data)).intValue();
	}

	/**
	 * 
	 * @desc 获得集合中所有元素的平均数
	 * @author Liuyh
	 * @date 2018年8月20日下午5:09:43
	 * @param data
	 * @return
	 */
	public static double calcAvg(List<Integer> data) {
		return calc(data).getAverage();
	}
}