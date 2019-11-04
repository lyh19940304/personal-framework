package com.basic.common.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * 
 * @desc 集合工具类
 * @author Liuyh
 * @date 2018年9月4日 上午11:55:23
 */
public class CollectionTools {

	/**
	 * 
	 * @desc 判断集合是否为空
	 * @author Liuyh
	 * @date 2018年8月27日上午9:38:37
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<? extends Object> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 
	 * @desc 判断集合是否为空 
	 * @author Liuyh
	 * @date 2019年10月31日下午7:17:57
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 
	 * @desc 字符串以指定符号分隔转Integer集合
	 * @author Zhangfz
	 * @date 2018年12月14日 下午3:50:57
	 * @param ids    字符串
	 * @param symbol 符号
	 * @return
	 */
	public static List<Integer> transformation(String ids, String symbol) {
		Iterable<String> split = Splitter.on(symbol).trimResults().split(ids);
		List<Integer> list = new ArrayList<Integer>();
		split.forEach(e -> {
			list.add(Integer.valueOf(e));
		});
		return list;
	}

	/**
	 * 
	 * @desc 将集合转为以指定符号拼接的字符串
	 * @author Liuyh
	 * @date 2019年4月9日下午4:51:27
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<?> list, String separator) {
		return Joiner.on(separator).join(list);
	}

	public static List<String> string(String ids, String symbol) {
		Iterable<String> split = Splitter.on(symbol).trimResults().split(ids);
		List<String> list = new ArrayList<String>();
		split.forEach(e -> {
			list.add(e);
		});
		return list;
	}
}
