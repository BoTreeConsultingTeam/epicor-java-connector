/**
 * 
 */
package com.dehinsystems.api.epicor.model;

/**
 * @author tgbaxi
 *
 */
public class PricePartInfo {

	private String priceSheet;
	
	private String priceSheetColor;
	
	private String priceType;
	
	private String priceCode;
	
	private String unitPrice;
	
	private String quantityPrice;

	private String errorMessage;
	
	public String getPriceSheet() {
		return priceSheet;
	}

	public void setPriceSheet(String priceSheet) {
		this.priceSheet = priceSheet;
	}

	public String getPriceSheetColor() {
		return priceSheetColor;
	}

	public void setPriceSheetColor(String priceSheetColor) {
		this.priceSheetColor = priceSheetColor;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantityPrice() {
		return quantityPrice;
	}

	public void setQuantityPrice(String quantityPrice) {
		this.quantityPrice = quantityPrice;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "PricePartInfo [priceSheet=" + priceSheet + ", priceSheetColor="
				+ priceSheetColor + ", priceType=" + priceType + ", priceCode="
				+ priceCode + ", unitPrice=" + unitPrice + ", quantityPrice="
				+ quantityPrice + ", getPriceSheet()=" + getPriceSheet()
				+ ", getPriceSheetColor()=" + getPriceSheetColor()
				+ ", getPriceType()=" + getPriceType() + ", getPriceCode()="
				+ getPriceCode() + ", getUnitPrice()=" + getUnitPrice()
				+ ", getQuantityPrice()=" + getQuantityPrice()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
