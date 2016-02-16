package com.hussard.web.base.util;

/**
 * Created by Kimsy on 2015-07-08.
 */
public class PaginationValidator {

    private PaginationValidator() {
        // this class can not be instanced
    }

    public static long validatePage(long page, long size, long total) {
        if (page < 1) {
            return 1;
        }

        long top = (long) Math.ceil((double) total / (double) size);
        if (page > top && top > 0 ) {
            return top;
        }

        return page;
    }

}
