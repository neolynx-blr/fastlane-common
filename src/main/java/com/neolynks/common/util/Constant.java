package com.neolynks.common.util;

/**
 * Created by nitesh.garg on 18-Sep-2015
 */
public class Constant {

	public static final String NEW_LINE_SEPARATOR = "\n";
	public static final String COMMA_SEPARATOR = ",";
	
	public static final String[] STATUS_FILE_HEADER = { "id", "timestamp" };

	public static final String[] SYNC_FILE_HEADER = { "identifier", "timestamp" };
	public static final String[] INVENTORY_FILE_HEADER = { "id", "item_code", "version_id", "name", "description", "tag_line",
			"barcode", "mrp", "price", "image_json", "discount_type", "discount_value", "last_modified_on" };
	
	public static final int SYNC_ID_TYPE_TIMESTAMP_IN_MILLIS = 1;
	
	
	public final static int VENDOR_DATA_SYNC_ID_TIMESTAMP_MILLIS = 1;
	
	public final static int PAYMENT_MODE_COD = 1;
	public final static int PAYMENT_MODE_CASH = 2;
	public final static int PAYMENT_MODE_EWALLET = 3;
	public final static int PAYMENT_MODE_DEBIT_CARD = 4;
	public final static int PAYMENT_MODE_NET_BANKING = 5;
	public final static int PAYMENT_MODE_CREDIT_CARD = 6;
	
	public final static int CARD_TYPE_VISA_CARD = 1;
	public final static int CARD_TYPE_AMEX_CARD = 2;
	public final static int CARD_TYPE_MASTER_CARD = 3;
	
	public final static int DELIVERY_MODE_IN_STORE_PICKUP = 1;
	public final static int DELIVERY_MODE_PARTIAL_DELIVERY = 2;
	public final static int DELIVERY_MODE_DELIVERY = 3;
	
	public final static int DISCOUNT_UNIT_COUNT = 1;
	public final static int DISCOUNT_UNIT_GRAMS = 2;
	public final static int DISCOUNT_UNIT_METERS = 3;
	public final static int DISCOUNT_UNIT_LITERS = 4;
	public final static int DISCOUNT_UNIT_RUPEES = 5;
	public final static int DISCOUNT_UNIT_PERCENTAGE = 6;
	
	public final static int DISCOUNT_ON_SELF = 1;
	public final static int DISCOUNT_ON_TIER = 2;
	public final static int DISCOUNT_ON_BRAND = 3;
	public final static int DISCOUNT_ON_CATEGORY = 4;
	public final static int DISCOUNT_ON_TRANSACTION = 5;
	public final static int DISCOUNT_ON_ANOTHER_ITEM = 6;
	
}
