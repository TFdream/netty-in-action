package io.dreamstudio.ordering.common;

import io.dreamstudio.ordering.common.auth.AuthCommand;
import io.dreamstudio.ordering.common.auth.AuthCommandResult;
import io.dreamstudio.ordering.common.keepalive.KeepAliveCommand;
import io.dreamstudio.ordering.common.keepalive.KeepAliveCommandResult;
import io.dreamstudio.ordering.common.order.OrderCommand;
import io.dreamstudio.ordering.common.order.OrderCommandResult;

/**
 * @author Ricky Fung
 */
public enum CommandType {
    AUTH(1, AuthCommand.class, AuthCommandResult.class),
    KEEP_ALIVE(2,KeepAliveCommand.class, KeepAliveCommandResult.class),
    ORDER(3, OrderCommand.class, OrderCommandResult.class),
    ;
    private int code;
    private Class<? extends Command> requestType;
    private Class<? extends CommandResult> responseType;
    CommandType(int code, Class<? extends Command> requestType, Class<? extends CommandResult> responseType) {
        this.code = code;
        this.requestType = requestType;
        this.responseType = responseType;
    }

    public static CommandType ofCode(int opCode) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getCode() == opCode) {
                return commandType;
            }
        }
        return null;
    }

    public static CommandType fromCommand(Command command) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getRequestType() == command.getClass()) {
                return commandType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public Class<? extends Command> getRequestType() {
        return requestType;
    }

    public Class<? extends CommandResult> getResponseType() {
        return responseType;
    }
}
