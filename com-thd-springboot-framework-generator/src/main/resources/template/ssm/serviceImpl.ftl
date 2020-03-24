package com.thd.springboottest.mybatisplus.service;

import java.util.List;

/**
 * com.thd.springboottest.mybatisplus.service.${table.nameBigCamel}ServiceImpl
 *
 * @author: wanglei62
 **/

@Service
public class ${table.nameBigCamel}ServiceImpl implements ${table.nameBigCamel}Service {
    @Autowired
    private ${table.nameBigCamel}Mapper ${table.nameCamel}Mapper;

    public List queryAll(){
        return ${table.nameCamel}Mapper.selectList(null);
    };

    public ${table.nameBigCamel} query${table.nameBigCamel}(${table.pkColumn.dataType} ${table.pkColumn.nameCamel}){
        QueryWrapper<${table.nameBigCamel}> qw = new QueryWrapper<${table.nameBigCamel}>();
        qw.eq("user_id",id);
        return ${table.nameCamel}Mapper.selectOne(qw);
    };

    public List<${table.nameBigCamel}> query${table.nameBigCamel}(QueryWrapper<${table.nameBigCamel}> wrapper){
        return ${table.nameCamel}Mapper.selectList(wrapper);
    }





}
