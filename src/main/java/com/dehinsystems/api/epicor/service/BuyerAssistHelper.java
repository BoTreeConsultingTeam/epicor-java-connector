package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.buyersGuide.BGCatalog;
import com.ccitriad.catalog.buyersGuide.BGDataItemEnhanced;
import com.ccitriad.catalog.parts.LocalC2C;
import com.ccitriad.catalog.parts.PartCatalog;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.BuyerAssistInfo;
import com.dehinsystems.api.epicor.model.CompatibilityInfo;
import com.dehinsystems.api.epicor.util.EpicoreConstants;
import com.dehinsystems.api.epicor.util.HtmlScrapper;

public class BuyerAssistHelper {

	/**
	 * @param partNumber
	 * @param manufracturer
	 * @return List of {@link BuyerAssistInfo}
	 * @throws IOException 
	 * @throws CatalogException 
	 * The method returns list of manufracturer details for given partnumber and manufracturer.
	 */
	public BuyerAssistInfo getBuyerAssistData(String partNumber,String manufracturer) throws CatalogException, IOException {
		
		BuyerAssistHelper assistHelper = new BuyerAssistHelper();
		
		Vector<String> mfgVector = new Vector<String>();
		
		BuyerAssistInfo buyerAssistInfo = new BuyerAssistInfo();
		
			List<String> mfgList = assistHelper.getManufactures(partNumber);
			for (String mfgName : mfgList) {
				if (mfgName.trim().equalsIgnoreCase(manufracturer.trim())) {
					mfgVector.add(mfgName.trim());
					buyerAssistInfo = assistHelper.getBuyerAssistAllMfg(partNumber, mfgVector);
					break;
				}
			}
		return buyerAssistInfo;

	}

