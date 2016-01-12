package com.neolynks.common.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;

import com.neolynks.common.model.ErrorCode;
import com.neolynks.common.model.client.ItemInfo;
import com.neolynks.common.model.client.price.ItemPrice;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by nitesh.garg on Nov 28, 2015
 *
 */

@Data
public class ItemRequest implements Serializable {

	private static final long serialVersionUID = 2596651645497262599L;

    @Setter(AccessLevel.NONE)
	private Long barcode;
    @Setter(AccessLevel.NONE)
	private String itemCode;

	private Integer countForInStorePickup = 1;
	
	public ItemRequest(Long barcode, String itemCode) {
		this.barcode = barcode;
		this.itemCode = itemCode;
	}
}
