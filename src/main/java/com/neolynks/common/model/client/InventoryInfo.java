package com.neolynks.common.model.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neolynks.common.api.common.BaseResponse;

/**
 * Created by nitesh.garg on 26-Aug-2015
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class InventoryInfo extends BaseResponse {

	private static final long serialVersionUID = -3415988252429606589L;

	private Long vendorId;

	private Long newDataVersionId;
	private Long currentDataVersionId;

	private Set<Long> deletedItems = new HashSet<Long>();
	private Map<Long, ItemInfo> addedItems = new HashMap<Long, ItemInfo>();
	private Map<Long, ItemInfo> updatedItems = new HashMap<Long, ItemInfo>();

}
