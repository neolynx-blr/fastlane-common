package com.neolynks.common.model.client;

import lombok.Data;

@Data
public class WorkerProductInfo extends ProductInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long barcode;
	private int count;
	private int sequenceId;
}
