package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.buyersGuide.BGCatalog;
import com.ccitriad.catalog.buyersGuide.BGDataItem;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.CompatibilityInfo;
import com.dehinsystems.api.epicor.model.ManufacturerDetails;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

public class BuyerAssistHelper {
	public static void main(String[] args) throws CatalogException, IOException {

		System.out.println("=====BuyerAssistData=======");
		BuyerAssistHelper assistHelper = new BuyerAssistHelper();
		assistHelper.getManufactures("EM-9045");
		List<ManufacturerDetails> details = assistHelper.getBuyerAssistData("EM9045", "AUTOEXTRA/STANDARD MOTOR PRODUCTS");
		for(ManufacturerDetails manufacturerDetails: details){
			System.out.println(manufacturerDetails.toString());
		}
	}

	public List<String> getManufactures(String partNumber)
			throws CatalogException, IOException {
		ManufacturersHelper manufacturersHelper = new ManufacturersHelper();
		List<String> mfgList = new ArrayList<String>();
		List<BGManufactureInfo> manufactureInfos = manufacturersHelper
				.getAllManufacturerByPartNumber(
						EpicoreConstants.DEFAULT_SUPPLIER_ID, partNumber);
		for (BGManufactureInfo bgManufactureInfo : manufactureInfos) {
			mfgList.add(bgManufactureInfo.getManufactureName());
			System.out.println(bgManufactureInfo.getManufactureName());
		}
		return mfgList;
	}



	public List<ManufacturerDetails> getBuyerAssistData(String partNumber, String manufacturer) throws CatalogException, IOException{
 		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,EpicoreConstants.DEFAULT_SUPPLIER_ID, EpicoreConstants.SERVICE_TYPE);
		List<?> bgcataloglist = bgCatalog.GetBuyersGuideData(partNumber, manufacturer);
		List<CompatibilityInfo> bgDataInfos = new ArrayList<CompatibilityInfo>();
		List<ManufacturerDetails> mfgList = new ArrayList<ManufacturerDetails>(0);
		ManufacturerDetails mfgDetails = null;
		for (Object object : bgcataloglist) {
			if (object instanceof BGDataItem) {
				BGDataItem dataItemEnhanced = (BGDataItem) object;
				/**
				 * This would remove repetitive data coming from response
				 */
				if(mfgDetails == null) {
					mfgDetails = new ManufacturerDetails();
					mfgDetails.setManufacturer(dataItemEnhanced.getManufacturerName());
					mfgDetails.setGroupText(dataItemEnhanced.getGroupText());
					mfgDetails.setGroupNumber(dataItemEnhanced.getGroupNumber());
					mfgDetails.setPartDescText(dataItemEnhanced.getPartDescriptionText());
					mfgDetails.setPartDescID(dataItemEnhanced.getPartDescriptionID());
					mfgDetails.setLineCode(dataItemEnhanced.getLineCode());
					mfgDetails.setOrderNumber(dataItemEnhanced.getOrderNumber());
				}

				CompatibilityInfo dataInfo = populateBGData(dataItemEnhanced);
				bgDataInfos.add(dataInfo);
				mfgList.add(mfgDetails);
			}
		}
		if(mfgDetails == null) {
			mfgDetails = new ManufacturerDetails();
		}
		mfgDetails.setCompatibilityInfos(bgDataInfos);
		bgCatalog.DisconnectCatalogServer();
		return mfgList;
	}
	/**
	 * @param dataItemEnhanced
	 * @return
	 */
	private CompatibilityInfo populateBGData(BGDataItem dataItemEnhanced) {
		CompatibilityInfo dataInfo = new CompatibilityInfo();
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setDetailsText(dataItemEnhanced.getDetailsText());
		dataInfo.setEngineText(dataItemEnhanced.getEngineOfVehicle());
		dataInfo.setFromYear(dataItemEnhanced.getFromYear());
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setLineDescription(dataItemEnhanced.getLineDescription());
		dataInfo.setMakeText(dataItemEnhanced.getMakeOfVehicle());
		dataInfo.setModelText(dataItemEnhanced.getModelOfVehicle());
		dataInfo.setNumApps(dataItemEnhanced.getNumberOfApplication());
		dataInfo.setPerCarQty(dataItemEnhanced.getPerVehicleQuantity());
		dataInfo.setToYear(dataItemEnhanced.getToYear());
		return dataInfo;
	}
}
