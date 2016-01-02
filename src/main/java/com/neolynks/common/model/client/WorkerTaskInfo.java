package com.neolynks.common.model.client;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WorkerTaskInfo implements Serializable {
	public enum TaskType {
		CREATE_CART, PICK_UP_PRODUCTS, NO_OPERATION;
	}

	private long Id;
	private TaskType taskType;
	private List<WorkerProductInfo> workerItems;
	private List<WorkerCartInfo> workerCartDetailsList;
}
