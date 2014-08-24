package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.util.Iterator;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.MasterCatalog;
import com.ccitriad.catalog.VehicleEngine;
import com.ccitriad.catalog.VehicleMake;
import com.ccitriad.catalog.VehicleModel;
import com.ccitriad.catalog.VehicleVIN;
import com.ccitriad.catalog.VehicleYear;
import com.dehinsystems.api.epicor.model.VehicleInfo;
import com.dehinsystems.api.epicor.util.EpicoreConstants;

/**
 * @author Harshil
 *
 */
public class VehicleHelper {

	private static final String DUMMY_YEAR = "2015";

	/**
	 * @param VinCode
	 * @param supplierID
	 * @return
	 * @throws CatalogException
	 * @throws IOException
	 */
	public VehicleInfo getVehicleInfoFromVIN(final String VinCode,final String supplierID) throws CatalogException, IOException {
		MasterCatalog masterCatalog = new MasterCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME, EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		VehicleVIN vin = masterCatalog.getVehicleVIN(new VehicleYear(0, DUMMY_YEAR), new VehicleMake(null, 0, EpicoreConstants.EMPTY),VinCode);
		VehicleInfo vehicleInfo = populateVehicleIn(vin);
		masterCatalog.DisconnectCatalogServer();
		return vehicleInfo;
	}

	/**
	 * @param vin
	 * @return
	 */
	private VehicleInfo populateVehicleIn(VehicleVIN vin) {
		VehicleInfo vInfo = new VehicleInfo();
		vInfo.setErrorCode(vin.getErrorCode());
		vInfo.setMakeCode(vin.getMakeCode());
		vInfo.setMakeText(vin.getMakeText());
		vInfo.setVinCode(vin.getVINCode());
		vInfo.setVinPattern(vin.getVINPattern());
		vInfo.setYearText(vin.getYearText());
		populateVehicleModelEngineSpecs(vin, vInfo);
		return vInfo;
	}

	/**
	 * @param vin
	 * @param vInfo
	 */
	private void populateVehicleModelEngineSpecs(VehicleVIN vin,VehicleInfo vInfo) {
		Iterator<?> itvm = vin.getVehicleModels().iterator();
		while (itvm.hasNext()) {
			VehicleModel vm = (VehicleModel) itvm.next();
			vInfo.setVehicleModelName(vm.getName());
			vInfo.setVehicleModelCode(vm.getCode());
		}
		Iterator<?> itve = vin.getVehicleEngines().iterator();
		while (itve.hasNext()) {
			VehicleEngine ve = (VehicleEngine) itve.next();
			vInfo.setVehicleEngineName(ve.getName());
			vInfo.setVehicleEngineCode(ve.getCode());
		}
		/*Iterator<?> it   = vin.getSpecificConditions().iterator();
		while (it.hasNext()) {
			SpecificConditionAnswer specCondAns = (SpecificConditionAnswer) it.next();
			vInfo.setSpecificaConditionAnswerText(specCondAns.getText());
			vInfo.setSpecificaConditionAnswerCode(specCondAns.getCode());
			vInfo.setSpecificaConditionAnswerAnswer(specCondAns.getAnswer());
		}*/
	}
	
	/*public static void main(String[] args) throws CatalogException, IOException {
		VehicleHelper vehicleHelper = new VehicleHelper();
		VehicleInfo vInfo = vehicleHelper.getVehicleInfoFromVIN("1HGCM82633A004352", "TS");
		System.out.println(vInfo);
	}*/
}
