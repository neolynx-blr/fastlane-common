package com.neolynks.common.model.client.price;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by nitesh.garg on Oct 28, 2015
 *
 */

@Data
public class DiscountDetail implements Serializable {

	private static final long serialVersionUID = 3994392467743733771L;

	private List<DiscountInfo> discountInfo = new ArrayList<DiscountInfo>();
}
