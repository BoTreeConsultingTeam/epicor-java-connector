package com.dehinsystems.api.epicor.model;

public class PartRecordDetails {

	private String description;

	private String extendedDescription;

	private String partNumber;

	private String uncompressedPartNumber;

	private String mfgName;

	private String years;

	private String lineCodes;

	private String catalogLineCode;

	private String genericDescription;

	private LocalCover2Cover localCover2Cover;
	
	private String listPrice;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the extendedDescription
	 */
	public String getExtendedDescription() {
		return extendedDescription;
	}

	/**
	 * @param extendedDescription
	 *            the extendedDescription to set
	 */
	public void setExtendedDescription(String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}

	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * @param partNumber
	 *            the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @return the uncompressedPartNumber
	 */
	public String getUncompressedPartNumber() {
		return uncompressedPartNumber;
	}

	/**
	 * @param uncompressedPartNumber
	 *            the uncompressedPartNumber to set
	 */
	public void setUncompressedPartNumber(String uncompressedPartNumber) {
		this.uncompressedPartNumber = uncompressedPartNumber;
	}

	/**
	 * @return the mfgName
	 */
	public String getMfgName() {
		return mfgName;
	}

	/**
	 * @param mfgName
	 *            the mfgName to set
	 */
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}

	/**
	 * @return the years
	 */
	public String getYears() {
		return years;
	}

	/**
	 * @param years
	 *            the years to set
	 */
	public void setYears(String years) {
		this.years = years;
	}

	/**
	 * @return the lineCodes
	 */
	public String getLineCodes() {
		return lineCodes;
	}

	/**
	 * @param lineCodes
	 *            the lineCodes to set
	 */
	public void setLineCodes(String lineCodes) {
		this.lineCodes = lineCodes;
	}

	/**
	 * @return the catalogLineCode
	 */
	public String getCatalogLineCode() {
		return catalogLineCode;
	}

	/**
	 * @param catalogLineCode
	 *            the catalogLineCode to set
	 */
	public void setCatalogLineCode(String catalogLineCode) {
		this.catalogLineCode = catalogLineCode;
	}

	/**
	 * @return the genericDescription
	 */
	public String getGenericDescription() {
		return genericDescription;
	}

	/**
	 * @param genericDescription
	 *            the genericDescription to set
	 */
	public void setGenericDescription(String genericDescription) {
		this.genericDescription = genericDescription;
	}

	/**
	 * 
	 * @return
	 */
	public LocalCover2Cover getLocalCover2Cover() {
		return localCover2Cover;
	}

	/**
	 * 
	 * @param localCover2Cover
	 */
	public void setLocalCover2Cover(LocalCover2Cover localCover2Cover) {
		this.localCover2Cover = localCover2Cover;
	}

	public String getListPrice() {
		return listPrice;
	}

	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}

}
