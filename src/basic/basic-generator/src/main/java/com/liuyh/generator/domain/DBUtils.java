package com.liuyh.generator.domain;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * @desc 数据库工具类 
 * @author Liuyh
 * @date 2019年10月11日下午3:02:08
 */
public class DBUtils {
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	private static final String BOOLEAN_STRING_TRUE = "true";
	private static final String BOOLEAN_STRING_FALSE = "false";

	/** 表名 */
	public static final String TABLE_NAME = "TABLE_NAME";
	/** 列名 */
	public static final String COLUMN_NAME = "COLUMN_NAME";
	/** 备注 */
	public static final String REMARKS = "REMARKS";
	/** 数据类型 */
	public static final String DATA_TYPE = "DATA_TYPE";
	/** 列长度 */
	public static final String COLUMN_SIZE = "COLUMN_SIZE";
	/** 是否可以为NULL */
	public static final String NULLABLE = "NULLABLE";
	/** 是否自增长 */
	public static final String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";

	public static void init(String url, String username, String password) {
		URL = url;
		USERNAME = username;
		PASSWORD = password;
	}

	public static Connection getConn() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static List<Table> getTableInfo(TableFilter tableFilter, NameConvert tableJavaNameConvert,
			NameConvert tableSqlNameConvert, NameConvert columnNameConvert, String packageName) {
		List<Table> tables = getTable(tableFilter, tableJavaNameConvert, tableSqlNameConvert, packageName);
		List<Column> columns = getColumn(columnNameConvert);
		Map<String, List<Column>> columnsMap = columns.stream().collect(Collectors.groupingBy(Column::getTableName));
		/** 保存所有的主键类 */
		List<Table> primaryKeysClasses = new ArrayList<>();
		tables.forEach(t -> {
			
			/** 对集合中的列设置是否主键标识 */
			List<Column> allColumns = columnsMap.get(t.getTableName());
			List<Column> tablePrimaryKeys = t.getPrimaryKeyColumns();
			Map<String, Column> tablePrimaryKeysMap = tablePrimaryKeys.stream()
					.collect(Collectors.toMap(Column::getColumnName, column -> column));
			allColumns.forEach(c->{
				if(tablePrimaryKeysMap.get(c.getColumnName())!=null) {
					c.setIsPrimaryKey(BOOLEAN_STRING_TRUE);
				}else {
					c.setIsPrimaryKey(BOOLEAN_STRING_FALSE);
				}
			});
			
			/** 将多个主键移除普通列 */
			List<Column> pkColumns = allColumns.stream().filter(c -> BOOLEAN_STRING_TRUE.equals(c.getIsPrimaryKey()))
					.collect(Collectors.toList());
			Map<String, Column> removePKColumns = pkColumns.stream()
					.collect(Collectors.toMap(Column::getColumnName, column -> column));
			allColumns = allColumns.stream().filter(c -> removePKColumns.get(c.getColumnName()) == null)
					.collect(Collectors.toList());
			
			/** 针对表主键个数确定是否需要生成主键类 */
			if(pkColumns.size()>1) {
				Table primaryKeysClass = new Table();
				primaryKeysClass.setColumns(pkColumns);
				primaryKeysClass.setPackageName(t.getPackageName());
				primaryKeysClass.setClassName(t.getClassName() + "Keys");
				primaryKeysClasses.add(primaryKeysClass);
				
				t.setPrimaryKeysPackageName(primaryKeysClass.getPackageName());
				t.setPrimaryKeysType(primaryKeysClass.getClassName());
			}else {
				t.setPrimaryKeysType(pkColumns.get(0).getSimpleJavaType());
			}
			
			/** 设置整理好的普通列跟主键列 */
			t.setPrimaryKeyColumns(pkColumns);
			t.setColumns(allColumns);
			
			/** 设置普通列需要导包的字段(主键类与实体类在同一目录所以不需要导包) */
			t.setImportPackages(
					t.getColumns().stream().filter(c -> c.getNeedImportPackage().equals(BOOLEAN_STRING_TRUE))
							.map(Column::getJavaType).collect(Collectors.toSet()));
			
		});
		tables.addAll(primaryKeysClasses);
		return tables;
	}

	private static List<Table> getTable(TableFilter tableFilter, NameConvert tableJavaNameConvert,
			NameConvert tableSqlNameConvert, String packageName) {
		List<Table> tables = new LinkedList<Table>();
		Connection conn = getConn();
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet tablesResultSet = metaData.getTables(conn.getCatalog(), "%", "%", new String[] { "TABLE" });
			Table table = null;
			Column primaryKeysColumn = null;
			List<Column> primaryKeysColumns = null;
			while (tablesResultSet.next()) {
				/** 表筛选 */
				String tableName = tablesResultSet.getString(TABLE_NAME);
				if (!tableFilter.filter(tableName)) {
					continue;
				}

				table = new Table();
				primaryKeysColumns = new ArrayList<>();

				table.setTableName(tableName);
				table.setPackageName(packageName);
				table.setCommon(tablesResultSet.getString(REMARKS));
				table.setClassName(tableJavaNameConvert.convert(tableName));
				table.setSqlTableName(tableSqlNameConvert.convert(tableName));

				/** 设置主键信息 */
				ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(conn.getCatalog(), "%", table.getTableName());
				while (primaryKeysResultSet.next()) {
					primaryKeysColumn = new Column();
					primaryKeysColumn.setColumnName(primaryKeysResultSet.getString(COLUMN_NAME));
					primaryKeysColumns.add(primaryKeysColumn);
				}
				table.setPrimaryKeyColumns(primaryKeysColumns);
				tables.add(table);
			}
			tables = tables.stream().distinct().collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tables;
	}

