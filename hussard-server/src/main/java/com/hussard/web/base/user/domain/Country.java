package com.hussard.web.base.user.domain;

/**
 * Created by Matthew on 2015-06-08.
 */
public enum Country {

    KR("South Korea"),
    US("United States");

    private String detail;

    Country(String detail) {
        this.detail = detail;
    }

    public String getCountry() {
        return detail;
    }
}
