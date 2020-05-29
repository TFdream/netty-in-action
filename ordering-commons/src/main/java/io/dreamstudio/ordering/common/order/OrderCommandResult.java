package io.dreamstudio.ordering.common.order;

import io.dreamstudio.ordering.common.CommandResult;
import lombok.Data;

/**
 * @author Ricky Fung
 */
@Data
public class OrderCommandResult extends CommandResult {
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 座位ID
     */
    private Long seatId;
    /**
     * 请求处理时间
     */
    private int processTime;
}
