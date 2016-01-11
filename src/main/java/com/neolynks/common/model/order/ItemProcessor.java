package com.neolynks.common.model.order;

import java.util.Calendar;
import java.util.Date;

import lombok.Data;

import org.apache.commons.collections4.CollectionUtils;

import com.neolynks.common.model.client.ProductInfo;
import com.neolynks.common.model.client.price.DiscountBase;
import com.neolynks.common.model.client.price.DiscountDetail;
import com.neolynks.common.model.client.price.DiscountInfo;
import com.neolynks.common.model.client.price.ItemPrice;
import com.neolynks.common.util.DiscountPlayer;

/**
 * Created by nitesh.garg on Nov 29, 2015
 *
 */

@Data
public class ItemProcessor {

	private Long barcode;
	private String itemCode;
	private ProductInfo productInfo;
	
	private ItemPrice itemPrice;
	private Integer countForInStorePickup = 1;

	/**
	 * MRP is arbitrary number with no real implications other than sometimes being considered for discounts
	 * 
	 * fn(basePrice, tax%) = taxAmount
	 * 
	 * selling-price = basePrice + taxAmount - individualItemDiscountAmount
	 * netPrice = fn ( (count * price), cummulative-discount-if-applicable)
	 * 
	 */	
	private Double netPrice;
	private Double netTaxAmount;
	private Double netTaxableAmount;
	private Double netDiscountAmount;
	
	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(ItemPrice itemPrice) {
		if (this.getItemPrice() == null || !this.getItemPrice().equals(itemPrice)) {
			this.itemPrice = itemPrice;
			calculatePricing();
		}
	}

	public void setItemCount(int inStorePickUpCount) {
		boolean recalculatePrice = false;
		if(inStorePickUpCount != this.getCountForInStorePickup()) {
            recalculatePrice = true;
        }
		this.setCountForInStorePickup(inStorePickUpCount);
		if(recalculatePrice) {
			calculatePricing();
		}
	}
	
	public ItemProcessor() {
	}
	
	public ItemProcessor(ItemRequest itemRequest) {
		this.setBarcode(itemRequest.getBarcode());
		this.setItemCode(itemRequest.getItemCode());
		this.setItemPrice(itemRequest.getItemPrice());
		this.setCountForInStorePickup(itemRequest.getCountForInStorePickup());
	}
	
	public ItemRequest generateItemRequest() {
		
		ItemRequest request = new ItemRequest();

		request.setBarcode(this.getBarcode());
		request.setItemCode(this.getItemCode());
		request.setCountForInStorePickup(this.getCountForInStorePickup());
		
		return request;
	}

    public void calculatePricing() {

        Double netPrice = 0.0D;
        Double sellingPrice = this.getItemPrice().getSellingPrice();
        DiscountDetail discountDetail = this.getItemPrice().getDiscountDetail();
        int itemCount = this.getCountForInStorePickup();

        Double maxDiscount = 0.0D;
        Date currentDate = Calendar.getInstance().getTime();
        Double priceBeforeDiscount = itemCount * sellingPrice;

        if (discountDetail != null && CollectionUtils.isNotEmpty(discountDetail.getDiscountInfo())) {
            for (DiscountInfo discountInfo : discountDetail.getDiscountInfo()) {
                if (currentDate.after(discountInfo.getStartDate()) && currentDate.before(discountInfo.getEndDate())) {
                    if (discountInfo.getDiscountOn().getPlayer() == DiscountPlayer.SELF) {
                        for (DiscountBase discountFor : discountInfo.getDiscountFor()) {
                            if (discountFor.getPlayer() == DiscountPlayer.SELF) {
                                maxDiscount += 1.0D;
                                priceBeforeDiscount += 1.0D;
                            }
                        }
                    }
                }
            }
        }
        this.setNetPrice(netPrice);
    }

}
