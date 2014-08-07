/**
 * 
 */
package com.dehinsystems.api.epicor.model;

import java.util.Vector;

/**
 * @author tgbaxi
 *
 */
public class MfgLineCodeOrderNoInfo {
	
	private Vector<?> mfgName;
	
	private Vector<?> lineCodes;
	
	private Vector<?> orderNumbers;

	public Vector<?> getMfgName() {
		return mfgName;
	}

	public void setMfgName(Vector<?> mfgName) {
		this.mfgName = mfgName;
	}

	public Vector<?> getLineCodes() {
		return lineCodes;
	}

	public void setLineCodes(Vector<?> lineCodes) {
		this.lineCodes = lineCodes;
	}

	public Vector<?> getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(Vector<?> orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
}
