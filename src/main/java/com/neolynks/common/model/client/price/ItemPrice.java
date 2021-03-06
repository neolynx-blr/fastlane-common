package com.neolynks.common.model.client.price;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by nitesh.garg on Oct 26, 2015
 *
 */

@Data
public class ItemPrice implements Comparable<ItemPrice>, Serializable {

	private static final long serialVersionUID = 8222498504856208090L;

	private Double mrp;
	private Boolean isMRP = Boolean.FALSE;

	private Double sellingPrice;
	private Boolean isSellingPrice = Boolean.FALSE;

	private Double basePrice;
	private Boolean isBasePrice = Boolean.FALSE;

	private TaxDetail taxDetail;
	private Boolean isTax = Boolean.FALSE;

	private DiscountDetail discountDetail;
	private Boolean isDiscount = Boolean.FALSE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ItemPrice that) {

		try {
			if (this.getMrp().compareTo(that.getMrp()) == 0 && this.getSellingPrice().compareTo(that.getSellingPrice()) == 0
					&& this.getBasePrice().compareTo(that.getBasePrice()) == 0
					&& (!this.getTaxDetail().isDifferent(that.getTaxDetail()))
					&& (!this.getDiscountDetail().isDifferent(that.getDiscountDetail()))) {
				return 0;
			}
		} catch (NullPointerException npe) {
			// Eat away for now //TODO
		}

		return 1;
	}

	public ItemPrice generateDifferentialFrom(ItemPrice newItemPrice) {

		if (newItemPrice != null) {

			ItemPrice differentialItemPrice = new ItemPrice();

			if (this.getMrp() == null || this.getMrp().compareTo(newItemPrice.getMrp()) != 0) {
				differentialItemPrice.setMrp(newItemPrice.getMrp());
			}

			if (this.getSellingPrice() == null || this.getSellingPrice().compareTo(newItemPrice.getSellingPrice()) != 0) {
				differentialItemPrice.setPrice(newItemPrice.getSellingPrice());
			}

			if (this.getBasePrice() == null || this.getBasePrice().compareTo(newItemPrice.getBasePrice()) != 0) {
				differentialItemPrice.setBasePrice(newItemPrice.getBasePrice());
			}

			if (this.getTaxDetail() == null || this.getTaxDetail().isDifferent(newItemPrice.getTaxDetail())) {
				differentialItemPrice.setTaxDetail(newItemPrice.getTaxDetail());
			}

			if (this.getDiscountDetail() == null
					|| this.getDiscountDetail().isDifferent(newItemPrice.getDiscountDetail())) {
				differentialItemPrice.setDiscountDetail(newItemPrice.getDiscountDetail());
			}

			return differentialItemPrice;

		}

		return null;
	}

	/**
	 * @param mrp
	 *            the mrp to set
	 */
	public void setMrp(Double mrp) {
		if (this.mrp != mrp) {
			this.mrp = mrp;
			this.setIsMRP(Boolean.TRUE);
		}
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		if (this.getSellingPrice() != price) {
			this.sellingPrice = price;
			this.setIsSellingPrice(Boolean.TRUE);
		}
	}

	/**
	 * @param basePrice
	 *            the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		if (this.basePrice != basePrice) {
			this.basePrice = basePrice;
			this.setIsBasePrice(Boolean.TRUE);
		}
	}

	/**
	 * @param taxDetail
	 *            the taxDetail to set
	 */
	public void setTaxDetail(TaxDetail taxDetail) {
		if (this.taxDetail == null && taxDetail != null) {
			this.taxDetail = taxDetail;
			this.setIsTax(Boolean.TRUE);
		} else if (this.taxDetail != null && this.taxDetail.isDifferent(taxDetail)) {
			this.taxDetail = taxDetail;
			this.setIsTax(Boolean.TRUE);
		}
	}

	/**
	 * @param discountDetail
	 *            the discountDetail to set
	 */
	public void setDiscountDetail(DiscountDetail discountDetail) {
		if (this.discountDetail == null && discountDetail != null) {
			this.discountDetail = discountDetail;
			this.setIsDiscount(Boolean.TRUE);
		} else if (this.discountDetail != null && this.discountDetail.isDifferent(discountDetail)) {
			this.discountDetail = discountDetail;
			this.setIsDiscount(Boolean.TRUE);
		}
	}

	public void reset() {
		this.setIsMRP(Boolean.FALSE);
		this.setIsBasePrice(Boolean.FALSE);
		this.setIsSellingPrice(Boolean.FALSE);

		this.setIsTax(Boolean.FALSE);
		this.setIsDiscount(Boolean.FALSE);
	}

}
