package com.neolynks.common.model.order;

import java.util.Calendar;
import java.util.Date;

import com.neolynks.common.model.client.ItemInfo;
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

	/**
	 * MRP is arbitrary number with no real implications other than sometimes being considered for discounts
	 * 
	 * fn(basePrice, tax%) = taxAmount
	 * 
	 * selling-price = basePrice + taxAmount - individualItemDiscountAmount
	 * netPrice = fn ( (count * price), cummulative-discount-if-applicable)
	 * 
	 */	

    //TODO: Understand this piece here
    public static Double calculatePricing(ItemInfo itemInfo, int itemCount) {
        Double netPrice = 0.0D;
        Double sellingPrice = itemInfo.getItemPrice().getSellingPrice();
        DiscountDetail discountDetail = itemInfo.getItemPrice().getDiscountDetail();

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
        return netPrice;
    }

}
