package com.dehinsystems.api.epicor.controller;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.interchange.ICatalogItems;
import com.ccitriad.catalog.interchange.IManufacturerItem;
import com.ccitriad.catalog.interchange.InterCatalog;
import com.ccitriad.catalog.interchange.InterItem;
import com.ccitriad.catalog.interchange.InterPartNumber;
import com.ccitriad.catalog.interchange.InterPartNumbers;
import com.ccitriad.catalog.parts.LocalC2C;
import com.ccitriad.catalog.parts.PartCatalog;
import com.dehinsystems.api.epicor.model.CatalogItem;
import com.dehinsystems.api.epicor.model.LocalCover2Cover;
import com.dehinsystems.api.epicor.model.ManufacturerItem;
import com.dehinsystems.api.epicor.model.PartRecordDetails;

@RestController
@RequestMapping("/parts/")
public class PartsController {
    
    private static final int SERVICE_TYPE = 0;

	private static final String SUPPLIER_ID = "TS";

	private static final String PASSWORD = "P@rtz00!";

	private static final String USER_NAME = "administrator";

	private static final String DOMAIN_ID = "PARTZOO";
    
	private static final String HOST_IP = "192.168.108.117";
	
	private static final String EMPTY = "";
	
	private LocalCover2Cover getCoverToCoverDetailsFromPartNumber(final String partNumber) {
		try {
			PartCatalog partCatalog = new PartCatalog(HOST_IP, DOMAIN_ID, USER_NAME, PASSWORD, SUPPLIER_ID, SERVICE_TYPE);
			LocalC2C localC2C = partCatalog.GraphicLocalC2CRequest(partNumber, null, null, null);
			return localC2C != null ? populateLocalCover2Cover(localC2C) : null;			
		} catch (CatalogException | IOException e) {
			return null;
		} 
	}

