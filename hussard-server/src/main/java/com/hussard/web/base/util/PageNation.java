package com.hussard.web.base.util;

/**
 * Created by user on 2016-02-17.
 */
public class PageNation {

    private static final int BLOCK_SIZE = 10;

    private int pageNum;
    private int pageSize;
    private int totalContentCnt;

    private int block = 0;
    private int totalBlock = 0;
    private int totalPage = 0;
    private int startPage = 1;
    private int endPage = 10;


    public PageNation(int pageNum, int pageSize, long totalContentCnt) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        if (totalContentCnt <= Integer.MAX_VALUE) {
            this.totalContentCnt = (int) totalContentCnt;
        }

        init();
    }

    private void init(){
        block = (pageNum - 1) / BLOCK_SIZE + 1;

        startPage = ((block-1)*BLOCK_SIZE) + 1;

        totalPage = (totalContentCnt - 1) / pageSize + 1;

        endPage = startPage + BLOCK_SIZE -1;

        if(endPage >= totalPage){
            endPage = totalPage;
        }

        totalBlock = (totalPage - 1) / BLOCK_SIZE + 1;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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

    public int getStartPage(){return startPage;}

    public void setStartPage(int startPage){this.startPage = startPage;}

    public int getEndPage(){return endPage;}

    public void setEndPage(int endPage){this.endPage = endPage;}

    public int getBlock(){return block;}

    public void setBlock(int block){this.block = block;}

    public int getTotalBlock(){return totalBlock;}

    public void setTotalBlock(int totalBlock){this.totalBlock = totalBlock;}

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
