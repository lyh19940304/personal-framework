package com.basic.common.data;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @desc 分页信息对象 
 * @author Liuyh
 * @date 2019年10月11日下午2:58:05
 */
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable{

	public static final int PAGE_NUMBER = 0;
	public static final int PAGE_SIZE = 10;
	public static final int MIN_PAGE_SIZE = 1;
	
	public static final int PAGE_SIZE_100=100;
	public static final int PAGE_SIZE_1000=1000;
	public static final int PAGE_SIZE_10000=10000;

	@ApiParam(value = "从第几条开始", name = "页码")
	private Integer pageNumber;
	@ApiParam(value = "从第几页开始", name = "页码大小")
	private Integer pageSize;

	public static Page defaultPage() {
		return new Page(PAGE_NUMBER, PAGE_SIZE);
	}
}
