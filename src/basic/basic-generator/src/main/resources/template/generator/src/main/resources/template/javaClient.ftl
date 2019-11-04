package ${"$"}{javaClientPackageName};

import org.apache.ibatis.annotations.Mapper;

import com.basic.mapper.BaseMapper;
import ${"$"}{t.packageName}.${"$"}{t.className};

${"<#if t.primaryKeysPackageName??>"}
import ${"$"}{t.primaryKeysPackageName}.${"$"}{t.primaryKeysType};
${"</#if>"}

@Mapper
public interface ${"$"}{t.className}Mapper extends BaseMapper<${"$"}{t.className},${"$"}{t.primaryKeysType}>{

}