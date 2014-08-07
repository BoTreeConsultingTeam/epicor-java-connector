package com.dehinsystems.api.epicor.model;

public class ManufactureInfo {
	
	private String mfgName;
	
	private String totalCoverage;
	
	private String usedCoverage;

	public String getTotalCoverage() {
		return totalCoverage; 
	}

	public void setTotalCoverage(String totalCoverage) {
		this.totalCoverage = totalCoverage;
	}

	public String getUsedCoverage() {
		return usedCoverage;
	}

	public void setUsedCoverage(String usedCoverage) {
		this.usedCoverage = usedCoverage;
	}

	public String getMfgName() {
		return mfgName;
	}

	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}

	@Override
	public String toString() {
		return "ManufactureInfo [mfgName=" + mfgName + ", totalCoverage="
				+ totalCoverage + ", usedCoverage=" + usedCoverage + "]";
	}
	
	

}
