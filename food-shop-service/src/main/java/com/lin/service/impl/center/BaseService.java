package com.lin.service.impl.center;

import com.github.pagehelper.PageInfo;
import com.lin.utils.PagedGridResult;

import java.util.List;

/**
 * 公共服务逻辑
 * @author lkmc2
 * @date 2020/3/21 20:58
 */
public class BaseService {

    /**
     * 设置分页结果信息
     * @param list 查询结果列列表
     * @param page 当前页
     * @return 分页结果信息
     */
    protected PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);

        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }

}
