package com.myshop.common.ui;

public class Pagination {
    private int current;
    private int beginPage;
    private int endPage;
    private int numberOfPage;
    private int totalPages;

    public Pagination(int current, int totalPages, int numberOfPage) {
        this.current = current;
        this.totalPages = totalPages;
        this.numberOfPage = numberOfPage;
        int pageMod = current % 5;
        beginPage = pageMod == 0 ? current - 5 + 1 : current - pageMod + 1;
        endPage = beginPage + 5 - 1;
        if (endPage > totalPages) {
            endPage = totalPages;
        }
    }

    public int getCurrent() {
        return current;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public boolean isHasPrevious() {
        return beginPage > 1;
    }

    public int getPreviousBeginPage() {
        return beginPage - numberOfPage;
    }

    public int getNextBeginPage() {
        return beginPage + numberOfPage;
    }

    public boolean isHasNext() {
        return endPage < totalPages;
    }

    public int[] getPageNos() {
        int[] result = new int[endPage - beginPage + 1];
        for (int i = beginPage ; i <= endPage ; i++) {
            result[i - beginPage] = i;
        }
        return result;
    }
}
