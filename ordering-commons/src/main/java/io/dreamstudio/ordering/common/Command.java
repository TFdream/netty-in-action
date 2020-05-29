package io.dreamstudio.ordering.common;

/**
 * 业务请求基类
 * @author Ricky Fung
 */
public abstract class Command extends MessageBody {

    public abstract CommandResult execute();
}
