package com.atguigu.cloud.common;

import lombok.Data;

import java.util.List;

/**
 * 前端分页显示
 *
 * @param <T>
 * @author zt
 */
@Data
public class Page<T> {

    protected Result result = new Result();
    /**
     * 结果集
     */
    protected List<T> list;

    public void setResult(int current, int pageSize, long total) {
        Result result = new Result();
        result.setCurrent(current);
        result.setPageSize(pageSize);
        result.setTotal(total);
        this.result = result;
    }

    @Data
    public static class Result {
        /**
         * 当前页
         */
        private int current;
        /**
         * 每页的数量
         */
        private int pageSize;
        /**
         * 总记录数
         */
        protected long total;
    }
}


