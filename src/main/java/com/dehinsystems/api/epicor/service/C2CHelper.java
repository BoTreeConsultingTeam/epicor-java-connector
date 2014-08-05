package com.dehinsystems.api.epicor.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.ccitriad.catalog.CatalogException;
import com.ccitriad.catalog.parts.LocalC2C;
import com.ccitriad.catalog.parts.PartCatalog;
import com.dehinsystems.api.epicor.model.LocalCover2Cover;
import com.dehinsystems.api.epicor.util.EpicoreConstants;
import com.dehinsystems.api.epicor.util.HtmlScrapper;

public class C2CHelper {

	public LocalCover2Cover getCoverToCoverDetailsFromPartNumber(final String supplierID, final String partNumber) throws CatalogException, UnknownHostException, IOException {
		PartCatalog partCatalog = new PartCatalog(EpicoreConstants.HOST_IP, EpicoreConstants.DOMAIN_ID,EpicoreConstants.USER_NAME,EpicoreConstants.PASSWORD,supplierID, EpicoreConstants.SERVICE_TYPE);
		LocalC2C localC2C = partCatalog.GraphicLocalC2CRequest(partNumber, null, null, null);
		return localC2C != null ? populateLocalCover2Cover(localC2C) : null;			
	}

	private LocalCover2Cover populateLocalCover2Cover(LocalC2C localC2C) throws IOException {
		LocalCover2Cover localCover2Cover = new LocalCover2Cover();
		localCover2Cover.setPartNumber(localC2C.getPartNumber());
		localCover2Cover.setC2cURL(localC2C.getC2CURL());
		localCover2Cover.setImageLibrary(localC2C.getImageLibrary());
		localCover2Cover.setLineCode(localC2C.getLineCode());
		localCover2Cover.setOrderNumber(localC2C.getOrderNumber());
		localCover2Cover.setThumbnailFile(localC2C.getThumbnailFile());
		if(localC2C.getC2CURL() != null) {
			List<String> imageUrls = HtmlScrapper.fetchImageUrls(localC2C.getC2CURL());
			localCover2Cover.setImageUrls(imageUrls);
		}
		
		return localCover2Cover;
	}
}
