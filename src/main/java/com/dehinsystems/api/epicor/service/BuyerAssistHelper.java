package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.buyersGuide.BGCatalog;
import com.ccitriad.catalog.buyersGuide.BGDataItemEnhanced;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.CompatibilityInfo;
import com.dehinsystems.api.epicor.model.ManufacturerDetails;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

public class BuyerAssistHelper {
	public static void main(String[] args) throws CatalogException, IOException {

		BuyerAssistHelper assistHelper = new BuyerAssistHelper();
		List<ManufacturerDetails> details = new ArrayList<ManufacturerDetails>();

		details = assistHelper.getBuyerAssistData("EM9045", "CARQUEST/STANDARD MOTOR PRODUCTS");
		
		for(ManufacturerDetails mfgDetails:details){
			System.out.println(mfgDetails.toString());
		}
	}

	/**
	 * @param partNumber
	 * @param manufracturer
	 * @return
	 * @throws IOException 
	 * @throws CatalogException 
	 */
	public List<ManufacturerDetails> getBuyerAssistData(String partNumber,String manufracturer) throws CatalogException, IOException {
		
		BuyerAssistHelper assistHelper = new BuyerAssistHelper();
		
		Vector<String> mfgVector = new Vector<String>();
		
		List<ManufacturerDetails> buyerDetailList = new ArrayList<ManufacturerDetails>();
		
			List<String> mfgList = assistHelper.getManufactures(partNumber);
			for (String mfgName : mfgList) {
				if (mfgName.trim().equalsIgnoreCase(manufracturer.trim())) {
					mfgVector.add(mfgName.trim());
					buyerDetailList = assistHelper.getBuyerAssistAllMfg(partNumber, mfgVector);
					break;
				}
			}
		return buyerDetailList;

	}

	/**
	 * @param partNumber
	 * @return
	 * @throws IOException 
	 * @throws CatalogException 
	 */
	public List<String> getManufactures(String partNumber) throws CatalogException, IOException {
		ManufacturersHelper manufacturersHelper = new ManufacturersHelper();
		List<String> mfgList = new ArrayList<String>();
		List<BGManufactureInfo> manufactureInfos;
			manufactureInfos = manufacturersHelper.getAllManufacturerByPartNumber(EpicoreConstants.DEFAULT_SUPPLIER_ID, partNumber);
			
			for (BGManufactureInfo bgManufactureInfo : manufactureInfos) {
				mfgList.add(bgManufactureInfo.getManufactureName());
			}

		return mfgList;
	}

	/**
	 * @param partNumber
	 * @param manufacturer
	 * @return
	 * @throws CatalogException
	 * @throws IOException
	 */
	public List<ManufacturerDetails> getBuyerAssistAllMfg(String partNumber,Vector<String> manufacturer) throws CatalogException, IOException {
		
		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP,EpicoreConstants.DOMAIN_ID, EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,EpicoreConstants.DEFAULT_SUPPLIER_ID,EpicoreConstants.SERVICE_TYPE);
		
		List<?> bgcataloglist = bgCatalog.GetBuyersGuideDataEnhanced(partNumber, manufacturer);
		List<CompatibilityInfo> bgDataInfos = new ArrayList<CompatibilityInfo>();
		List<ManufacturerDetails> mfgList = new ArrayList<ManufacturerDetails>();
		
		ManufacturerDetails mfgDetails = null;
		
		for (Object object : bgcataloglist) {
			
			if (object instanceof BGDataItemEnhanced) {
				
				BGDataItemEnhanced dataItemEnhanced = (BGDataItemEnhanced) object;
				/**
				 * This would remove repetitive data coming from response
				 */
				mfgDetails = new ManufacturerDetails();
				mfgDetails.setManufacturer(dataItemEnhanced.getManufacturerName());
				mfgDetails.setGroupText(dataItemEnhanced.getGroupText());
				mfgDetails.setGroupNumber(dataItemEnhanced.getGroupNumber());
				mfgDetails.setPartDescText(dataItemEnhanced.getPartDescriptionText());
				mfgDetails.setPartDescID(dataItemEnhanced.getPartDescriptionID());
				mfgDetails.setLineCode(dataItemEnhanced.getLineCode());
				mfgDetails.setOrderNumber(dataItemEnhanced.getOrderNumber());

				CompatibilityInfo dataInfo = populateBGData(dataItemEnhanced);
				
				bgDataInfos.add(dataInfo);
				mfgDetails.setCompatibilityInfos(bgDataInfos);
				mfgList.add(mfgDetails);
			}
		}

		bgCatalog.DisconnectCatalogServer();
		return mfgList;
	}

	/**
	 * @param dataItemEnhanced
	 * @return
	 */
	private CompatibilityInfo populateBGData(BGDataItemEnhanced dataItemEnhanced) {
		
		CompatibilityInfo dataInfo = new CompatibilityInfo();
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setDetailsText(dataItemEnhanced.getDetailsText());
		dataInfo.setEngineCode(dataItemEnhanced.getEngineCodeOfVehicle());
		dataInfo.setEngineText(dataItemEnhanced.getEngineOfVehicle());
		dataInfo.setFromYear(dataItemEnhanced.getFromYear());
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setLineDescription(dataItemEnhanced.getLineDescription());
		dataInfo.setMakeCode(dataItemEnhanced.getMakeCodeOfVehicle());
		dataInfo.setMakeText(dataItemEnhanced.getMakeOfVehicle());
		dataInfo.setModelCode(dataItemEnhanced.getModelCodeOfVehicle());
		dataInfo.setModelText(dataItemEnhanced.getModelOfVehicle());
		dataInfo.setNumApps(dataItemEnhanced.getNumberOfApplication());
		dataInfo.setPerCarQty(dataItemEnhanced.getPerVehicleQuantity());
		dataInfo.setSpecCode1(dataItemEnhanced.getSpecCode1());
		dataInfo.setSpecCode2(dataItemEnhanced.getSpecCode2());
		dataInfo.setSpecCode3(dataItemEnhanced.getSpecCode3());
		dataInfo.setSpecCode4(dataItemEnhanced.getSpecCode4());
		dataInfo.setToYear(dataItemEnhanced.getToYear());
		return dataInfo;
	}
}
