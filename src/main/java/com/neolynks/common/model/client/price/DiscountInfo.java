package com.neolynks.common.model.client.price;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by nitesh.garg on Oct 28, 2015
 *
 */

@Data
public class DiscountInfo implements Serializable {

	private static final long serialVersionUID = 1810204767291078266L;

	private Date startDate;
	private Date endDate;

	DiscountBase discountOn;
	List<DiscountBase> discountFor;
}
