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
import com.ccitriad.catalog.mfgPrices.MfgPricesCatalog;
import com.ccitriad.catalog.mfgPrices.MfgPricesItem;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.CompatibilityInfo;
import com.dehinsystems.api.epicor.model.LocalCover2Cover;
import com.dehinsystems.api.epicor.model.ManufactureInfo;
import com.dehinsystems.api.epicor.model.ManufacturerDetails;
import com.dehinsystems.api.epicor.model.PricePartInfo;
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
	public List<BGManufactureInfo> getAllManufacturerByPartNumber(final String supplierID, final String partNumber) throws CatalogException, IOException {
		List<BGManufactureInfo> bgManufactureInfos = new ArrayList<BGManufactureInfo>();
		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		/**
		 * Step 1: Get all the manufacturers for the provided part number.
		 */
		List<?> mfgList = bgCatalog.GetBuyersGuideAllLinesManufacturer(partNumber, EpicoreConstants.DEFAULT_DATE);
		for(Object object:mfgList) {
			if(object instanceof BGManufacturer){
				BGManufacturer manufacturer = (BGManufacturer) object;
				BGManufactureInfo bgManufactureInfo = populateManufactureByPartNumber(manufacturer);
				/**
				 * Step 2: Fetch coverage for each manufacturer.
				 */
				ManufacturerDetails mfgDetails = getBGDataWithMfg(supplierID, partNumber, bgManufactureInfo.getOrderNumber());
				/**
				 * Step 3: Fetch C2C information
				 */
				C2CHelper c2cHelper = new C2CHelper();
				LocalCover2Cover cover2Cover = c2cHelper.getCoverToCoverDetailsFromPartNumber(supplierID, partNumber, mfgDetails.getManufacturer(), mfgDetails.getLineCode(), mfgDetails.getOrderNumber());
				mfgDetails.setLocalCover2Cover(cover2Cover);
				
				/**
				 * Step 4: Pricing information
				 */
				List<PricePartInfo> pricePartInfos = getPricesByMfgNameAndPartNumber(supplierID, mfgDetails.getManufacturer(), partNumber); 
				mfgDetails.setPricePartInfos(pricePartInfos);
				bgManufactureInfo.setMfgDetails(mfgDetails);
				
				/**
				 * Adding the Manufacturer Information in the list.
				 */
				bgManufactureInfos.add(bgManufactureInfo);
			}
		}
		bgCatalog.DisconnectCatalogServer();
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
	
	public ManufacturerDetails getBGDataWithMfg(final String supplierID, final String partNumber, final String orderNumber) throws CatalogException, IOException{
 		BGCatalog bgCatalog = new BGCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		List<?> bgcataloglist = bgCatalog.GetBuyersGuideDataByVehEnhanced("0", "0", "0", "0", "0", null, partNumber, null, orderNumber);
		List<CompatibilityInfo> bgDataInfos = new ArrayList<CompatibilityInfo>();
		ManufacturerDetails mfgDetails = null;
		for (Object object : bgcataloglist) {
			if (object instanceof BGDataItemEnhanced) {
				BGDataItemEnhanced dataItemEnhanced = (BGDataItemEnhanced) object;
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
			}
		}
		if(mfgDetails == null) {
			mfgDetails = new ManufacturerDetails();
		}
		mfgDetails.setCompatibilityInfos(bgDataInfos);
		bgCatalog.DisconnectCatalogServer();
		return mfgDetails;
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
	
	public List<PricePartInfo> getPricesByMfgNameAndPartNumber(final String supplierID, final String mfgName, final String partNumber) throws UnknownHostException, IOException {
		List<PricePartInfo> pricePartInfos = new ArrayList<PricePartInfo>(0);
		MfgPricesCatalog pricesCatalog = null;
		try{
			pricesCatalog = new MfgPricesCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID, EpicoreConstants.USER_NAME, EpicoreConstants.PASSWORD, supplierID, EpicoreConstants.SERVICE_TYPE);
			List<?> items = pricesCatalog.MfgPricesInfo(mfgName, partNumber);
			
			for (Object object : items) {
				if (object instanceof MfgPricesItem) {
					MfgPricesItem mp = (MfgPricesItem) object;
					PricePartInfo pricePartInfo = populatePrice(mp);
					pricePartInfos.add(pricePartInfo);
				}
			}
		} catch(CatalogException e) {
			PricePartInfo pricePartInfo = new PricePartInfo();
			pricePartInfo.setErrorMessage(e.getMessage());
			pricePartInfos.add(pricePartInfo);
		} finally{
			if(pricesCatalog != null) {
				try {
					pricesCatalog.DisconnectCatalogServer();
				} catch (CatalogException e) {
					PricePartInfo pricePartInfo = new PricePartInfo();
					pricePartInfo.setErrorMessage(e.getMessage());
					pricePartInfos.add(pricePartInfo);
				}
			}
			
		}
		return pricePartInfos;
	}

	private PricePartInfo populatePrice(MfgPricesItem mp) {
		PricePartInfo partInfo = new PricePartInfo();
		partInfo.setPriceCode(mp.getPriceCode());
		partInfo.setPriceSheet(mp.getPriceSheet());
		partInfo.setPriceSheetColor(mp.getPriceSheetColor());
		partInfo.setPriceType(mp.getPriceType());
		partInfo.setQuantityPrice(mp.getQuantityPrice());
		partInfo.setUnitPrice(mp.getUnitPrice());
		return partInfo;
	}
}
