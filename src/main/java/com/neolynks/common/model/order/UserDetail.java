package com.neolynks.common.model.order;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by nitesh.garg on Oct 22, 2015
 *
 */

@Data
public class UserDetail implements Serializable {

	private static final long serialVersionUID = -8197374004008782447L;
	
	private String userId;
	private String deviceIdMap;
}
