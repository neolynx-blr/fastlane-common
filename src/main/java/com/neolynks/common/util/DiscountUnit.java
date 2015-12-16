/**
 * 
 */
package com.neolynks.common.util;

/**
 * Created by nitesh.garg on Dec 13, 2015
 *
 */
public enum DiscountUnit {

	PERCENTAGE(1), COUNT(2), GRAMS(3), LITERS(4), METERS(5), RUPEES(6);

	private final int value;

	private DiscountUnit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
