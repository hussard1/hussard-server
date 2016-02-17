package com.hussard.web.base.bbs.domain;

import com.hussard.web.base.domain.DefaultColumns;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BbsFile implements Serializable{

	private static final long serialVersionUID = 5284702939442078668L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int contentId;
	private String fileName;
	private String fileOriName;
	private String fileUrl;
	private long fileSize;
	@Embedded
	private DefaultColumns defaultColumns;

	public BbsFile() {
		this.defaultColumns = new DefaultColumns();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}

	public DefaultColumns getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(DefaultColumns defaultColumns) {
		this.defaultColumns = defaultColumns;
	}
}
