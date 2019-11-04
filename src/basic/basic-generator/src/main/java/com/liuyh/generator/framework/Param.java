package com.liuyh.generator.framework;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @desc 生成代码的data 
 * @author Liuyh
 * @date 2019年10月11日下午3:03:41
 */
@Getter
@Setter
public class Param {
	private String groupId;
	private String artifactId;
	private String classNameArtifactId;
	private List<Module> modules;
}
