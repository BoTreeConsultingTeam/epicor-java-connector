package com.dehinsystems.api.epicor.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccitriad.catalog.CatalogException;
import com.dehinsystems.api.epicor.model.BGManufactureInfo;
import com.dehinsystems.api.epicor.model.BuyerGuildeDataVO;
import com.dehinsystems.api.epicor.model.LocalCover2Cover;
import com.dehinsystems.api.epicor.service.C2CHelper;
import com.dehinsystems.api.epicor.service.ManufacturersHelper;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

@RestController
@RequestMapping("/bgdetails/")
public class BuyerAssistController {
   	
	@RequestMapping(value = "srchbgdata/{partNumber}/{supplierID}/", method = RequestMethod.GET, headers="Accept=application/json")
	public BuyerGuildeDataVO getBuyerAssistDetails(@PathVariable String supplierID, @PathVariable String partNumber) {
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
			 * Step 1,2: Fetch all the manufacturers and there coverage for given Part Number 
			*/
			List<BGManufactureInfo> manufacturers = manufacturersHelper.getAllManufacturerByPartNumber(supplierID, partNumber);
			bgDataVO.setLstBgMfgInfos(manufacturers);
			/**
			 * Step 3: Fetch C2C information
			 */
			C2CHelper c2cHelper = new C2CHelper();
			LocalCover2Cover cover2Cover = c2cHelper.getCoverToCoverDetailsFromPartNumber(supplierID, partNumber);
			bgDataVO.setCover2CoverInfo(cover2Cover);
		} catch (CatalogException | IOException e) {
			if(e instanceof IOException) {
				bgDataVO.setErrorMessage("Something wrong with ePartExpert Connections. Please try again later.");
				return bgDataVO;
			}
			if(e instanceof CatalogException) {
				bgDataVO.setErrorMessage("Something wrong with ePartExpert System. Please report this to administrator - "+e.getMessage());
			}
		}
		return bgDataVO;
	}
    
	public static void main(String[] args) {
		BuyerAssistController buyerAssistController = new BuyerAssistController();
		BuyerGuildeDataVO bgDataVO = buyerAssistController.getBuyerAssistDetails("TS", "EM-9045");
		System.out.println(bgDataVO);
	}
}
