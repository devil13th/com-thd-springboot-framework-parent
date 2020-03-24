package ${coding.entityPackageName};

import lombok.Data;

import java.util.Date;

/**
 * @author devil13th
 **/
@Data
public class  ${table.nameBigCamel} {
    // ${table.pkColumn.comment} - PK
    private ${table.pkColumn.dataType} ${table.pkColumn.nameCamel};
    <#list table.normalColumns as col>
    // ${col.comment}
    private ${col.dataType} ${col.nameCamel};
    </#list>
}
