package com.liuyh.generator.domain;

import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @desc 表的元数据 
 * @author Liuyh
 * @date 2019年10月11日下午3:02:58
 */
@Setter
@Getter
public class Table {
	/** sql中表名 */
	private String sqlTableName;
	/** 包名 */
	private String packageName;
	/** 表说明 */
	private String common;
	/** 表名称 */
	private String tableName;
	/** 类名 */
	private String className;
	/** 表字段(普通字段) */
	private List<Column> columns;
	/** 表字段(复合主键字段) */
	private List<Column> primaryKeyColumns;
	/** 主键类型 */
	private String primaryKeysType;
	/** 主键类包名 */
	private String primaryKeysPackageName;
	/** 需要导入的包 */
	private Set<String> importPackages;
}
