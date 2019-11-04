package ${"$"}{packageName};

import lombok.Getter;
import lombok.Setter;

${"<#if importPackages?exists>"}
${"<#list importPackages as package>"}
import ${"$"}{package};
${"</#list>"}
${"</#if>"}

/**
 *  Auto Generate
 */
@Getter
@Setter
public class ${"$"}{className}{
    ${"<#list columns as column>"}
    /** ${"$"}{column.common} */
    private ${"$"}{column.simpleJavaType} ${"$"}{column.propertyName};
    ${"</#list>"}
}