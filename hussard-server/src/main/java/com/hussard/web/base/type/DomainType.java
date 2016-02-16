package com.hussard.web.base.type;

/**
 * Created by Matthew on 2015-06-09.
 */
public class DomainType {

    public static final int LOCALE_CODE         = 2;
    public static final int USERNAME            = 40;
    public static final int NAME                = 100;
    public static final int PASSWORD            = 100;
    public static final int TOKEN_KEY           = 64;

    public static final int NUMBER              = 20;
    public static final int IP                  = 15;
    public static final int MAC                 = 20;
    public static final int PHONE               = 50;

    public static final int UUID_CODE           = 40;
    public static final int SEQ           		= 5;

    public static final int DESCRIPTION         = 4000;
    public static final int ROUTE               = 50;
    public static final int SYSTEM_DIV          = 50;

    public static final int KEY_LENGTH          = 64;

    public static final int YEAR          		= 4;
    public static final int BIRTHDAY      		= 8;
    public static final int CALENDAR_TYPE  		= 5;

    private DomainType() {
        // empty cause this is static class
    }
}
