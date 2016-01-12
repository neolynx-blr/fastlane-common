package com.neolynks.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * Created by nitesh.garg on 26-Aug-2015
 */

@Data
public class ResponseAudit implements Serializable {

	private static final long serialVersionUID = -790793693651803942L;

	private Boolean isError = Boolean.TRUE;

	private List<Long> successIds = new ArrayList<Long>();
	private Map<Long, Error> failureIdCodeMap = new HashMap<Long, Error>();

}
