package ${"$"}{packageName};

import lombok.Data;
import lombok.EqualsAndHashCode;
${"<#if (primaryKeyColumns?size>1)>"}
import ${"$"}{primaryKeysPackageName}.${"$"}{primaryKeysType};
${"</#if>"}
${"<#if importPackages?exists>"}
${"<#list importPackages as package>"}
import ${"$"}{package};
${"</#list>"}
${"</#if>"}

/**
 *  ${"$"}{common}
 *  Auto Generate
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ${"$"}{className} ${"<#if (primaryKeyColumns?size>1) >"}extends ${"$"}{primaryKeysType}${"</#if>"}{
							   
	${"<#if (primaryKeyColumns?size==1) >"}
	${"<#list primaryKeyColumns as column>"}
    /** ${"$"}{column.common} */
    private ${"$"}{column.simpleJavaType} ${"$"}{column.propertyName};
    ${"</#list>"}
	${"</#if>"}
    ${"<#list columns as column>"}
    /** ${"$"}{column.common} */
    private ${"$"}{column.simpleJavaType} ${"$"}{column.propertyName};
    ${"</#list>"}
}