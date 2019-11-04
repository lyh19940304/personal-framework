package com.basic.common.res;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @desc 统一返回报文定义 
 * @author Liuyh
 * @date 2019年10月11日下午2:58:24
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "标准响应码")
	private String code;
	@ApiModelProperty(example = "标准响应信息")
	private String msg;
	private T data;
	@ApiModelProperty(example = "记录数(业务需要时返回,默认返回0)")
	private int total;
	@ApiModelProperty(example = "自定义消息体")
	private String desc;
	@ApiModelProperty(example = "当前时间戳")
	private LocalDateTime timestamp;
	@ApiModelProperty(example = "用户登录令牌")
	private String token;

	public boolean isSuccess() {
		return SystemEnums.SUCCESS.getCode().equals(code);
	}

	/** begin of 成功响应 */
	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(T data) {
		return success(data, 0);
	}

	public static <T> Result<T> success(T data, int total) {
		return success(data, total, null);
	}

	public static <T> Result<T> success(T data, String desc) {
		return success(data, 0, desc);
	}

	public static <T> Result<T> success(T data, int total, String desc) {
		return createResponse(SystemEnums.SUCCESS, data, total, desc);
	}

	/** end of 成功响应 */

	/** begin of 业务失败响应 */
	public static <T> Result<T> business(BasicEnums resultEnums) {
		return business(resultEnums, null);
	}

	public static <T> Result<T> business(BasicEnums resultEnums, String desc) {
		return createResponse(resultEnums, null, 0, desc);
	}
	public static <T> Result<T> business(String code,String msg){
		return createResponse(code,msg,null,0,null);
	}
	public static <T> Result<T> business(String code,String msg,String desc){
		return createResponse(code,msg,null,0,desc);
	}

	/** end of 业务失败响应 */

	/** begin of 运行时异常响应 */
	public static <T> Result<T> exception() {
		return exception(null);
	}

	public static <T> Result<T> exception(String desc) {
		return createResponse(SystemEnums.EXCEPTION, null, 0, desc);
	}

	/** end of 运行时异常响应 */

	private static <T> Result<T> createResponse(BasicEnums resultEnums, T data, int total, String desc) {
		return createResponse(resultEnums.getCode(), resultEnums.getMsg(), data, total, desc);
	}

	/**
	 * 返回一个带有所有参数的响应对象
	 * 
	 * @author Liuyh
	 * @date 2018年7月17日下午2:03:14
	 * @param code  标准响应码
	 * @param msg   标准响应信息
	 * @param data  响应数据
	 * @param total 记录数
	 * @param desc  自定义响应消息体
	 * @return
	 */
	private static <T> Result<T> createResponse(String code, String msg, T data, int total, String desc) {
		return new Result<>(code, msg, data, total, desc, LocalDateTime.now(), null);
	}
}
