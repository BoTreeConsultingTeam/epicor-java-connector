package com.dehinsystems.api.epicor.model;

/**
 * @author Harshil
 * 
 */
public class C2CDetails {

	private String contentLabel;
	private String contentData;

	public String getContentLabel() {
		return contentLabel;
	}

	public void setContentLabel(String contentLabel) {
		this.contentLabel = contentLabel;
	}

	public String getContentData() {
		return contentData;
	}

	public void setContentData(String contentData) {
		this.contentData = contentData;
	}

	@Override
	public String toString() {
		return "C2CDetails [contentLabel=" + contentLabel + ", contentData="
				+ contentData + "]";
	}

}
