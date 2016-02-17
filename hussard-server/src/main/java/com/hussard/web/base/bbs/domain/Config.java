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
    private Integer perPage;
    private String replyYn;
    private int readAuth;
    private int writeAuth;
    private int replyWriteAuth;

    @OneToMany(mappedBy = "config")
    private Set<Content> content;

    @Embedded
    private DefaultColumns defaultColumns;

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

    public String getBbsDesc() {
        return bbsDesc;
    }

    public void setBbsDesc(String bbsDesc) {
        this.bbsDesc = bbsDesc;
    }

    public Set<Content> getContent() {
        return content;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }

    public DefaultColumns getDefaultColumns() {
        return defaultColumns;
    }

    public void setDefaultColumns(DefaultColumns defaultColumns) {
        this.defaultColumns = defaultColumns;
    }
}
