package com.dehinsystems.api.epicor.controller;


import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccitriad.catalog.CatalogException;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.BuyerAssistInfo;
import com.dehinsystems.api.epicor.model.BuyerGuildeDataVO;
import com.dehinsystems.api.epicor.service.BuyerAssistHelper;
import com.dehinsystems.api.epicor.service.ManufacturersHelper;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

@RestController
@RequestMapping("bgdetails/")
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
	/**
	 * @param supplierId
	 * @param partNumber
	 * @param manufracturer
	 * @return BuyerAssistInfo object as json 
	 */
	@RequestMapping(value = "{partNumber}", method = RequestMethod.GET, headers="Accept=application/json")
	public BuyerAssistInfo getCompatibilityInfo(@RequestParam(value= "supplierID",required=false) String supplierId ,
			@PathVariable String partNumber,@RequestParam(value= "mfg",required=true) String manufracturer ){
		
		supplierId = StringUtils.isEmpty(supplierId) ? EpicoreConstants.DEFAULT_SUPPLIER_ID : supplierId;
		
		BuyerAssistInfo buyerInfo = new BuyerAssistInfo() ;

		try {
				if (StringUtils.isEmpty(manufracturer)) {
					buyerInfo.setErrorMessage("Please enter Manufacturer name");
					return buyerInfo;
				}
			Vector<String> mfgVector = new Vector<String>();
			mfgVector.add(manufracturer);
			
			BuyerAssistHelper buyerAssistHelper = new BuyerAssistHelper();
			buyerInfo = buyerAssistHelper.getBuyerAssistAllMfg(partNumber,mfgVector,supplierId);
			
			
		} catch (CatalogException | IOException e) {
				buyerInfo.setErrorMessage("Something wrong with ePartExpert Connections. Please try again later-"+ e.getMessage());
		}
		
		return buyerInfo;
	}
}
