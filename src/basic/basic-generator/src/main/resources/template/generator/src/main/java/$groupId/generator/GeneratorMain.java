package ${project.groupId}.generator;

import com.liuyh.generator.domain.DBGenerator;
import com.liuyh.generator.domain.NameConvertUtils;

public class GeneratorMain {
	public static void main(String[] args) {
		DBGenerator generator = new DBGenerator();
		/** 数据库配置,请修改正确的用户名密码及链接 */
		DBGenerator.DBSetting dbSetting = new DBGenerator.DBSetting();
		dbSetting.setDbUrl("jdbc:mysql://localhost:3306/${project.artifactId}?characterEncoding=UTF8&useSSL=false");
		dbSetting.setDbUserName("root");
		dbSetting.setDbPassWord("123456");
		dbSetting.setTableFilter(e -> true);
		generator.setDbSetting(dbSetting);

		/** 默认类名首字母大写驼峰 属性名首字母小写驼峰 (均以下划线分割) */
		DBGenerator.JavaModelSetting modelSetting = new DBGenerator.JavaModelSetting();
		modelSetting.setTableJavaConvert(e -> NameConvertUtils.underlineToHumpFirstUpper(e.substring(2)));
		modelSetting.setTableSqlConvert(e -> e);
		modelSetting.setColumnConvert(e -> NameConvertUtils.underlineToHump(e));
		generator.setJavaModelSetting(modelSetting);
		/** 设置生成实体类包名及mybatis代理包名 */
		generator.setJavaClientPackageName("${project.groupId}.mapper");
		generator.setJavaBeanPackageName("${project.groupId}.model");

		/** 设置生成代码路径 */
		DBGenerator.FileSetting fileSetting = new DBGenerator.FileSetting();
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.lastIndexOf("\\") + 1);
		fileSetting.setBaseDir(path);
		String dir = "${project.groupId}";
		dir = dir.replace(".", "\\");
		fileSetting
				.setJavaModelChild(fileSetting.getBaseDir() + "${project.artifactId}-model\\src\\main\\java\\" + dir + "\\model\\");
		fileSetting.setJavaModelSuper(fileSetting.getJavaModelChild());
		fileSetting.setJavaClient(
				fileSetting.getBaseDir() + "${project.artifactId}-data-access\\src\\main\\java\\" + dir + "\\mapper\\");
		fileSetting.setSqlMap(fileSetting.getBaseDir() + "${project.artifactId}-data-access\\src\\main\\resources\\sqlmap\\");
		generator.setFileSetting(fileSetting);

		try {
			generator.generator();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}