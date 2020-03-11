package com.lin.utils;

import lombok.Data;

import java.util.List;

/**
 * 用来返回分页 Grid 的数据格式
 * @author lkmc2
 * @date 2020/3/11 21:40
 */
@Data
public class PagedGridResult {

    /** 当前页数 **/
    private int page;

    /** 总页数 **/
    private int total;

    /** 总记录数 **/
    private long records;

    /** 每行显示的内容 **/
    private List<?> rows;

}
