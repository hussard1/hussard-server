package com.hussard.web.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kimsy on 2015-07-08.
 */
public class DefaultColumns implements Serializable {

    private static final long serialVersionUID = -6856575310192153955L;

    private boolean useYn = true;
    private String registrant = "system";
    private Date registrationDate = new Date();
    private String modifier = "system";
    private Date modificationDate = new Date();
    private String dpModificationDate;
    private String dpRegistrationDate;

    public boolean isUseYn() {
        return useYn;
    }

    public void setUseYn(boolean useYn) {
        this.useYn = useYn;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getDpModificationDate() {
        return dpModificationDate;
    }

    public void setDpModificationDate(String dpModificationDate) {
        this.dpModificationDate = dpModificationDate;
    }

    public String getDpRegistrationDate() {
        return dpRegistrationDate;
    }

    public void setDpRegistrationDate(String dpRegistrationDate) {
        this.dpRegistrationDate = dpRegistrationDate;
    }
}