	/**
	 * @param partNumber
	 * @return list of manufracturer's name
	 * @throws IOException 
	 * @throws CatalogException 
	 * This returns list of all manufracturer for given partnumber.
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
	 * @return list of {@link BuyerAssistInfo}
	 * @throws CatalogException
	 * @throws IOException
	 * The method is used to get buyerassist data of all manufracturers for given  partnumber.
	 */
	public BuyerAssistInfo getBuyerAssistAllMfg(String partNumber,Vector<String> manufacturer) throws CatalogException, IOException {
		
		PartCatalog partCatalog = new PartCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,EpicoreConstants.DEFAULT_SUPPLIER_ID, EpicoreConstants.SERVICE_TYPE);
		LocalC2C localC2C = partCatalog.GraphicLocalC2CRequest(partNumber, null, null, null);
		List<String> imageUrls = HtmlScrapper.fetchImageUrls(localC2C.getC2CURL());
		partCatalog.DisconnectCatalogServer();
		
		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP,EpicoreConstants.DOMAIN_ID, EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,EpicoreConstants.DEFAULT_SUPPLIER_ID,EpicoreConstants.SERVICE_TYPE);
		List<CompatibilityInfo> bgDataInfoList = new ArrayList<CompatibilityInfo>();
		
		List<?> bgcataloglist = bgCatalog.GetBuyersGuideDataEnhanced(partNumber, manufacturer);
		List<BuyerAssistInfo> buyerAssistList = new ArrayList<BuyerAssistInfo>();
		
		BuyerAssistInfo buyerAssistInfo = null;
		
		buyerAssistInfo = saveBuyerAssisInfo(imageUrls, bgDataInfoList,
				bgcataloglist, buyerAssistList, buyerAssistInfo);
		bgCatalog.DisconnectCatalogServer();
		return buyerAssistInfo;
	}

	private BuyerAssistInfo saveBuyerAssisInfo(List<String> imageUrls,
			List<CompatibilityInfo> bgDataInfoList, List<?> bgcataloglist,
			List<BuyerAssistInfo> buyerAssistList,
			BuyerAssistInfo buyerAssistInfo) {
		

		CompatibilityInfo bgDataInfos;
		for (Object object : bgcataloglist) {
			
			if (object instanceof BGDataItemEnhanced) {  
				
				BGDataItemEnhanced dataItemEnhanced = (BGDataItemEnhanced) object;
				/**
				 * This would remove repetitive data coming from response
				 */
				if (buyerAssistInfo == null) {
					buyerAssistInfo = new BuyerAssistInfo();
					buyerAssistInfo.setManufacturer(StringUtils.trimToEmpty(dataItemEnhanced.getManufacturerName()));
					buyerAssistInfo.setGroupText(StringUtils.trimToEmpty(dataItemEnhanced.getGroupText()));
					buyerAssistInfo.setGroupNumber(StringUtils.trimToEmpty(dataItemEnhanced.getGroupNumber()));
					buyerAssistInfo.setPartDescText(StringUtils.trimToEmpty(dataItemEnhanced.getPartDescriptionText()));
					buyerAssistInfo.setPartDescID(StringUtils.trimToEmpty(dataItemEnhanced.getPartDescriptionID()));
					buyerAssistInfo.setLineCode(StringUtils.trimToEmpty(dataItemEnhanced.getLineCode()));
					buyerAssistInfo.setOrderNumber(StringUtils.trimToEmpty(dataItemEnhanced.getOrderNumber()));
				}
				
				bgDataInfos = populateBGData(dataItemEnhanced);
				bgDataInfoList.add(bgDataInfos);
				
				buyerAssistInfo.setCompatibilityInfo(bgDataInfoList);
				buyerAssistInfo.setImageUrls(imageUrls);
				buyerAssistList.add(buyerAssistInfo);
			}
		}
		return buyerAssistInfo;
	}

	/**
	 * @param dataItemEnhanced
	 * @return object of {@link CompatibilityInfo}
	 */
	private CompatibilityInfo populateBGData(BGDataItemEnhanced dataItemEnhanced) {
		
		CompatibilityInfo dataInfo = new CompatibilityInfo();
		
		dataInfo.setFromYear(StringUtils.trimToEmpty(dataItemEnhanced.getFromYear()));
		dataInfo.setToYear(StringUtils.trimToEmpty(dataItemEnhanced.getToYear()));
		dataInfo.setNumApps(StringUtils.trimToEmpty(dataItemEnhanced.getNumberOfApplication()));
		dataInfo.setPerCarQty(StringUtils.trimToEmpty(dataItemEnhanced.getPerVehicleQuantity()));
		dataInfo.setAddDate(StringUtils.trimToEmpty(dataItemEnhanced.getAddDate()));
		dataInfo.setMakeText(StringUtils.trimToEmpty(dataItemEnhanced.getMakeOfVehicle()));
		dataInfo.setMakeCode(StringUtils.trimToEmpty(dataItemEnhanced.getMakeCodeOfVehicle()));
		dataInfo.setModelText(StringUtils.trimToEmpty(dataItemEnhanced.getModelOfVehicle()));
		dataInfo.setModelCode(StringUtils.trimToEmpty(dataItemEnhanced.getModelCodeOfVehicle()));
		dataInfo.setEngineText(StringUtils.trimToEmpty(dataItemEnhanced.getEngineOfVehicle()));
		dataInfo.setEngineCode(StringUtils.trimToEmpty(dataItemEnhanced.getEngineCodeOfVehicle()));
		dataInfo.setSpecCode1(StringUtils.trimToEmpty(dataItemEnhanced.getSpecCode1()));
		dataInfo.setSpecCode2(StringUtils.trimToEmpty(dataItemEnhanced.getSpecCode2()));
		dataInfo.setSpecCode3(StringUtils.trimToEmpty(dataItemEnhanced.getSpecCode3()));
		dataInfo.setSpecCode4(StringUtils.trimToEmpty(dataItemEnhanced.getSpecCode4()));
		dataInfo.setDetailsText(StringUtils.trimToEmpty(dataItemEnhanced.getDetailsText()));
		dataInfo.setLineDescription(StringUtils.trimToEmpty(dataItemEnhanced.getLineDescription()));
		return dataInfo;
	}
	

}
