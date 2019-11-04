package com.liuyh.generator.framework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;

/**
 * 
 * @desc 生成框架代码 
 * @author Liuyh
 * @date 2019年10月11日下午3:04:08
 */
@Getter
public class FrameworkGenerator {

	/** 应用运行的根路径,用于查找freemark模板 */
	private String projectDir;
	private Configuration conf;
	/** 用于保存项目基本信息 */
	private Param project;

	/** 要替换的目录 */
	private String replaceDir;
	private String templateModuleDir;
	
	/** 生成代码根路径 */
	private String baseDir;

	private static final String SEPARATOR = "/";
	private static final String POINT = ".";
	private static final String TEMPLATE = "template";
	private static final String POM_FILE = "pom.xml";
	private static final String DIR_GROUPID = "$groupId";
	private static final String CLASSNAME_ARTIFACTID="$classNameArtifactId";

	public void generator() throws Exception {
		projectDir = new File("").getCanonicalPath();
		project = new Param();
		Scanner input = new Scanner(System.in);

		/** 初始化项目基本信息 */
		System.out.println("请输入groupId:");
		project.setGroupId(input.next());
		System.out.println("请输入生成目录");
		String generatorDir = input.next();
		input.close();
		project.setArtifactId(project.getGroupId().substring(project.getGroupId().lastIndexOf(".") + 1));
		project.setClassNameArtifactId(
				project.getArtifactId().substring(0, 1).toUpperCase() + project.getArtifactId().substring(1));
		project.setModules(Arrays.asList(Module.values()));
		replaceDir = project.getGroupId().replace(POINT, SEPARATOR);

		/** 设置代码生成目录 */
		

		/** 创建文件夹 */
		File dirFile = new File(generatorDir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		initConf();
		createParent(generatorDir, conf);
		createChildren(generatorDir, conf);
		
	}

	private void createChildren(String generatorDir, Configuration conf2) {
		project.getModules().forEach(module -> {
			/** 1.初始化文件 */
			String dir = new StringBuilder(baseDir).append(project.getArtifactId()).append("-").append(module.getCode())
					.append(SEPARATOR).toString();

			File pomFile = getPomFile(dir);
			/** 2.根据模块的code取得模板名称并渲染 */
			String templateName = new StringBuilder().append(module.getCode()).append(SEPARATOR).append(POM_FILE)
					.toString();
			try {
				Template template = conf.getTemplate(get(templateName));
				template.process(this, new OutputStreamWriter(new FileOutputStream(pomFile)));
			} catch (IOException | TemplateException e) {
				System.out.println(module.getCode() + "创建失败:" + e);
			}

			/** 3.迭代遍历目录下其他所有文件 */
			String templateModuleDir = String.format("%s%s%s%s", this.templateModuleDir, TEMPLATE, SEPARATOR,
					module.getCode(), SEPARATOR);

			String generatorModuleDir = dir;
			handle(get(templateModuleDir), get(generatorModuleDir), new File(templateModuleDir));
		});
	}
	public void handle(String templateModuleFile, String generatorModuleFile, File file) {
		String absolutePath = get(file.getAbsolutePath() + SEPARATOR);
		String reallyPath = absolutePath.replace(templateModuleFile, generatorModuleFile).replace(DIR_GROUPID,
				replaceDir).replace(CLASSNAME_ARTIFACTID, project.getClassNameArtifactId());
		if (file.isDirectory()) {
			if (!file.getAbsolutePath().equals(new File(templateModuleFile).getAbsolutePath())) {
				new File(reallyPath).mkdirs();
			}
			File[] listFiles = file.listFiles(e -> !POM_FILE.equals(e.getName()));
			for (File temp : listFiles) {
				handle(templateModuleFile, generatorModuleFile, temp);
			}
		} else if (file.isFile()) {
			try {
				String templateName = file.getAbsolutePath().substring(templateModuleFile.indexOf(TEMPLATE) + TEMPLATE.length());
				Template template = conf.getTemplate(get(templateName));
				template.process(this, new OutputStreamWriter(new FileOutputStream(reallyPath)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createParent(String generatorDir, Configuration conf2) {
		try {
			/** 1.初始化目录及文件 */
			String dir = generatorDir + project.getArtifactId();
			File pomFile = getPomFile(dir);
			
			/** 2.获取模板并渲染 */
			Template parentPom = conf.getTemplate(POM_FILE);
			parentPom.process(this, new OutputStreamWriter(new FileOutputStream(pomFile), "UTF-8"));
			baseDir = pomFile.getParent() + SEPARATOR;
		} catch (Exception e) {
			System.out.println("主工程创建失败:" + e);
		}
	}

	private void initConf() {
		try {
			URL resource = FrameworkGenerator.class.getClassLoader().getResource(TEMPLATE);
			String runDir = null;
			if (resource != null) {
				runDir = resource.getPath();
				templateModuleDir = String.format("%s/src/main/resources/", projectDir);
			} else {
				runDir = FrameworkGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				runDir = new File(runDir).getParent() + SEPARATOR;
				templateModuleDir = runDir;
				runDir = runDir + TEMPLATE;
			}
			runDir = get(runDir);
			conf = new Configuration(Configuration.VERSION_2_3_22);
			conf.setDirectoryForTemplateLoading(new File(runDir));
			conf.setDefaultEncoding("UTF-8");
			conf.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String get(String value) {
		return value.replace("\\", "/");
	}
	
	private File getPomFile(String dir) {
		File filePath = new File(dir);
		if (filePath.exists()) {
			filePath.delete();
		}
		filePath.mkdirs();
		File pomFile = new File(filePath, POM_FILE);
		if (pomFile.exists()) {
			pomFile.delete();
		}
		return pomFile;
	}
}