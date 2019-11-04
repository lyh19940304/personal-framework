package com.liuyh.generator.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @desc 代码生成器 
 * @author Liuyh
 * @date 2019年10月11日下午3:01:26
 */
@Getter
@Setter
public class DBGenerator {

	/** 数据库配置 */
	private DBSetting dbSetting;
	/** javaModel配置 */
	private JavaModelSetting javaModelSetting;
	/** 模板配置 */
	private TemplateSetting templateSetting;
	/** javaClient包名称 */
	private String javaClientPackageName;
	/** javabean包名称 */
	private String javaBeanPackageName;
	/** 生成文件目录配置 */
	private FileSetting fileSetting;
	/** 填充数据表信息 */
	private Table t;

	public void generator() throws Exception {
		checkDBSetting();
		checkNameConvert();
		checkPackageName();
		checkFile();
		initTemplate();

		Configuration conf = getConf();
		DBUtils.init(dbSetting.getDbUrl(), dbSetting.getDbUserName(), dbSetting.getDbPassWord());
		List<Table> tables = DBUtils.getTableInfo(dbSetting.getTableFilter(), javaModelSetting.getTableJavaConvert(),
				javaModelSetting.getTableSqlConvert(), javaModelSetting.getColumnConvert(), javaBeanPackageName);
		
		/** 获取模板 */
		Template javaModelChildTemplate = conf.getTemplate(templateSetting.getJavaModelChildTemplate());
		Template javaModelSuperTemplate = conf.getTemplate(templateSetting.getJavaModelSuperTemplate());
		Template sqlMapTemplate = conf.getTemplate(templateSetting.getSqlMapTemplate());
		Template javaClientTemplate = conf.getTemplate(templateSetting.getJavaClientTemplate());
		Template sqlMapExtendTemplate = conf.getTemplate(templateSetting.getSqlMapExtendTemplate());
		
		File javaModelChildPath = initDir(fileSetting.getJavaModelChild());
		File javaModelSuperPath = initDir(fileSetting.getJavaModelSuper());
		File javaClientPath = initDir(fileSetting.getJavaClient());
		File sqlMapPath = initDir(fileSetting.getSqlMap());
		
		tables.forEach(t -> {
			this.setT(t);
			if (t.getTableName() == null) {
				try (Writer javaModelSuperWriter = new OutputStreamWriter(
						new FileOutputStream(new File(javaModelSuperPath, t.getClassName() + ".java")))) {
					javaModelSuperTemplate.process(t, javaModelSuperWriter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				File javaModelChildFile = new File(javaModelChildPath, t.getClassName() + ".java");
				File javaClientFile = new File(javaClientPath, t.getClassName() + "Mapper.java");
				File sqlMapFile = new File(sqlMapPath, t.getClassName() + "Mapper.xml");
				File sqlMapExtendFile = new File(sqlMapPath, t.getClassName() + "MapperExtend.xml");

				if (!javaClientFile.exists()) {
					try {
						Writer javaClientWriter = new OutputStreamWriter(new FileOutputStream(javaClientFile));
						javaClientTemplate.process(this, javaClientWriter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (!sqlMapExtendFile.exists()) {
					try {
						Writer sqlMapExtendWriter = new OutputStreamWriter(new FileOutputStream(sqlMapExtendFile));
						sqlMapExtendTemplate.process(this, sqlMapExtendWriter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					Writer javaModelChildWriter = new OutputStreamWriter(new FileOutputStream(javaModelChildFile));
					javaModelChildTemplate.process(t, javaModelChildWriter);

					Writer sqlMapWriter = new OutputStreamWriter(new FileOutputStream(sqlMapFile));
					sqlMapTemplate.process(this, sqlMapWriter);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	private Configuration getConf() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(templateSetting.getTemplateDir()));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return cfg;
	}

	private void initTemplate() {
		templateSetting = new TemplateSetting();
		templateSetting.setTemplateDir(DBGenerator.class.getClassLoader().getResource("template").getPath());
		templateSetting.setJavaModelChildTemplate("child.ftl");
		templateSetting.setJavaModelSuperTemplate("super.ftl");
		templateSetting.setSqlMapTemplate("mapper.ftl");
		templateSetting.setJavaClientTemplate("javaClient.ftl");
		templateSetting.setSqlMapExtendTemplate("mapperExtend.ftl");
	}

	private void checkFile() {
		if (fileSetting == null || StringUtils.isNullOrEmpty(fileSetting.getJavaClient())
				|| StringUtils.isNullOrEmpty(fileSetting.getJavaModelChild())
				|| StringUtils.isNullOrEmpty(fileSetting.getJavaModelSuper())
				|| StringUtils.isNullOrEmpty(fileSetting.getSqlMap())) {
			throw new IllegalAccessError("代码生成路径必填");
		}
	}

	private void checkPackageName() {
		if (StringUtils.isNullOrEmpty(javaClientPackageName) || StringUtils.isNullOrEmpty(javaBeanPackageName)) {
			throw new IllegalAccessError("生成类的包名必填！");
		}
	}

	private void checkNameConvert() {
		if (javaModelSetting == null || javaModelSetting.getTableJavaConvert() == null
				|| javaModelSetting.getTableSqlConvert() == null || javaModelSetting.getColumnConvert() == null) {
			throw new IllegalAccessError("数据库表跟列字段转换为类跟属性的规则必填！");
		}
	}

	private void checkDBSetting() {
		if (dbSetting == null || StringUtils.isNullOrEmpty(dbSetting.getDbUrl())
				|| StringUtils.isNullOrEmpty(dbSetting.getDbUserName())
				|| StringUtils.isNullOrEmpty(dbSetting.getDbPassWord())) {
			throw new IllegalAccessError("数据库信息必填！");
		}
		/** 如果没有指定生成哪些表 则全部生成 */
		if (dbSetting.getTableFilter() == null) {
			dbSetting.setTableFilter(e -> true);
		}
	}
	
	private static File initDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	@Getter
	@Setter
	public static class DBSetting {
		/** 数据库链接 */
		private String dbUrl;
		/** 数据库用户名 */
		private String dbUserName;
		/** 数据库密码 */
		private String dbPassWord;
		/** 设置生成哪些表 */
		private TableFilter tableFilter;
	}

	@Getter
	@Setter
	public static class JavaModelSetting {
		/** 表名转类名实现 */
		private NameConvert tableJavaConvert;
		/** 表名转sql中的表名实现 */
		private NameConvert tableSqlConvert;
		/** 列名转属性名实现 */
		private NameConvert columnConvert;
		/** 模块名称 */
		private String moduleName;
	}

	@Getter
	@Setter
	public static class FileSetting {
		/** 生成文件根路径(不要也罢,这个无所谓,只要下面3个值OK就好) */
		private String baseDir;
		/** javaBean子类生成目录 */
		private String javaModelChild;
		/** javaBean父类生成目录 */
		private String javaModelSuper;
		/** javaClient生成目录 */
		private String javaClient;
		/** sqlMap生成目录 */
		private String sqlMap;
	}

	@Getter
	@Setter
	public static class TemplateSetting {
		/** 模板目录 */
		private String templateDir;
		/** javabean子模板文件 */
		private String javaModelChildTemplate;
		/** javabean父模板文件 */
		private String javaModelSuperTemplate;
		/** javaClient模板文件 */
		private String javaClientTemplate;
		/** sqlMap模板文件 */
		private String sqlMapTemplate;
		/** sqlMap拓展模板文件 */
		private String sqlMapExtendTemplate;
	}
}