	private LocalCover2Cover populateLocalCover2Cover(LocalC2C localC2C) {
		LocalCover2Cover localCover2Cover = new LocalCover2Cover();
		localCover2Cover.setPartNumber(localC2C.getPartNumber());
		localCover2Cover.setC2cURL(localC2C.getC2CURL());
		localCover2Cover.setImageLibrary(localC2C.getImageLibrary());
		localCover2Cover.setLineCode(localC2C.getLineCode());
		localCover2Cover.setOrderNumber(localC2C.getOrderNumber());
		localCover2Cover.setThumbnailFile(localC2C.getThumbnailFile());
		return localCover2Cover;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/srchprdet/{partNumber}", method = RequestMethod.GET, headers="Accept=application/json")
	public CatalogItem getPrimaryDetailsFromPartNum(@PathVariable String partNumber) {
		InterCatalog interCatalog = null;
		try {
			interCatalog = new InterCatalog(HOST_IP, DOMAIN_ID, USER_NAME, PASSWORD, SUPPLIER_ID, SERVICE_TYPE);
			List primaryDetails = getPartPrimaryDetails(interCatalog, partNumber);
			CatalogItem catalogItem = primaryDetails != null ? populateCatalogItemPrimaryInfo(partNumber, primaryDetails) : null;
			if(catalogItem == null) {
				catalogItem = new CatalogItem();
				catalogItem.setPartNumber(partNumber);
				catalogItem.setErrorMessage("No Primary Details Found !!");
			} else {
				catalogItem.setErrorMessage(EMPTY);
			}
			return catalogItem;
		} catch (CatalogException | IOException e) {
			CatalogItem catalogItem = new CatalogItem();
			if(e instanceof IOException || e instanceof CatalogException) {
				catalogItem.setErrorMessage("Unable to connect to server. Please check internet connectivity.");
			}
			return catalogItem;
		} finally {
			if(interCatalog != null) {
				try {
					interCatalog.DisconnectCatalogServer();
				} catch (CatalogException | IOException e) {
					CatalogItem catalogItem = new CatalogItem();
					if(e instanceof IOException || e instanceof CatalogException) {
						catalogItem.setErrorMessage("Unable to close the connection to EpiCore.");
					}
					return catalogItem;
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchSecDetails/{partNumber}", method = RequestMethod.GET, headers="Accept=application/json")
	public CatalogItem getSecondaryDetailsFromPartNum(@PathVariable String partNumber) {
		try {
			InterCatalog interCatalog = new InterCatalog(HOST_IP, DOMAIN_ID, USER_NAME, PASSWORD, SUPPLIER_ID, SERVICE_TYPE);
			List primaryDetails = getPartSecondaryDetails(interCatalog, partNumber);
			CatalogItem catalogItem = primaryDetails != null ? populateCatalogItemPrimaryInfo(partNumber, primaryDetails) : null;
			if(catalogItem == null) {
				catalogItem = new CatalogItem();
				catalogItem.setPartNumber(partNumber);
				catalogItem.setErrorMessage("No Secondary Details Found !!");
			} else {
				catalogItem.setErrorMessage(EMPTY);
			}
			return catalogItem;
		} catch (CatalogException | IOException e) {
			CatalogItem catalogItem = new CatalogItem();
			if(e instanceof IOException || e instanceof CatalogException) {
				catalogItem.setErrorMessage("Unable to connect to server. Please check internet connectivity.");
			}
			return catalogItem;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{partNumber}", method = RequestMethod.GET,headers="Accept=application/json")
    public List<CatalogItem> searchPartNumber(@PathVariable String partNumber) {
		List<CatalogItem> catalogItems = new ArrayList<CatalogItem>();
    	if(StringUtils.isEmpty(partNumber) || partNumber.length() <= 4) {
    		CatalogItem catalogItem = new CatalogItem();
			catalogItem.setErrorMessage("Too many data found !! Please correct the part number search input !!");
			catalogItems.add(catalogItem);
			return catalogItems;
    	}
    	if(!partNumber.contains("*")){
    		partNumber = partNumber+"*";
    	}
    	try {
    		InterCatalog interCatalog = new InterCatalog(HOST_IP, DOMAIN_ID, USER_NAME, PASSWORD, SUPPLIER_ID, SERVICE_TYPE);
			List partNumbers = getPartNumberList(interCatalog, partNumber);
			if(partNumbers != null && partNumbers instanceof InterPartNumbers) {
				Iterator it = partNumbers.iterator();
				while (it.hasNext()) {
					InterPartNumber ipn = (InterPartNumber) it.next();
					String pno = ipn.getText();
					//Getting the primary details for the part number
					List primaryDetails = getPartPrimaryDetails(interCatalog, pno);
					CatalogItem catalogItem = primaryDetails != null ? populateCatalogItemPrimaryInfo(pno, primaryDetails) : null;
					if(catalogItem == null) {
						catalogItem = new CatalogItem();
						catalogItem.setPartNumber(pno);
						catalogItem.setErrorMessage("No Primary Details Found !!");
					} else {
						catalogItem.setErrorMessage(EMPTY);
					}
					catalogItems.add(catalogItem);
				}
			} else {
				CatalogItem catalogItem = new CatalogItem();
				catalogItem.setErrorMessage("Too many data found !! Please correct the part number search input !!");
				catalogItems.add(catalogItem);
			}
		} catch (CatalogException | IOException e) {
			if(e instanceof IOException || e instanceof CatalogException) {
				CatalogItem catalogItem = new CatalogItem();
				catalogItem.setErrorMessage("Unable to connect to server. Please check internet connectivity.");
				catalogItems.add(catalogItem);
			}
		} 
    	return catalogItems;
    }

	/**
	 * Method populates the Primary information POJO from the given part number.
	 * 
	 * @param pno
	 * @param primaryDetails
	 * @return
	 * @throws IOException 
	 * @throws CatalogException 
	 * @throws UnknownHostException 
	 */
    @SuppressWarnings("rawtypes")
    private CatalogItem populateCatalogItemPrimaryInfo(String pno, List primaryDetails) throws UnknownHostException, CatalogException, IOException {
    	CatalogItem catalogItem = null;
    	if (primaryDetails instanceof ICatalogItems) {
    		catalogItem = new CatalogItem();
    		//Setting the actual part number. 
    		catalogItem.setPartNumber(pno);
    		
    		Iterator it = primaryDetails.iterator();
    		Object primaryDetail;
    		List<ManufacturerItem> allManufacturerItems = new ArrayList<ManufacturerItem>(0);
    		List<PartRecordDetails> allInterItems = new ArrayList<PartRecordDetails>(0);
    		while (it.hasNext()) {
    			primaryDetail = it.next();
    			if (primaryDetail instanceof IManufacturerItem) {
    				ManufacturerItem mItem = populateManufacturerItem(primaryDetail);
    				allManufacturerItems.add(mItem);
    			}
    			
    			if (primaryDetail instanceof InterItem) {
    				PartRecordDetails interItem = populatePartRecordDetails(primaryDetail);
    				allInterItems.add(interItem);
    			}
    		}
    		
    		for(ManufacturerItem mItem : allManufacturerItems) {
    			String mLineCode = mItem.getLineCodes();
    			List<PartRecordDetails> interItems = new ArrayList<PartRecordDetails>(0);
    			for(PartRecordDetails interItem : allInterItems) {
    				if(interItem.getLineCodes().equals(mLineCode)) {
    					LocalCover2Cover localC2C = getCoverToCoverDetailsFromPartNumber(interItem.getPartNumber());
    					interItem.setLocalCover2Cover(localC2C);
    					interItems.add(interItem);
    				}
    			}
    			mItem.setInterItems(interItems);
    		}
    		catalogItem.setManufacturerItems(allManufacturerItems);
    	}
		return catalogItem;
	}
    
    /**
     * Populates InterItem POJO for JSON.
     * 
     * @param primaryDetail
     * @return
     */
	@SuppressWarnings("rawtypes")
	private PartRecordDetails populatePartRecordDetails(Object primaryDetail) {
		InterItem interItem = (InterItem) primaryDetail;
		PartRecordDetails partRecordDets = new PartRecordDetails();
		partRecordDets.setDescription(interItem.getDescription() != null ? interItem.getDescription() : EMPTY);
		partRecordDets.setExtendedDescription(interItem.getExtendedDescription() != null ? interItem.getExtendedDescription() : EMPTY);
		partRecordDets.setPartNumber(interItem.getPartNumber() != null ? interItem.getPartNumber() : EMPTY);
		partRecordDets.setUncompressedPartNumber(interItem.getUncompressedPartNumber() != null ? interItem.getUncompressedPartNumber() : EMPTY);
		partRecordDets.setMfgName(interItem.getmfgName() != null ? interItem.getmfgName() : EMPTY);
		partRecordDets.setYears(interItem.getYears() != null ? interItem.getYears() : EMPTY);
		
		StringBuilder lineCodes = new StringBuilder();
		Iterator itii = interItem.getLineCodes();
		while (itii.hasNext()) {
			lineCodes.append(itii.next());
		}
		
		partRecordDets.setLineCodes(lineCodes.length() > 0 ? lineCodes.toString() : EMPTY);
		partRecordDets.setCatalogLineCode(interItem.getCatalogLineCode() != null ? interItem.getCatalogLineCode() : EMPTY);
		partRecordDets.setGenericDescription(interItem.getGenericDescription() != null ? interItem.getGenericDescription() : EMPTY);
		partRecordDets.setListPrice(interItem.getListPrice() != null ? interItem.getListPrice() : EMPTY);
		return partRecordDets;
	}
	
	/**
	 * Populates Manufacturer Item POJO for JSON.
	 * 
	 * @param primaryDetail
	 * @return
	 */
	private ManufacturerItem populateManufacturerItem(Object primaryDetail) {
		IManufacturerItem mi = (IManufacturerItem) primaryDetail;
		ManufacturerItem mItem = new ManufacturerItem();
		mItem.setOrderNumber(mi.getOrderNumber());
		mItem.setLineCodes(mi.getLineCodes());
		return mItem;
	}

	/**
	 * Returns the list of Primary Details for the given part number.
	 * 
	 * @param interCatalog
	 * @param pno
	 * @return
	 */
	@SuppressWarnings("rawtypes")
    private List getPartPrimaryDetails(InterCatalog interCatalog, String pno) {
		try {
			return interCatalog.InterchangePrimary(pno);
		} catch (CatalogException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Returns the list of Secondary Details for the given part number.
	 * 
	 * @param interCatalog
	 * @param pno
	 * @return
	 */
	@SuppressWarnings("rawtypes")
    private List getPartSecondaryDetails(InterCatalog interCatalog, String pno) {
		try {
			return interCatalog.InterchangeSecondary(pno);
		} catch (CatalogException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gives us the list of part numbers for the searched part number.
	 * 
	 * @param interCatalog
	 * @param partNumber
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List getPartNumberList(InterCatalog interCatalog, String partNumber) {
		try {
			return interCatalog.InterchangePN(partNumber);
		} catch (CatalogException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
    
    
}
