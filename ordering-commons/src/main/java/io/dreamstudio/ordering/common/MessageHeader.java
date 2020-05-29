package io.dreamstudio.ordering.common;

import lombok.Data;

/**
 * @author Ricky Fung
 */
@Data
public class MessageHeader {
    /**
     * 版本号
     */
    private int version = 1;

    /**
     * 业务类型
     */
    private int opCode;

    private Long requestId;

}
