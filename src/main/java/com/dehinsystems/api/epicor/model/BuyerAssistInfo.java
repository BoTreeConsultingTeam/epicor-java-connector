package com.dehinsystems.api.epicor.model;

import java.util.List;


/**
 * @author Harshil
 *
 */
public class BuyerAssistInfo {

	private String manufacturer;

	private String groupNumber;

	private String groupText;

	private String partDescText;

	private String partDescID;
	
	private String lineCode;
	
	private String orderNumber;
	
	private List<CompatibilityInfo> compatibilityInfo;
	
	private List<String> imageUrls;
	
	private String errorMessage;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupText() {
		return groupText;
	}

	public void setGroupText(String groupText) {
		this.groupText = groupText;
	}

	public String getPartDescText() {
		return partDescText;
	}

	public void setPartDescText(String partDescText) {
		this.partDescText = partDescText;
	}

	public String getPartDescID() {
		return partDescID;
	}

	public void setPartDescID(String partDescID) {
		this.partDescID = partDescID;
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

	public List<CompatibilityInfo> getCompatibilityInfo() {
		return compatibilityInfo;
	}

	public void setCompatibilityInfo(List<CompatibilityInfo> compatibilityInfo) {
		this.compatibilityInfo = compatibilityInfo;
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
		return "BuyerAssistInfo [manufacturer=" + manufacturer
				+ ", groupNumber=" + groupNumber + ", groupText=" + groupText
				+ ", partDescText=" + partDescText + ", partDescID="
				+ partDescID + ", lineCode=" + lineCode + ", orderNumber="
				+ orderNumber + ", compatibilityInfo=" + compatibilityInfo
				+ ", imageUrls=" + imageUrls + "]";
	}

	
}
