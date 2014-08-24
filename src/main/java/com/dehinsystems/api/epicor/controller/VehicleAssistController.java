package com.dehinsystems.api.epicor.controller;


import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccitriad.catalog.CatalogException;
import com.dehinsystems.api.epicor.model.VehicleInfo;
import com.dehinsystems.api.epicor.service.VehicleHelper;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

@RestController
@RequestMapping("/vehicleassist/")
public class VehicleAssistController {
   	
	@RequestMapping(value = "getvehicleinfo/{vinNumber}", method = RequestMethod.GET, headers="Accept=application/json")
	public VehicleInfo getVehicleInfo(@RequestParam(value = "supplierID", required = false) String supplierID, @PathVariable String vinNumber) {
		supplierID = StringUtils.isEmpty(supplierID) ? EpicoreConstants.DEFAULT_SUPPLIER_ID : supplierID;
		
		VehicleInfo vehicleInfo = new VehicleInfo();
		if(StringUtils.isEmpty(vinNumber)) {
			vehicleInfo.setErrorMessage("Please provide correct Part Number");
			return vehicleInfo;
		}
		
		VehicleHelper vehicleHelper = new VehicleHelper();
		try {
			vehicleInfo = vehicleHelper.getVehicleInfoFromVIN(vinNumber, supplierID);
		} catch (CatalogException | IOException e) {
			if(e instanceof IOException) {
				vehicleInfo.setErrorMessage("Something wrong with ePartExpert Connections. Please try again later.");
				return vehicleInfo;
			}
			if(e instanceof CatalogException) {
				vehicleInfo.setErrorMessage("Something wrong with ePartExpert System. Please report this to administrator - "+e.getMessage());
			}
		} finally {
			vehicleHelper = null;
		}
		return vehicleInfo;
	}
}
