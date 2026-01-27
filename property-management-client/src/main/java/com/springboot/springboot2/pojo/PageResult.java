package com.springboot.springboot2.pojo;

import com.github.pagehelper.Page;

import java.util.List;

public class PageResult<T> {
    private List<T> records;   // 当前页数据
    private long total;        // 总记录数
    private int current;       // 当前页码
    private int size;          // 每页条数

    public PageResult() {}

    public PageResult(List<T> records, long total, int current, int size) {
        this.records = records;
        this.total = total;
        this.current = current;
        this.size = size;
    }

    public List<T> getRecords() { return records; }
    public void setRecords(List<T> records) { this.records = records; }

    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }

    public int getCurrent() { return current; }
    public void setCurrent(int current) { this.current = current; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    /**
     * 将 PageHelper 的 Page<T> 转成 PageResult<T>
     */
    public static <T> PageResult<T> restPage(Page<T> page) {
        return new PageResult<>(
                page.getResult(),
                page.getTotal(),
                page.getPageNum(),
                page.getPageSize()
        );
    }
}
