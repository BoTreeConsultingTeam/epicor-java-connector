/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.List;

/**
 * @author
 *
 */
public class ManufacturerItem {
	
	private String LineCodes;
	
	private String OrderNumber;
	
	private List<PartRecordDetails> interItems;

	/**
	 * @return the lineCodes
	 */
	public String getLineCodes() {
		return LineCodes;
	}

	/**
	 * @param lineCodes the lineCodes to set
	 */
	public void setLineCodes(String lineCodes) {
		LineCodes = lineCodes;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return OrderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public List<PartRecordDetails> getInterItems() {
		return interItems;
	}

	public void setInterItems(List<PartRecordDetails> interItems) {
		this.interItems = interItems;
	}
	
	

}
