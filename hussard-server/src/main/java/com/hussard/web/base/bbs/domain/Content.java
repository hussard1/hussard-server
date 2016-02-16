package com.hussard.web.base.bbs.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by user on 2015-07-01.
 */
public class Content {

    private int contentId;
    private int bbsId;
    @NotNull
    @Size(min=1, max=30)
    private String contentSubject;
    @NotNull
    @Size(min=1)
    private String contentDetails;
    private int contentViewCnt;
    private String regiIpAddress;
    private int contentType;
    private String contentStaDtime;
    private String contentEndDtime;
    private int contentPopupYn;
    private String contentPopupStaDtime;
    private String contentPopupEndDtime;
    private String useYn;
    private String regiId;
    private Date regiDtime;
    private String modiId;
    private Date modiDtime;
    private String replyCnt;
    private String fileCnt;
    private MultipartFile fileUpload[];
    private Integer fileDelId[];

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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
}
