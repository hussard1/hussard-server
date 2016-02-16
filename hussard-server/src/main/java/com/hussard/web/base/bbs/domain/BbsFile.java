package com.hussard.web.base.bbs.domain;

import java.util.Date;

public class BbsFile {

	private int fileId;
	private int contentId;
	private String fileName;
	private String fileOriName;
	private String fileUrl;
	private long fileSize;
	private String useYn;
	private String regiId;
	private Date regiDtime;
	private String modiId;
	private Date modiDtime;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
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

	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}
}
