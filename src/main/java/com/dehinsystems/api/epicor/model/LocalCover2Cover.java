/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.List;

/**
 * @author tgbaxi
 *
 */
public class LocalCover2Cover {

	private String partNumber;
	
	private String c2cURL;
	
	private String lineCode;
	
	private String orderNumber;
	
	private String imageLibrary;
	
	private String thumbnailFile;
	
	private List<String> imageUrls;
	
	private String errorMessage;

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getC2cURL() {
		return c2cURL;
	}

	public void setC2cURL(String c2cURL) {
		this.c2cURL = c2cURL;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getImageLibrary() {
		return imageLibrary;
	}

	public void setImageLibrary(String imageLibrary) {
		this.imageLibrary = imageLibrary;
	}

	public String getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(String thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "LocalCover2Cover [partNumber=" + partNumber + ", c2cURL="
				+ c2cURL + ", lineCode=" + lineCode + ", orderNumber="
				+ orderNumber + ", imageLibrary=" + imageLibrary
				+ ", thumbnailFile=" + thumbnailFile + ", imageUrls="
				+ imageUrls + "]";
	}
}
