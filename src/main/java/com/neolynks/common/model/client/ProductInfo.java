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

	public ProductInfo generateDifferentialFrom(ProductInfo newProductInfo) {

		if (newProductInfo != null) {

			ProductInfo differentialProductInfo = new ProductInfo();

			if (this.getName() == null || !this.getName().equals(newProductInfo.getName())) {
				differentialProductInfo.setName(newProductInfo.getName());
			}

			if (this.getTagLine() == null || !this.getTagLine().equals(newProductInfo.getTagLine())) {
				differentialProductInfo.setTagLine(newProductInfo.getTagLine());
			}

			if (this.getImageJSON() == null || !this.getImageJSON().equals(newProductInfo.getImageJSON())) {
				differentialProductInfo.setImageJSON(newProductInfo.getImageJSON());
			}

			if (this.getDescription() == null || !this.getDescription().equals(newProductInfo.getDescription())) {
				differentialProductInfo.setDescription(newProductInfo.getDescription());
			}

			if (this.getBenefits() == null || !this.getBenefits().equals(newProductInfo.getBenefits())) {
				differentialProductInfo.setBenefits(newProductInfo.getBenefits());
			}

			if (this.getHowToUse() == null || !this.getHowToUse().equals(newProductInfo.getHowToUse())) {
				differentialProductInfo.setHowToUse(newProductInfo.getHowToUse());
			}

			if (this.getBrandName() == null || !this.getBrandName().equals(newProductInfo.getBrandName())) {
				differentialProductInfo.setBrandName(newProductInfo.getBrandName());
			}

			return differentialProductInfo;
		}

		return null;
	}

}