	private static List<Column> getColumn(NameConvert columnNameConvert) {
		List<Column> columns = new ArrayList<>();
		Connection conn = getConn();
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet columnsResultSet = metaData.getColumns(conn.getCatalog(), "%", "%", "%");
			Column column = null;
			Map<Integer, String> intTypeMapper = intTypeMapper();
			Map<String, String> jdbcJavaTypeMapper = jdbcJavaTypeMapper();

			while (columnsResultSet.next()) {
				column = new Column();
				column.setTableName(columnsResultSet.getString(TABLE_NAME));
				column.setColumnName(columnsResultSet.getString(COLUMN_NAME));
				column.setCommon(columnsResultSet.getString(REMARKS));
				/** jdbcType从数字映射到字符串类型 */
				column.setJdbcType(intTypeMapper.get(columnsResultSet.getInt(DATA_TYPE)));
				/** javaType从数据库类型映射到java类型 */
				column.setJavaType(jdbcJavaTypeMapper.get(column.getJdbcType()));
				column.setNeedImportPackage(BOOLEAN_STRING_TRUE);
				if (column.getJavaType().indexOf(".") < 0 || column.getJavaType().startsWith("java.lang.")) {
					column.setNeedImportPackage(BOOLEAN_STRING_FALSE);
				}
				column.setLength(columnsResultSet.getInt(COLUMN_SIZE));
				column.setIsNull(String.valueOf(columnsResultSet.getInt(NULLABLE) > 0));
				column.setSimpleJavaType(column.getJavaType().substring(column.getJavaType().lastIndexOf(".") + 1));
				/** 属性名转换 */
				column.setPropertyName(columnNameConvert.convert(column.getColumnName()));
				column.setIsAutoincrement(columnsResultSet.getString(IS_AUTOINCREMENT));
				columns.add(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columns;
	}

	public static Map<Integer, String> intTypeMapper() {
		Map<Integer, String> map = new HashMap<>();
		map.put(-7, "BIT");
		map.put(-6, "TINYINT");
		map.put(5, "SMALLINT");
		map.put(4, "INTEGER");
		map.put(-5, "BIGINT");
		map.put(6, "FLOAT");
		map.put(7, "REAL");
		map.put(8, "DOUBLE");
		map.put(2, "NUMERIC");
		map.put(3, "DECIMAL");
		map.put(1, "CHAR");
		map.put(12, "VARCHAR");
		map.put(-1, "LONGVARCHAR");
		map.put(91, "DATE");
		map.put(92, "TIME");
		map.put(93, "TIMESTAMP");
		map.put(-2, "BINARY");
		map.put(-3, "VARBINARY");
		map.put(-4, "LONGVARBINARY");
		map.put(0, "NULL");
		map.put(1111, "OTHER");
		map.put(2000, "JAVA_OBJECT");
		map.put(2001, "DISTINCT");
		map.put(2002, "STRUCT");
		map.put(2003, "ARRAY");
		map.put(2004, "BLOB");
		map.put(2005, "CLOB");
		map.put(2006, "REF");
		map.put(70, "DATALINK");
		map.put(16, "BOOLEAN");
		map.put(-8, "ROWID");
		map.put(-15, "NCHAR");
		map.put(-9, "NVARCHAR");
		map.put(-16, "LONGNVARCHAR");
		map.put(2011, "NCLOB");
		map.put(2009, "SQLXML");
		map.put(2012, "REF_CURSOR");
		map.put(2013, "TIME_WITH_TIMEZONE");
		map.put(2014, "TIMESTAMP_WITH_TIMEZONE");
		return map;
	}

	public static Map<String, String> jdbcJavaTypeMapper() {
		Map<String, String> map = new HashMap<>();
		map.put("CHAR", "String");
		map.put("VARCHAR", "String");
		map.put("LONGVARCHAR", "String");
		map.put("NUMERIC", "java.math.BigDecimal");
		map.put("DECIMAL", "java.math.BigDecimal");
		map.put("BIT", "boolean");
		map.put("BOOLEAN", "boolean");
		map.put("TINYINT", "byte");
		map.put("SMALLINT", "short");
		map.put("INTEGER", "Integer");
		map.put("INT", "Integer");
		map.put("BIGINT", "long");
		map.put("REAL", "float");
		map.put("FLOAT", "double");
		map.put("DOUBLE", "double");
		map.put("BINARY", "byte[]");
		map.put("VARBINARY", "byte[]");
		map.put("LONGVARBINARY", "byte[]");
		map.put("DATE", "java.time.LocalDate");
		map.put("TIME", "java.time.LocalTime");
		map.put("TIMESTAMP", "java.time.LocalDateTime");
		map.put("CLOB", "com.mysql.jdbc.Clob");
		map.put("BLOB", "com.mysql.jdbc.Blob");
		map.put("ARRAY", "Array");
		map.put("STRUCT", "Struct");
		map.put("REF", "Ref");
		map.put("DATALINK", "java.net.URL");
		return map;
	}
}