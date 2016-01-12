package com.neolynks.common.model.order;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Created by nitesh.garg on Nov 29, 2015
 *
 * This class is not meant to be sent from/server but rather meant for local
 * processing both by the application as well as on server side if needed. On
 * client side, the application should do all cart handling operations using
 * this class as this allow calculating final pricing and all the core
 * operations like adding/removing items to cart as well as
 * incrementing/decrementing item counts. While communicating with the server,
 * simply extract a CartRequest object out of this class.
 */

@Data
public class CartProcessor {

	private Long vendorId;
	private Long deviceDataVersionId;

	private UserDetail userDetail = new UserDetail();
	
	private Map<Long, Integer> inStorePickUpItemBarcodeCountMap = new HashMap<Long, Integer>();

	private Double taxAmount;
	private Double taxableAmount;
	private Double discountAmount;

	private Double netAmount;

	private int itemCount;
	private int totalCount;

	/**
	 * Following fields are meaningful only when order has once already been
	 * sent and created on server side. Next transaction will be more like
	 * updating the order, if anything.
	 */
	private Long orderId;
	private Long lastKnownServerDataVersionId;

	/**
	 * Following fields are more of information flowing from server to client
	 * and something that may be needed at information to display on client side
	 * but not to be sent back to the server.
	 */
	private Integer orderStatus;

	public void addItemToCart(ItemRequest itemRequest) {
		Boolean recalculatePrice = Boolean.FALSE;
		ItemProcessor existingItem = this.getBarcodeItemRequestMap().get(itemProcessor.getBarcode());
		int newCount = itemProcessor.getCountForInStorePickup();
		int existingCount = (existingItem == null ? 0 : existingItem.getCountForInStorePickup());

		if (newCount == 0) {
			if (existingCount > 0) {
				recalculatePrice = Boolean.TRUE;
			}
			removeItemFromCart(itemProcessor.getBarcode());
		} else {
			if (newCount != existingCount) {
				recalculatePrice = Boolean.TRUE;
				this.setTotalCount(this.getTotalCount() + newCount - existingCount);
			}

			if (existingCount == 0) {
				this.setItemCount(this.getItemCount() + 1);
			}
			
			this.getBarcodeItemRequestMap().put(itemProcessor.getBarcode(), itemProcessor);
		}	

		if (recalculatePrice) {
			calculatePricing();
		}
	}
	
	public void removeItemFromCart(Long barcode) {

		ItemProcessor existingItem = this.getBarcodeItemRequestMap().get(barcode);
		int existingCount = existingItem.getCountForInStorePickup();

		this.setItemCount(this.getItemCount() - 1);
		
		this.getBarcodeItemRequestMap().remove(barcode);
		this.getInStorePickUpItemBarcodeCountMap().remove(barcode);
		
		this.setTotalCount(this.getTotalCount() - existingCount);

		calculatePricing();
	}
	
	public void setUserDetailsForCart(String userId, String deviceIdMap, Integer addressId) {
		this.getUserDetail().setUserId(userId);
		this.getUserDetail().setDeviceIdMap(deviceIdMap);
	}

	public CartProcessor(CartRequest request) {
		this.setVendorId(request.getVendorId());
		this.setUserDetail(request.getUserDetail());
		this.setDeviceDataVersionId(request.getDeviceDataVersionId());
		
		this.setInStorePickUpItemBarcodeCountMap(request.getInStorePickUpItemBarcodeCountMap());
		
		/* TBM: @Shukla TODO
		for(ItemRequest instance : request.getItemList()) {
			ItemProcessor itemProcessor = new ItemProcessor(instance);
			itemProcessor.setIsPricingChanged(Boolean.FALSE);
			this.getBarcodeItemRequestMap().put(instance.getBarcode(), itemProcessor);
		}
		*/
		
		this.setNetAmount(request.getNetAmount());
		this.setTaxAmount(request.getTaxAmount());
		this.setTaxableAmount(request.getTaxableAmount());
		this.setDiscountAmount(request.getDiscountAmount());
		
		if(request.getUpdateCart() != null) {
			this.setOrderId(request.getCartId());
			this.setLastKnownServerDataVersionId(request.getUpdateCart().getLastKnownServerDataVersionId());
		}
	}
	
	public CartRequest generateRequest() {
		CartRequest request = new CartRequest();

		request.setCartId(this.getOrderId());
		request.setVendorId(this.getVendorId());
		request.setUserDetail(this.getUserDetail());
		request.setDeviceDataVersionId(this.getDeviceDataVersionId());

		request.setInStorePickUpItemBarcodeCountMap(this.getInStorePickUpItemBarcodeCountMap());
		
		request.setNetAmount(this.getNetAmount());
		request.setTaxAmount(this.getTaxAmount());
		request.setTaxableAmount(this.getTaxableAmount());
		request.setDiscountAmount(this.getDiscountAmount());
		
		if (this.getOrderId() != null) {
			CartUpdated updateCart = new CartUpdated();
			updateCart.setLastKnownServerDataVersionId(this.getLastKnownServerDataVersionId());
			request.setUpdateCart(updateCart);
		}

		return request;
	}

	public void mergeResponseToCart(Response cartResponse) {

		this.setOrderId(cartResponse.getOrderId());
		this.setOrderStatus(cartResponse.getOrderStatus());
		this.setLastKnownServerDataVersionId(cartResponse.getServerDataVersionId());

		if (cartResponse.getUpdateResponseInfo() != null) {
			this.setTaxAmount(cartResponse.getUpdateResponseInfo().getTaxAmount());
			this.setNetAmount(cartResponse.getUpdateResponseInfo().getNetAmount());
			this.setTaxableAmount(cartResponse.getUpdateResponseInfo().getTaxableAmount());
			this.setDiscountAmount(cartResponse.getUpdateResponseInfo().getDiscountAmount());
		}

		for (ItemRequest instance : cartResponse.getUpdateResponseInfo().getOnlyUpdatedItemList()) {
			
			if(instance.getCountForInStorePickup() > 0) {
				this.getInStorePickUpItemBarcodeCountMap().put(instance.getBarcode(), instance.getCountForInStorePickup());
			}

			this.getBarcodeItemRequestMap().put(instance.getBarcode(), new ItemProcessor(instance));
		}
		
		for (ItemProcessor instance : this.getBarcodeItemRequestMap().values()) {
			this.getBarcodeItemRequestMap().put(instance.getBarcode(), instance);
		}		 
	}

	public void calculatePricing() {
		Double netPrice = 0.0D;
		for (ItemProcessor instance : this.getBarcodeItemRequestMap().values()) {
			netPrice += instance.getNetPrice();
		}
		this.setNetAmount(netPrice);
	}

}
