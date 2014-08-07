package com.dehinsystems.api.epicor.model;

import java.util.List;

public class BGManufactureInfo {

	private String manufactureName;
	
	private String orderNumber;
	
	private String discriptorText;
	
	private List<BGDataWithMfgWithCoverageInfo> lstBgDataForMfg;

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
	
	public List<BGDataWithMfgWithCoverageInfo> getLstBgDataForMfg() {
		return lstBgDataForMfg;
	}

	public void setLstBgDataForMfg(List<BGDataWithMfgWithCoverageInfo> lstBgDataForMfg) {
		this.lstBgDataForMfg = lstBgDataForMfg;
	}

	@Override
	public String toString() {
		return "BGManufactureInfo [manufactureName=" + manufactureName
				+ ", orderNumber=" + orderNumber + ", discriptorText="
				+ discriptorText + ", lstBgDataForMfg=" + lstBgDataForMfg + "]";
	}
	
}
