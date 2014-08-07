package com.dehinsystems.api.epicor.model;


public class BGManufactureInfo {

	private String manufactureName;
	
	private String orderNumber;
	
	private String discriptorText;
	
	private ManufacturerDetails mfgDetails;

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDiscriptorText() {
		return discriptorText;
	}

	public void setDiscriptorText(String discriptorText) {
		this.discriptorText = discriptorText;
	}

	public ManufacturerDetails getMfgDetails() {
		return mfgDetails;
	}

	public void setMfgDetails(ManufacturerDetails mfgDetails) {
		this.mfgDetails = mfgDetails;
	}

	@Override
	public String toString() {
		return "BGManufactureInfo [manufactureName=" + manufactureName
				+ ", orderNumber=" + orderNumber + ", discriptorText="
				+ discriptorText + ", mfgDetails=" + mfgDetails + "]";
	}	
}
