<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${'$'}{javaClientPackageName}.${'$'}{t.className}Mapper">
  <resultMap id="BaseResultMap" type="${"$"}{javaBeanPackageName}.${"$"}{t.className}">
  	${"<#list t.primaryKeyColumns as column>"}
  	<id column="${"$"}{column.columnName}" jdbcType="${"$"}{column.jdbcType}" property="${"$"}{column.propertyName}" />
  	${"</#list>"}
  	${"<#list t.columns as column>"}
  	<result column="${"$"}{column.columnName}" jdbcType="${"$"}{column.jdbcType}" property="${"$"}{column.propertyName}" />
  	${"</#list>"}
  </resultMap>
  
  <sql id ="Table_Name">
  	${"$"}{t.sqlTableName}
  </sql>
  
  <sql id="Base_Column_List">
  	${"<#if t.primaryKeyColumns?exists>"}
	${"<#list t.primaryKeyColumns as column>"}${"$"}{column.columnName}${"<#if column_has_next>,</#if></#list>,"}
  	${"</#if>"}
  	${"<#list t.columns as column>"}${"$"}{column.columnName}${"<#if column_has_next>,</#if></#list>"}
  </sql>
  
  <select id="selectByPrimaryKey" parameterType="${'$'}{t.primaryKeysType}" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from <include refid="Table_Name" />
    where
    ${"<#list t.primaryKeyColumns as column>"}
    	${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}
    	${"<#if column_has_next>and </#if>"}
    ${"</#list>"}
  </select>
  <delete id="deleteByPrimaryKey"  parameterType="${'$'}{t.className}">
    delete from <include refid="Table_Name" />
    where 
    ${"<#list t.primaryKeyColumns as column>"}
    	${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}
    	${"<#if column_has_next>and </#if>"}
    ${"</#list>"}
  </delete>
  <insert id="insert" parameterType="${'$'}{t.className}" ${"<#if (t.primaryKeyColumns?size=1)>"}${"<#if t.primaryKeyColumns[0].isAutoincrement='YES'>"}useGeneratedKeys="true" keyProperty="${'$'}{t.primaryKeyColumns[0].columnName}"${"</#if></#if>"}>
  	insert into <include refid="Table_Name" /> (${"<#if (t.primaryKeyColumns?size=1)>"}${"<#if t.primaryKeyColumns[0].isAutoincrement='NO'>"}${"$"}{t.primaryKeyColumns[0].columnName},${"</#if></#if>"}${"<#if (t.primaryKeyColumns?size>1)>"}${"<#list t.primaryKeyColumns as column>"}${"$"}{column.columnName},${"</#list></#if>"}${"<#list t.columns as column>"}${"$"}{column.columnName}${"<#if column_has_next>"},${"</#if>"}${"</#list>"})
  	values (${"<#if (t.primaryKeyColumns?size=1)>"}${"<#if t.primaryKeyColumns[0].isAutoincrement='NO'>"}${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{t.primaryKeyColumns[0].propertyName},jdbcType=${"$"}{t.primaryKeyColumns[0].jdbcType}},${"</#if></#if>"}${"<#if (t.primaryKeyColumns?size>1)>"}${"<#list t.primaryKeyColumns as column>"}${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}},${"</#list></#if>"}${"<#list t.columns as column>"}${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}${"<#if column_has_next>"},${"</#if>"}${"</#list>"})
  </insert>
  <insert id="insertSelective" parameterType="${'$'}{t.className}" ${"<#if (t.primaryKeyColumns?size=1)>"}${"<#if t.primaryKeyColumns[0].isAutoincrement='YES'>"}useGeneratedKeys="true" keyProperty="${'$'}{t.primaryKeyColumns[0].columnName}"${"</#if></#if>"}>
    insert into <include refid="Table_Name" /> 
    <trim prefix="(" suffix=")" suffixOverrides=",">${"<#if (t.primaryKeyColumns?size=1)>"}${"<#if t.primaryKeyColumns[0].isAutoincrement='NO'>"}
    <if test="${'$'}{t.primaryKeyColumns[0].propertyName} != null">
    	${"$"}{t.primaryKeyColumns[0].columnName},
    ${"</if>"}${"</#if>"}${"</#if>"}${"<#if (t.primaryKeyColumns?size>1)>"}${"<#list t.primaryKeyColumns as column>"}
    <if test="${'$'}{column.propertyName} != null">
    	${"$"}{column.columnName},
	${"</if>"}${"</#list>"}${"</#if>"}${"<#list t.columns as column>"}
    <if test="${'$'}{column.propertyName} != null">
    	${"$"}{column.columnName},
	${"</if>"}${"</#list>"}
	</trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
    ${"<#if (t.primaryKeyColumns?size=1)>"}
    ${"<#if t.primaryKeyColumns[0].isAutoincrement='NO'>"}
    <if test="${'$'}{t.primaryKeyColumns[0].propertyName} != null">
    	${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{t.primaryKeyColumns[0].propertyName},jdbcType=${"$"}{t.primaryKeyColumns[0].jdbcType}},
    </if>
    ${"</#if>"}
    ${"</#if>"}
    ${"<#if (t.primaryKeyColumns?size>1)>"}
     ${"<#list t.primaryKeyColumns as column>"}
    <if test="${'$'}{column.propertyName} != null">
    	${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}},
	</if>
    ${"</#list>"}
    ${"</#if>"}
    ${"<#list t.columns as column>"}
    <if test="${'$'}{column.propertyName} != null">
    	${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}},
	</if>
    ${"</#list>"}
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="${'$'}{t.className}">
    update <include refid="Table_Name" />
    <set>
    ${"<#list t.columns as column>"}
    <if test="${'$'}{column.propertyName} != null">
    	${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}${"<#if column_has_next>,</#if>"}
	</if>
    ${"</#list>"}
    </set>
    where 
    ${"<#list t.primaryKeyColumns as column>"}
    	${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}
    	${"<#if column_has_next>and</#if>"}
    ${"</#list>"}
  </update>
  <update id="updateByPrimaryKey" parameterType="${'$'}{t.className}">
    update <include refid="Table_Name" />
    set
    ${"<#list t.columns as column>"}
    ${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}${"<#if column_has_next>,</#if>"}
    ${"</#list>"}
    where 
    ${"<#list t.primaryKeyColumns as column>"}
    	${"$"}{column.columnName} = ${"$"}${"{\""}${"#"}${"\"}"}{${"$"}{column.propertyName},jdbcType=${"$"}{column.jdbcType}}
    	${"<#if column_has_next>and </#if>"}
    ${"</#list>"}
  </update>
</mapper>