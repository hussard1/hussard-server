package com.hussard.web.base.bbs.domain;

import com.hussard.web.base.domain.DefaultColumns;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2015-07-01.
 */

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="bbs_id")
    private int bbsId;
    @NotNull
    @Size(min=1, max=30)
    @Column(name="content_subject")
    private String contentSubject;
    @NotNull
    @Size(min=1)
    @Column(name="content_details")
    private String contentDetails;
    @Column(name="content_view_cnt")
    private int contentViewCnt;
    @Column(name="regi_ip_address")
    private String regiIpAddress;
    @Column(name="content_type")
    private int contentType;
    private String contentStaDtime;
    private String contentEndDtime;
    private int contentPopupYn;
    private String contentPopupStaDtime;
    private String contentPopupEndDtime;
    @Column(name="reply_cnt")
    private String replyCnt;
    @Column(name="file_cnt")
    private String fileCnt;
    @Transient
    private MultipartFile fileUpload[];
    @Transient
    private Integer fileDelId[];
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "content_id")
    private Set<Reply> reply;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private List<BbsFile> bbsFile;

    @Embedded
    private DefaultColumns defaultColumns;

    public Content() {
        this.defaultColumns = new DefaultColumns();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public String getContentSubject() {
        return contentSubject;
    }

    public void setContentSubject(String contentSubject) {
        this.contentSubject = contentSubject;
    }

    public String getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(String contentDetails) {
        this.contentDetails = contentDetails;
    }

    public int getContentViewCnt() {
        return contentViewCnt;
    }

    public void setContentViewCnt(int contentViewCnt) {
        this.contentViewCnt = contentViewCnt;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }


    public String getRegiIpAddress() {
        return regiIpAddress;
    }

    public void setRegiIpAddress(String regiIpAddress) {
        this.regiIpAddress = regiIpAddress;
    }

    public String getContentStaDtime() {
        return contentStaDtime;
    }

    public void setContentStaDtime(String contentStaDtime) {
        this.contentStaDtime = contentStaDtime;
    }

    public String getContentEndDtime() {
        return contentEndDtime;
    }

    public void setContentEndDtime(String contentEndDtime) {
        this.contentEndDtime = contentEndDtime;
    }

    public String getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(String replyCnt) {
        this.replyCnt = replyCnt;
    }

    public MultipartFile[] getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(MultipartFile[] fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Integer[] getFileDelId() {
        return fileDelId;
    }

    public void setFileDelId(Integer[] fileDelId) {
        this.fileDelId = fileDelId;
    }

    public int getContentPopupYn() {
        return contentPopupYn;
    }

    public void setContentPopupYn(int contentPopupYn) {
        this.contentPopupYn = contentPopupYn;
    }

    public String getContentPopupStaDtime() {
        return contentPopupStaDtime;
    }

    public void setContentPopupStaDtime(String contentPopupStaDtime) {
        this.contentPopupStaDtime = contentPopupStaDtime;
    }

    public String getContentPopupEndDtime() {
        return contentPopupEndDtime;
    }

    public void setContentPopupEndDtime(String contentPopupEndDtime) {
        this.contentPopupEndDtime = contentPopupEndDtime;
    }

    public String getFileCnt() {
        return fileCnt;
    }

    public void setFileCnt(String fileCnt) {
        this.fileCnt = fileCnt;
    }

    public Set<Reply> getReply() {
        return reply;
    }

    public void setReply(Set<Reply> reply) {
        this.reply = reply;
    }

    public List<BbsFile> getBbsFile() {
        return bbsFile;
    }

    public void setBbsFile(List<BbsFile> bbsFile) {
        this.bbsFile = bbsFile;
    }

    public DefaultColumns getDefaultColumns() {
        return defaultColumns;
    }

    public void setDefaultColumns(DefaultColumns defaultColumns) {
        this.defaultColumns = defaultColumns;
    }
}
