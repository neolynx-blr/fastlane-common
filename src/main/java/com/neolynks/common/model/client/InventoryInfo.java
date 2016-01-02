package com.neolynks.common.model.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neolynks.common.model.BaseResponse;

/**
 * Created by nitesh.garg on 26-Aug-2015
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryInfo extends BaseResponse {

	private static final long serialVersionUID = -3415988252429606589L;

	private Long vendorId;

	/**
	 * This flag indicated if the data contained in the instance of this object
	 * is focusing on pricing of the inventory contained or else the complete
	 * information of every item
	 */
	private Boolean isDataPriceOriented = Boolean.FALSE;

	private Long newDataVersionId;
	private Long currentDataVersionId;

	private Set<String> deletedItems = new HashSet<String>();
	private Map<Long, ItemInfo> addedItems = new HashMap<Long, ItemInfo>();
	private Map<Long, ItemInfo> updatedItems = new HashMap<Long, ItemInfo>();

/*	
	public Boolean checkItemCodeInDifferential(String itemCode) {
		return Boolean.FALSE;
	}

	public Boolean checkIfInstanceDataIsValid() {
		Boolean response = Boolean.TRUE;

		if (vendorId == null || vendorId < 1) {
			response = Boolean.FALSE;
		}

		if (newDataVersionId == null || currentDataVersionId == null || newDataVersionId < currentDataVersionId) {
			response = Boolean.FALSE;
		}

		if (this.getDeletedItems() == null || this.getAddedItems() == null || this.getUpdatedItems() == null
				|| (this.getDeletedItems().size() + this.getAddedItems().size() + this.getUpdatedItems().size() < 1)) {
			response = Boolean.FALSE;
		}

		return response;
	}
*/
	
}
