package com.neolynks.common.model.client.price;

import java.io.Serializable;

import lombok.Data;

import com.neolynks.common.util.DiscountPlayer;
import com.neolynks.common.util.DiscountUnit;

/**
 * Created by nitesh.garg on Dec 13, 2015
 *
 */

@Data
public class DiscountBase implements Serializable {

	private static final long serialVersionUID = -556123350699505821L;

	private Double value = 1.0D;
	private DiscountUnit unit = DiscountUnit.COUNT;
	private DiscountPlayer player = DiscountPlayer.SELF;
}
