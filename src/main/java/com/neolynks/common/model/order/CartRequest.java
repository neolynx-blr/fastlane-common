package com.neolynks.common.model.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.neolynks.common.api.customer.UserDetail;
import lombok.Data;

/**
 * Created by nitesh.garg on Nov 28, 2015
 *
 */

@Data
public class CartRequest implements Serializable {

	private static final long serialVersionUID = -532841197586404513L;

	private Long cartId;
	
	private Long vendorId;
	private Long deviceDataVersionId;

	private UserDetail userDetail;

	private Map<Long, Integer> inStorePickUpItemBarcodeCountMap = new HashMap<Long, Integer>();

	/**
	 * This object is used to indicate that order is updated now, although DO
	 * NOTE that fields present above may also be modified in the update
	 * request. Will be ignored for create order calls.
	 */
	private CartUpdated updateCart = new CartUpdated();
	
}
