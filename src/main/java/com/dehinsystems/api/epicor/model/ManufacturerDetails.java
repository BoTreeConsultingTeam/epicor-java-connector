/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.List;

/**
 * @author tgbaxi
 *
 */
public class ManufacturerDetails {

	private String manufacturer;
	
	private String groupNumber;
	
	private String groupText;
	
	private String partDescText;
	
	private String partDescID;
	
	private String lineCode;
	
	private String orderNumber;
	
	private List<CompatibilityInfo> compatibilityInfos;
	
	private LocalCover2Cover localCover2Cover;
	
	private List<PricePartInfo> pricePartInfos;
	
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

	public List<CompatibilityInfo> getCompatibilityInfos() {
		return compatibilityInfos;
	}

	public void setCompatibilityInfos(List<CompatibilityInfo> compatibilityInfos) {
		this.compatibilityInfos = compatibilityInfos;
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

	public LocalCover2Cover getLocalCover2Cover() {
		return localCover2Cover;
	}

	public void setLocalCover2Cover(LocalCover2Cover localCover2Cover) {
		this.localCover2Cover = localCover2Cover;
	}

	public List<PricePartInfo> getPricePartInfos() {
		return pricePartInfos;
	}

	public void setPricePartInfos(List<PricePartInfo> pricePartInfos) {
		this.pricePartInfos = pricePartInfos;
	}

	@Override
	public String toString() {
		return "ManufacturerDetails [manufacturer=" + manufacturer
				+ ", groupNumber=" + groupNumber + ", groupText=" + groupText
				+ ", partDescText=" + partDescText + ", partDescID="
				+ partDescID + ", lineCode=" + lineCode + ", orderNumber="
				+ orderNumber + ", compatibilityInfos=" + compatibilityInfos
				+ ", localCover2Cover=" + localCover2Cover
				+ ", pricePartInfos=" + pricePartInfos + ", getManufacturer()="
				+ getManufacturer() + ", getGroupNumber()=" + getGroupNumber()
				+ ", getGroupText()=" + getGroupText() + ", getPartDescText()="
				+ getPartDescText() + ", getPartDescID()=" + getPartDescID()
				+ ", getCompatibilityInfos()=" + getCompatibilityInfos()
				+ ", getLineCode()=" + getLineCode() + ", getOrderNumber()="
				+ getOrderNumber() + ", getLocalCover2Cover()="
				+ getLocalCover2Cover() + ", getPricePartInfos()="
				+ getPricePartInfos() + "]";
	}
}
