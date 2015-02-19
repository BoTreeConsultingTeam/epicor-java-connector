package com.dehinsystems.api.epicor.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccitriad.catalog.CatalogException;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.BuyerGuildeDataVO;
import com.dehinsystems.api.epicor.model.CatalogItem;
import com.dehinsystems.api.epicor.model.ManufacturerDetails;
import com.dehinsystems.api.epicor.service.BuyerAssistHelper;
import com.dehinsystems.api.epicor.service.ManufacturersHelper;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

@RestController
@RequestMapping("/bgdetails/")
public class BuyerAssistController {
   	
	@RequestMapping(value = "srchbgdata/{partNumber}", method = RequestMethod.GET, headers="Accept=application/json")
	public BuyerGuildeDataVO getBuyerAssistDetails(@RequestParam(value = "supplierID", required = false) String supplierID, @PathVariable String partNumber) {
		supplierID = StringUtils.isEmpty(supplierID) ? EpicoreConstants.DEFAULT_SUPPLIER_ID : supplierID;
		BuyerGuildeDataVO bgDataVO = new BuyerGuildeDataVO();
		bgDataVO.setPartNumber(partNumber);
		bgDataVO.setSupplierID(supplierID);
		if(StringUtils.isEmpty(partNumber)) {
			bgDataVO.setErrorMessage("Please provide correct Part Number");
			return bgDataVO;
		}
		
		ManufacturersHelper manufacturersHelper = new ManufacturersHelper();
		try {
			/**
			 * Step 1,2,3: Fetch all the manufacturers and there coverage for given Part Number, C2C information 
			*/
			List<BGManufactureInfo> manufacturers = manufacturersHelper.getAllManufacturerByPartNumber(supplierID, partNumber);
			bgDataVO.setLstBgMfgInfos(manufacturers);
		} catch (CatalogException | IOException e) {
			if(e instanceof IOException) {
				bgDataVO.setErrorMessage("Something wrong with ePartExpert Connections. Please try again later.");
				return bgDataVO;
			}
			if(e instanceof CatalogException) {
				bgDataVO.setErrorMessage("Something wrong with ePartExpert System. Please report this to administrator - "+e.getMessage());
			}
		} finally {
			manufacturersHelper = null;
		}
		return bgDataVO;
	}
	
	@RequestMapping(value = "{partNumber}/{manufracturer}", method = RequestMethod.GET, headers="Accept=application/json")
	public List<ManufacturerDetails> getBuyerAssisData(@RequestParam(value = "supplierID", required = false) String supplierID, @PathVariable String partNumber,String manufracturer){
		
		supplierID = StringUtils.isEmpty(supplierID) ? EpicoreConstants.DEFAULT_SUPPLIER_ID : supplierID;
		List<ManufacturerDetails> mfgDetails = new ArrayList<ManufacturerDetails>();;
	
		
		BuyerAssistHelper buyerAssistHelper = new BuyerAssistHelper();
		try {
			mfgDetails = buyerAssistHelper.getBuyerAssistData(partNumber, manufracturer);
		} catch (CatalogException | IOException e) {
			
			if(e instanceof IOException || e instanceof CatalogException) {
				ManufacturerDetails manufacturerDetails = new ManufacturerDetails();
				manufacturerDetails.setErrorMessage("Something wrong with ePartExpert Connections. Please try again later");
				mfgDetails.add(manufacturerDetails);
			}
		}
		return mfgDetails;
		
	}
}
