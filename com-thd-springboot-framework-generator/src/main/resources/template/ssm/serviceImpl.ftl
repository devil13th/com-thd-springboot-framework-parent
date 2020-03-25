package com.thd.springboottest.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thd.springboot.framework.db.mapper.BasicMapper;
import com.thd.springboot.framework.db.service.BasicServiceImpl;
import ${coding.entityPackageName}.${table.nameBigCamel}Entity;
import ${coding.mapperPackageName}.${table.nameBigCamel}Mapper;
import ${coding.servicePackageName}.${table.nameBigCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ${table.nameBigCamel}ServiceImpl extends BasicServiceImpl<${table.nameBigCamel}Entity> implements ${table.nameBigCamel}Service {
    @Autowired
    private ${table.nameBigCamel}Mapper ${table.nameCamel}Mapper;

    @Override
    public BasicMapper<${table.nameBigCamel}Entity> basicMapper() {
        return ${table.nameCamel}Mapper;
    }

    public List queryAll(){
        return ${table.nameCamel}Mapper.selectList(null);
    };

    public ${table.nameBigCamel}Entity query${table.nameBigCamel}(${table.pkColumn.dataType} ${table.pkColumn.nameCamel}){
        QueryWrapper<${table.nameBigCamel}Entity> qw = new QueryWrapper<${table.nameBigCamel}Entity>();
        qw.eq("user_id",id);
        return ${table.nameCamel}Mapper.selectOne(qw);
    };

    public List<${table.nameBigCamel}Entity> query${table.nameBigCamel}(QueryWrapper<${table.nameBigCamel}Entity> wrapper){
        return ${table.nameCamel}Mapper.selectList(wrapper);
    }





}
