package com.thd.springboot.framework.db.utils;

import com.github.pagehelper.PageHelper;
import com.thd.springboot.framework.entity.BasicEntity;

/**
 * com.thd.springboot.framework.db.utils.PageUtils
 *
 * @author: wanglei62
 * @DATE: 2020/4/9 11:28
 **/
public class PageUtils {
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static <T extends BasicEntity> void setPageHelper(T entity) {
        if (entity.getPageNum() == null) {
            entity.setPageNum(DEFAULT_PAGE_NUM);
        }
        if (entity.getPageSize() == null) {
            entity.setPageSize(DEFAULT_PAGE_SIZE);
        }
        PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), entity.getOrderBy());
    }
}
