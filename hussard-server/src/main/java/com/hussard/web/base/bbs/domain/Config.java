package com.hussard.web.base.bbs.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Config {

    private int bbsId;
    @NotNull
    @Size(min=1, max=20)
    private String bbsName;
    private String bbsDesc;
    @NotNull
    private Integer perPage;
    private String replyYn;
    private int readAuth;
    private int writeAuth;
    private int replyWriteAuth;
    private String useYn;
    private String regiId;
    private Date regiDtime;
    private String modiId;
    private Date modiDtime;


    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public String getReplyYn() {
        return replyYn;
    }

    public void setReplyYn(String replyYn) {
        this.replyYn = replyYn;
    }

    public int getReadAuth() {
        return readAuth;
    }

    public void setReadAuth(int readAuth) {
        this.readAuth = readAuth;
    }

    public int getWriteAuth() {
        return writeAuth;
    }

    public void setWriteAuth(int writeAuth) {
        this.writeAuth = writeAuth;
    }


    public int getReplyWriteAuth() {
        return replyWriteAuth;
    }

    public void setReplyWriteAuth(int replyWriteAuth) {
        this.replyWriteAuth = replyWriteAuth;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRegiId() {
        return regiId;
    }

    public void setRegiId(String regiId) {
        this.regiId = regiId;
    }

    public Date getRegiDtime() {
        return regiDtime;
    }

    public void setRegiDtime(Date regiDtime) {
        this.regiDtime = regiDtime;
    }

    public String getModiId() {
        return modiId;
    }

    public void setModiId(String modiId) {
        this.modiId = modiId;
    }

    public Date getModiDtime() {
        return modiDtime;
    }

    public void setModiDtime(Date modiDtime) {
        this.modiDtime = modiDtime;
    }


    public String getBbsDesc() {
        return bbsDesc;
    }

    public void setBbsDesc(String bbsDesc) {
        this.bbsDesc = bbsDesc;
    }
}
