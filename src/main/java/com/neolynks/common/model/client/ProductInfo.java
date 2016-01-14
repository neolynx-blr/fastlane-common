package com.neolynks.common.model.client;

import java.io.Serializable;

import lombok.Data;

import com.neolynks.common.util.StringUtilsCustom;

/**
 * Created by nitesh.garg on Oct 28, 2015
 *
 */

@Data
public class ProductInfo implements Serializable {

	private static final long serialVersionUID = 1395028613058948189L;

	private String name;
	private String tagLine;
	private String imageJSON;
	private String description;
	private String benefits;
	private String howToUse;
	private String brandName;
}
