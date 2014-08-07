/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.List;

/**
 * @author Harshil
 *
 */
public class BuyerGuildeDataVO {
	
	private String partNumber;
	
	private List<BGManufactureInfo> lstBgMfgInfos;
	
	private LocalCover2Cover cover2CoverInfo;
	
	private String supplierID;
	
	private String errorMessage;

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public List<BGManufactureInfo> getLstBgMfgInfos() {
		return lstBgMfgInfos;
	}

	public void setLstBgMfgInfos(List<BGManufactureInfo> lstBgMfgInfos) {
		this.lstBgMfgInfos = lstBgMfgInfos;
	}

	public LocalCover2Cover getCover2CoverInfo() {
		return cover2CoverInfo;
	}

	public void setCover2CoverInfo(LocalCover2Cover cover2CoverInfo) {
		this.cover2CoverInfo = cover2CoverInfo;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "BuyerGuildeDataVO [partNumber=" + partNumber
				+ ", lstBgMfgInfos=" + lstBgMfgInfos + ", cover2CoverInfo="
				+ cover2CoverInfo + "]";
	}

}
