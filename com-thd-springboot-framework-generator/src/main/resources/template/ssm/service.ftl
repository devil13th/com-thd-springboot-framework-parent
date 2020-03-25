package com.thd.springboottest.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ${coding.entityPackageName}.${table.nameBigCamel}Entity;
import org.springframework.stereotype.Service;
import com.thd.springboot.framework.db.service.BasicService;
import java.util.List;
@Service
public interface ${table.nameBigCamel}Service  extends BasicService<${table.nameBigCamel}Entity>{
    public List queryAll();
    public ${table.nameBigCamel}Entity query${table.nameBigCamel}(${table.pkColumn.dataType} ${table.pkColumn.nameCamel});
    public List<${table.nameBigCamel}Entity> query${table.nameBigCamel}(QueryWrapper<${table.nameBigCamel}Entity> wrapper);

}
