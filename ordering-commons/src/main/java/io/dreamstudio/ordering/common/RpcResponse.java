package io.dreamstudio.ordering.common;

/**
 * 响应消息
 * @author Ricky Fung
 */
public class RpcResponse extends Message<CommandResult> {

    @Override
    protected Class getMessageBodyDecodeClass(int opCode) {
        return CommandType.ofCode(opCode).getResponseType();
    }
}
