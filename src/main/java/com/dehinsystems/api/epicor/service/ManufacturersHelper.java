package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.adminMCL.AdminMCLCatalog;
import com.ccitriad.catalog.adminMCL.MCLMfgListItem;
import com.ccitriad.catalog.buyersGuide.BGCatalog;
import com.ccitriad.catalog.buyersGuide.BGDataItemEnhanced;
import com.ccitriad.catalog.buyersGuide.BGManufacturer;
import com.dehinsystems.api.epicor.model.BGDataWithMfgWithCoverageInfo;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.ManufactureInfo;
import com.dehinsystems.api.epicor.util.EpicoreConstants;
public class ManufacturersHelper {
	
	
	/**
	 * 
	 * @return
	 * @throws CatalogException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public List<ManufactureInfo> getAllManufacturers(final String supplierID) throws CatalogException, UnknownHostException, IOException {
		List<ManufactureInfo> manufactureInfos = new ArrayList<ManufactureInfo>(0);
		AdminMCLCatalog adminMCLCatalog = new AdminMCLCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		List<?> manufacturerlist = adminMCLCatalog.AllManufacturers();
		for(Object object:manufacturerlist){
			if(object instanceof MCLMfgListItem){
				MCLMfgListItem mfgListItem = (MCLMfgListItem) object;
				ManufactureInfo manufactureInfo = populateMfgInfo(mfgListItem);
				manufactureInfos.add(manufactureInfo);
			}
		}
		return manufactureInfos;
	}

	/**
	 * 
	 * @param mfgListItem
	 * @return
	 */
	private ManufactureInfo populateMfgInfo(MCLMfgListItem mfgListItem) {
		ManufactureInfo manufactureInfo = new ManufactureInfo();
		manufactureInfo.setMfgName(mfgListItem.getManufacturerName());
		manufactureInfo.setTotalCoverage(mfgListItem.getTotalCoverages());
		manufactureInfo.setUsedCoverage(mfgListItem.getUsedCoverages());
		return manufactureInfo;
	}
	
	/**
	 * 
	 * @param partNumber
	 * @return
	 * @throws CatalogException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public List<BGManufactureInfo> getAllManufacturerByPartNumber(final String supplierID, final String partNumber) throws CatalogException, UnknownHostException, IOException {
			List<BGManufactureInfo> bgManufactureInfos = new ArrayList<BGManufactureInfo>();
			BGCatalog bgCatalog =new BGCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
			List<?> mfgList = bgCatalog.GetBuyersGuideAllLinesManufacturer(partNumber, EpicoreConstants.DEFAULT_DATE);
			for(Object object:mfgList){
				if(object instanceof BGManufacturer){
					BGManufacturer manufacturer = (BGManufacturer) object;
					BGManufactureInfo bgManufactureInfo = populateManufactureByPartNumber(manufacturer);
					/**
					 * Step 2: Fetch coverage for each manufacturer.
					 */
					List<BGDataWithMfgWithCoverageInfo> coverages = getBGDataWithMfg(supplierID, partNumber, bgManufactureInfo.getOrderNumber());
					bgManufactureInfo.setLstBgDataForMfg(coverages);
					
					bgManufactureInfos.add(bgManufactureInfo);
				}
			}
			return bgManufactureInfos;
	}
	
	/**
	 * 
	 * @param manufacturer
	 * @return
	 */
	private BGManufactureInfo populateManufactureByPartNumber(BGManufacturer manufacturer) {
		BGManufactureInfo bgManufactureInfo = new BGManufactureInfo();
		bgManufactureInfo.setManufactureName(manufacturer.getManufacturer());
		bgManufactureInfo.setOrderNumber(manufacturer.getOrderNumber());
		bgManufactureInfo.setDiscriptorText(manufacturer.getDescriptionText());
		return bgManufactureInfo;
	}
	
	public List<BGDataWithMfgWithCoverageInfo> getBGDataWithMfg(final String supplierID, final String partNumber, final String orderNumber) throws UnknownHostException, CatalogException, IOException{
 		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		List<?> bgcataloglist = bgCatalog.GetBuyersGuideDataByVehEnhanced("0", "0", "0", "0", "0", null, partNumber, null, orderNumber);
		List<BGDataWithMfgWithCoverageInfo> bgDataInfos = new ArrayList<BGDataWithMfgWithCoverageInfo>();
		for (Object object : bgcataloglist) {
			if (object instanceof BGDataItemEnhanced) {
				BGDataItemEnhanced dataItemEnhanced = (BGDataItemEnhanced) object;
				BGDataWithMfgWithCoverageInfo dataInfo = populateBGData(dataItemEnhanced);
				bgDataInfos.add(dataInfo);
			}

		}
		return bgDataInfos;
	}
	/**
	 * @param dataItemEnhanced
	 * @return
	 */
	private BGDataWithMfgWithCoverageInfo populateBGData(BGDataItemEnhanced dataItemEnhanced) {
		BGDataWithMfgWithCoverageInfo dataInfo = new BGDataWithMfgWithCoverageInfo();
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setDetailsText(dataItemEnhanced.getDetailsText());
		dataInfo.setEngineCode(dataItemEnhanced.getEngineCodeOfVehicle());
		dataInfo.setEngineText(dataItemEnhanced.getEngineOfVehicle());
		dataInfo.setFromYear(dataItemEnhanced.getFromYear());
		dataInfo.setAddDate(dataItemEnhanced.getAddDate());
		dataInfo.setGroupNumber(dataItemEnhanced.getGroupNumber());
		dataInfo.setGroupText(dataItemEnhanced.getGroupText());
		dataInfo.setLineCode(dataItemEnhanced.getLineCode());
		dataInfo.setLineDescription(dataItemEnhanced.getLineDescription());
		dataInfo.setMakeCode(dataItemEnhanced.getMakeCodeOfVehicle());
		dataInfo.setMakeText(dataItemEnhanced.getMakeOfVehicle());
		dataInfo.setManufacturer(dataItemEnhanced.getManufacturerName());
		dataInfo.setModelCode(dataItemEnhanced.getModelCodeOfVehicle());
		dataInfo.setModelText(dataItemEnhanced.getModelOfVehicle());
		dataInfo.setNumApps(dataItemEnhanced.getNumberOfApplication());
		dataInfo.setOrderNumber(dataItemEnhanced.getOrderNumber());
		dataInfo.setPartDescID(dataItemEnhanced.getPartDescriptionID());
		dataInfo.setPartDescText(dataItemEnhanced.getPartDescriptionText());
		dataInfo.setPerCarQty(dataItemEnhanced.getPerVehicleQuantity());
		dataInfo.setSpecCode1(dataItemEnhanced.getSpecCode1());
		dataInfo.setSpecCode2(dataItemEnhanced.getSpecCode2());
		dataInfo.setSpecCode3(dataItemEnhanced.getSpecCode3());
		dataInfo.setSpecCode4(dataItemEnhanced.getSpecCode4());
		dataInfo.setToYear(dataItemEnhanced.getToYear());
		return dataInfo;
	}
}
