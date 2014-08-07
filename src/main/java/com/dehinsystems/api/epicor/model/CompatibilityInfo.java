package com.dehinsystems.api.epicor.model;

public class CompatibilityInfo {

	private String fromYear;
	private String toYear;
	private String numApps;
	private String perCarQty;
	private String addDate;
	private String makeText;
	private String makeCode;
	private String modelText;
	private String modelCode;
	private String engineText;
	private String engineCode;
	private String specCode1;
	private String specCode2;
	private String specCode3;
	private String specCode4;
	private String detailsText;
	private String lineDescription;
	
	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getNumApps() {
		return numApps;
	}

	public void setNumApps(String numApps) {
		this.numApps = numApps;
	}

	public String getPerCarQty() {
		return perCarQty;
	}

	public void setPerCarQty(String perCarQty) {
		this.perCarQty = perCarQty;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getMakeText() {
		return makeText;
	}

	public void setMakeText(String makeText) {
		this.makeText = makeText;
	}

	public String getMakeCode() {
		return makeCode;
	}

	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}

	public String getModelText() {
		return modelText;
	}

	public void setModelText(String modelText) {
		this.modelText = modelText;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getEngineText() {
		return engineText;
	}

	public void setEngineText(String engineText) {
		this.engineText = engineText;
	}

	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public String getSpecCode1() {
		return specCode1;
	}

	public void setSpecCode1(String specCode1) {
		this.specCode1 = specCode1;
	}

	public String getSpecCode2() {
		return specCode2;
	}

	public void setSpecCode2(String specCode2) {
		this.specCode2 = specCode2;
	}

	public String getSpecCode3() {
		return specCode3;
	}

	public void setSpecCode3(String specCode3) {
		this.specCode3 = specCode3;
	}

	public String getSpecCode4() {
		return specCode4;
	}

	public void setSpecCode4(String specCode4) {
		this.specCode4 = specCode4;
	}

	public String getDetailsText() {
		return detailsText;
	}

	public void setDetailsText(String detailsText) {
		this.detailsText = detailsText;
	}

	public String getLineDescription() {
		return lineDescription;
	}

	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}

	@Override
	public String toString() {
		return "CompatibilityInfo [fromYear=" + fromYear + ", toYear=" + toYear
				+ ", numApps=" + numApps + ", perCarQty=" + perCarQty
				+ ", addDate=" + addDate + ", makeText=" + makeText
				+ ", makeCode=" + makeCode + ", modelText=" + modelText
				+ ", modelCode=" + modelCode + ", engineText=" + engineText
				+ ", engineCode=" + engineCode + ", specCode1=" + specCode1
				+ ", specCode2=" + specCode2 + ", specCode3=" + specCode3
				+ ", specCode4=" + specCode4 + ", detailsText=" + detailsText
				+ ", lineDescription=" + lineDescription + "]";
	}
}
