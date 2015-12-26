package com.neolynks.common.model.client;

import java.io.Serializable;

public class WorkerCartDetails implements Serializable{
	private long id;
	private String status;
	private long priority;
	private long waitingTime; // in seconds
	private long totalItems;
	private long processedItems;
}
