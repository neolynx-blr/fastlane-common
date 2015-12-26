package com.neolynks.common.model.client;

import java.io.Serializable;
import java.util.List;

public class WorkerTaskDetails implements Serializable {
	public enum TaskType {
		CREATE_CART, PICK_UP_ITEMS, NO_OPERATION
	}

	private long Id;
	private TaskType taskType;
	private List<WorkerItem> workerItems;
	private List<WorkerCartDetails> workerCartDetailsList;
}
