package ${coding.entityPackageName};

import lombok.Data;

import java.util.Date;
import com.thd.springboot.framework.db.entity.BasicEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("${table.name}")
public class  ${table.nameBigCamel}Entity extends BasicEntity<${table.pkColumn.dataType}> {
    // ${table.pkColumn.comment} - PK
    @TableField("${table.pkColumn.name}")
    private ${table.pkColumn.dataType} ${table.pkColumn.nameCamel};
    <#list table.normalColumns as col>
    // ${col.comment}
    @TableField("${col.name}")
    private ${col.dataType} ${col.nameCamel};
    </#list>
}
