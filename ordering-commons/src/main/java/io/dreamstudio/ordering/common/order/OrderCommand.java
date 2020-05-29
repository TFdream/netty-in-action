package io.dreamstudio.ordering.common.order;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;
import io.dreamstudio.ordering.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class OrderCommand extends Command {
    private transient final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 座位id
     */
    private long seatId;
    /**
     * 菜品列表
     */
    private List<String> dishes;

    public OrderCommand() {}
    public OrderCommand(long seatId, List<String> dishes) {
        this.seatId = seatId;
        this.dishes = dishes;
    }

    @Override
    public CommandResult execute() {
        LOG.info("点餐系统-处理请求开始, seatId:{}, dishes:{}", seatId, dishes);

        OrderCommandResult result = process();

        LOG.info("点餐系统-处理请求结束, ");
        return result;
    }

    private OrderCommandResult process() {
        int maxMillis = 1000;
        int time = new Random().nextInt(maxMillis);
        if (time<1) {
            time = 1;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        OrderCommandResult result = new OrderCommandResult();
        result.setOrderId(IdUtils.nextOrderId());
        result.setSeatId(seatId);
        result.setProcessTime(time);
        return result;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public void setDishes(List<String> dishes) {
        this.dishes = dishes;
    }
}
