package com.neolynks.common.model.cart;

import java.io.Serializable;

import com.neolynks.common.api.common.CartStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neolynks.common.api.common.BaseResponse;

/**
 * Created by nitesh.garg on Dec 27, 2015
 *
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class CartResponse extends BaseResponse implements Serializable {
	private static final long serialVersionUID = 5112133396527776299L;
	CartInfo cartBase;

    @Data
    public class CartInfo implements Serializable {

        private static final long serialVersionUID = 1255731123698943954L;

        private Long id;
        private CartStatus status = CartStatus.OPEN;

        private String userId;
        private Long vendorId;

        private Integer totalItemCount = 0;
        private Integer uniqueItemCount = 0;
    }
}
