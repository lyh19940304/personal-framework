package com.liuyh.generator.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @desc 列的元数据 
 * @author Liuyh
 * @date 2019年10月11日下午3:00:53
 */
@Getter
@Setter
public class Column {
	/** 所在表 */
	private String tableName;
	/** 字段说明 */
	private String common;
	/** 列名 */
	private String columnName;
	/** java属性名 */
	private String propertyName;
	/** 数据库类型 */
	private String jdbcType;
	/** java类型 全限定名 */
	private String javaType;
	/** java类型 类名 */
	private String simpleJavaType;
	/** java类型是否需要导包 即 是否不在java.lang包下 */
	private String needImportPackage;
	/** 数据长度 */
	private int length;
	/** 是否可以为空 */
	private String isNull;
	/** 是否是主键 */
	private String isPrimaryKey;
	/** 是否自增长 */
	private String isAutoincrement;
}
