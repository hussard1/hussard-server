package com.hussard.web.base.bbs.domain;

import com.hussard.web.base.domain.DefaultColumns;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int contentId;
	@Size(min=1, max=300)
	private String replyDetails;
	private String regiIpAddress;
	@Embedded
	private DefaultColumns defaultColumns;

	public Reply() {
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

	public String getReplyDetails() {
		return replyDetails;
	}

	public void setReplyDetails(String replyDetails) {
		this.replyDetails = replyDetails;
	}

	public String getRegiIpAddress() {
		return regiIpAddress;
	}

	public void setRegiIpAddress(String regiIpAddress) {
		this.regiIpAddress = regiIpAddress;
	}

	public DefaultColumns getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(DefaultColumns defaultColumns) {
		this.defaultColumns = defaultColumns;
	}
}
