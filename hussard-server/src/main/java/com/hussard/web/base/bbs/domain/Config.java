package com.hussard.web.base.bbs.domain;


import com.hussard.web.base.domain.DefaultColumns;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Config implements Serializable{

    private static final long serialVersionUID = -5980349850197237283L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    @Size(min=1, max=20)
    private String bbsName;
    private String bbsDesc;
    @NotNull
    private int perPage;
    private int replyYn;
    private int readAuth;
    private int writeAuth;
    private int replyWriteAuth;

    @Embedded
    private DefaultColumns defaultColumns;

    public Config() {
        this.defaultColumns = new DefaultColumns();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getReplyYn() {
        return replyYn;
    }

    public void setReplyYn(int replyYn) {
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

    public String getBbsDesc() {
        return bbsDesc;
    }

    public void setBbsDesc(String bbsDesc) {
        this.bbsDesc = bbsDesc;
    }

    public DefaultColumns getDefaultColumns() {
        return defaultColumns;
    }

    public void setDefaultColumns(DefaultColumns defaultColumns) {
        this.defaultColumns = defaultColumns;
    }
}
