package com.jk.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PageResultUtil<T> {

    private int start; // 启始条数
    private int end; // 结束条数
    private int totalPage; // 总页数
    private int cpage = 1; // 当前页
    private long totalRow; // 总信息数
    private int pageSize = 3; // 分页单位
    private Map<String, String> link = new HashMap<String, String>(); // 查询条件
    private List<T> list;  //当前页的数据


    public PageResultUtil(int cpage, long totalRow, int pageSize) {
        this.totalRow = totalRow;
        this.pageSize = pageSize;
        this.cpage = (cpage <= 0) ? 1 : cpage;
        this.totalPage = (int) (totalRow / pageSize);
        if (totalRow % pageSize > 0)
            this.totalPage = this.totalPage + 1;
        if (this.cpage > this.totalPage)
            this.cpage = this.totalPage;

        this.start = (this.cpage - 1) * pageSize;
        this.end = this.start + pageSize - 1;
    }

    public PageResultUtil() {
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCpage() {
        return cpage;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public int getPageSize() {
        return pageSize;
    }


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCpage(int cpage) {
        this.cpage = cpage;
    }

    public void setTotalRow(long totalRow) {
        this.totalPage = (int) (totalRow / pageSize);
        if (totalRow % pageSize > 0)
            this.totalPage = this.totalPage + 1;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }


}
