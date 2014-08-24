package com.dehinsystems.api.epicor.model;

public class VehicleInfo {

	private String vinCode;
	private String errorCode;
	private String vinPattern;
	private String yearText;
	private String makeText;
	private String makeCode;
	private String vehicleModelName;
	private Integer vehicleModelCode;
	private String vehicleEngineName;
	private Integer vehicleEngineCode;
	//private String specificaConditionAnswerText;
	//private String specificaConditionAnswerCode;
	//private String specificaConditionAnswerAnswer;
	private String errorMessage;

	public String getVinCode() {
		return vinCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getVinPattern() {
		return vinPattern;
	}

	public String getYearText() {
		return yearText;
	}

	public String getMakeText() {
		return makeText;
	}

	public String getMakeCode() {
		return makeCode;
	}

	public String getVehicleModelName() {
		return vehicleModelName;
	}

	public Integer getVehicleModelCode() {
		return vehicleModelCode;
	}

	public String getVehicleEngineName() {
		return vehicleEngineName;
	}

	public Integer getVehicleEngineCode() {
		return vehicleEngineCode;
	}

/*	public String getSpecificaConditionAnswerText() {
		return specificaConditionAnswerText;
	}

	public String getSpecificaConditionAnswerCode() {
		return specificaConditionAnswerCode;
	}

	public String getSpecificaConditionAnswerAnswer() {
		return specificaConditionAnswerAnswer;
	}*/
	
	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setVinPattern(String vinPattern) {
		this.vinPattern = vinPattern;
	}

	public void setYearText(String yearText) {
		this.yearText = yearText;
	}

	public void setMakeText(String makeText) {
		this.makeText = makeText;
	}

	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}

	public void setVehicleModelName(String vehicleModelName) {
		this.vehicleModelName = vehicleModelName;
	}

	public void setVehicleModelCode(Integer vehicleModelCode) {
		this.vehicleModelCode = vehicleModelCode;
	}

	public void setVehicleEngineName(String vehicleEngineName) {
		this.vehicleEngineName = vehicleEngineName;
	}

	public void setVehicleEngineCode(Integer vehicleEngineCode) {
		this.vehicleEngineCode = vehicleEngineCode;
	}

	@Override
	public String toString() {
		return "VehicleInfo [vinCode=" + vinCode + ", errorCode=" + errorCode
				+ ", vinPattern=" + vinPattern + ", yearText=" + yearText
				+ ", makeText=" + makeText + ", makeCode=" + makeCode
				+ ", vehicleModelName=" + vehicleModelName
				+ ", vehicleModelCode=" + vehicleModelCode
				+ ", vehicleEngineName=" + vehicleEngineName
				+ ", vehicleEngineCode=" + vehicleEngineCode + "]";
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/*public void setSpecificaConditionAnswerText(
			String specificaConditionAnswerText) {
		this.specificaConditionAnswerText = specificaConditionAnswerText;
	}

	public void setSpecificaConditionAnswerCode(
			String specificaConditionAnswerCode) {
		this.specificaConditionAnswerCode = specificaConditionAnswerCode;
	}

	public void setSpecificaConditionAnswerAnswer(
			String specificaConditionAnswerAnswer) {
		this.specificaConditionAnswerAnswer = specificaConditionAnswerAnswer;
	}*/
	
}
