/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.List;

/**
 * @author
 *
 */
public class CatalogItem {
	
	private String partNumber;
	
	private List<ManufacturerItem> manufacturerItems;
	
	private String errorMessage;

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public List<ManufacturerItem> getManufacturerItems() {
		return manufacturerItems;
	}

	public void setManufacturerItems(List<ManufacturerItem> manufacturerItems) {
		this.manufacturerItems = manufacturerItems;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
