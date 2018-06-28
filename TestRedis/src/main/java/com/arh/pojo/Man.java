package com.arh.pojo;

import java.io.Serializable;
import java.util.Date;

public class Man implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer myId;
	private Date myDate;
	private String info;

	public Integer getMyId() {
		return myId;
	}

	public void setMyId(Integer myId) {
		this.myId = myId;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
