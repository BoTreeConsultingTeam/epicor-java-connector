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
	
	private String supplierID;

	private List<BGManufactureInfo> lstBgMfgInfos;

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
				+ ", lstBgMfgInfos=" + lstBgMfgInfos + ", supplierID="
				+ supplierID + ", errorMessage=" + errorMessage
				+ ", getPartNumber()=" + getPartNumber()
				+ ", getLstBgMfgInfos()=" + getLstBgMfgInfos()
				+ ", getSupplierID()=" + getSupplierID()
				+ ", getErrorMessage()=" + getErrorMessage() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
