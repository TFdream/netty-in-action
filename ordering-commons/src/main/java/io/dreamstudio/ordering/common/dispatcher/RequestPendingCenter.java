package io.dreamstudio.ordering.common.dispatcher;

import io.dreamstudio.ordering.common.CommandResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ricky Fung
 */
public class RequestPendingCenter {
    private final Map<Long, CommandResultFuture> futureMap = new ConcurrentHashMap<>();

    public void add(Long requestId, CommandResultFuture future) {
        futureMap.put(requestId, future);
    }

    public void set(Long requestId, CommandResult result) {
        CommandResultFuture future = futureMap.get(requestId);
        if (future != null) {
            future.setSuccess(result);
            //调用成功后删除，避免越来越大
            futureMap.remove(requestId);
        }
    }
}
