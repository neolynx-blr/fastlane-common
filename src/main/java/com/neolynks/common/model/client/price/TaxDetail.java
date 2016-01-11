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
public class TaxDetail implements Serializable {

	private static final long serialVersionUID = -1224105408199308790L;

	private List<TaxInfo> taxInfo = new ArrayList<TaxInfo>();

    //TODO: Check if lombox equals work for such cases (having list inside object)
	public Boolean isDifferent(TaxDetail newTaxDetail) {
		for (TaxInfo outerInstance : this.getTaxInfo()) {
			for (TaxInfo innerInstance : newTaxDetail.getTaxInfo()) {
				if (!outerInstance.equals(innerInstance)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

    public static void main(String[] arg){
        TaxDetail taxDetail = new TaxDetail();
        List<TaxInfo> taxInfo = new ArrayList<TaxInfo>();
        taxInfo.add(new TaxInfo(1, 1.0));
        taxDetail.setTaxInfo(taxInfo);

        TaxDetail taxDetail2 = new TaxDetail();
        List<TaxInfo> taxInfo2 = new ArrayList<TaxInfo>();
        taxInfo2.add(new TaxInfo(1, 1.0));
        taxDetail2.setTaxInfo(taxInfo2);

        System.out.println(taxDetail.equals(taxDetail2));
    }
}
