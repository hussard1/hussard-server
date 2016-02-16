package com.hussard.web.base.bbs.domain;

import javax.validation.constraints.Size;
import java.util.Date;


public class Reply {
	private int replyId;
	private int contentId;
	@Size(min=1, max=300)
	private String replyDetails;
	private String regiIpAddress;
	private String useYn;
	private String regiId;
	private Date regiDtime;
	private String modiId;
	private Date modiDtime;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
}
