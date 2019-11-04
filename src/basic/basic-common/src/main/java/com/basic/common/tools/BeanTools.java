package com.basic.common.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

/**
 * 
 * @desc 对象转换工具类 
 * @author Liuyh
 * @date 2019年10月11日下午2:59:11
 */
public class BeanTools {
	/**
	 * 
	 * @desc 对象转Map
	 * @author Liuyh
	 * @date 2019年2月27日上午9:28:43
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
	    Map<String, Object> beanMap = new HashMap<>(bean.getClass().getDeclaredFields().length);
	    if (bean != null) {
	        BeanMap tempMap = BeanMap.create(bean);
	        for (Object key : tempMap.keySet()) {
	            String putKey = String.valueOf(key);
	            Object putValue = tempMap.get(key);
	            beanMap.put(putKey, putValue);
	        }
	    }
	    return beanMap;
	}
	
	/**
	 * 
	 * @desc List<对象>转List<Map>
	 * @author Liuyh
	 * @date 2019年2月27日上午9:28:59
	 * @param beanList
	 * @return
	 */
	public static <T> List<Map<String, Object>> beanToMapBatch(List<T> beanList) {
	    List<Map<String, Object>> beanMapList = new ArrayList<>(beanList.size());
	    if (beanList != null && beanList.size() > 0) {
	        for (T bean : beanList) {
	            Map<String, Object> beanMap = beanToMap(bean);
	            beanMapList.add(beanMap);
	        }
	    }
	    return beanMapList;
	}
}
