package com.hussard.web.base.util;

/**
 * Created by user on 2016-02-17.
 */
public class PageNation {

    private static final int BLOCK_SIZE = 10;

    int page;
    int pageSize;
    int totalContentCnt;

    int block = 0;
    int totalBlock = 0;
    int totalPage = 0;



    public PageNation(int page, int pageSize, long totalContentCnt) {
        this.page = page;
        this.pageSize = pageSize;

        if (totalContentCnt <= Integer.MAX_VALUE) {
            this.totalContentCnt = (int) totalContentCnt;
        }

        init();
    }

    private void init(){
        block = (page - 1) / BLOCK_SIZE + 1;

        int startpage = ((block-1)*BLOCK_SIZE) + 1;

        int endpage = startpage + BLOCK_SIZE -1;

        totalPage = (totalContentCnt - 1) / pageSize + 1;

        totalBlock = (totalPage - 1) / BLOCK_SIZE + 1;

    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalContentCnt() {
        return totalContentCnt;
    }

    public void setTotalContentCnt(int totalContentCnt) {
        this.totalContentCnt = totalContentCnt;
    }
}
