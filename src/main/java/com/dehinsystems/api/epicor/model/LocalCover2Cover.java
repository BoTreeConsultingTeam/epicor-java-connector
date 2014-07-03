/**
 * 
 */
package com.dehinsystems.api.epicor.model;

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
}
