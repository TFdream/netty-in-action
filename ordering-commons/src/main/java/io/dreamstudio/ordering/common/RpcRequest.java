package io.dreamstudio.ordering.common;

/**
 * 请求消息
 * @author Ricky Fung
 */
public class RpcRequest extends Message<Command> {

    @Override
    protected Class getMessageBodyDecodeClass(int opCode) {
        return CommandType.ofCode(opCode).getRequestType();
    }

    public RpcRequest() {}

    public RpcRequest(Long requestId, Command command) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setRequestId(requestId);
        messageHeader.setOpCode(CommandType.fromCommand(command).getCode());

        this.setHeader(messageHeader);
        this.setBody(command);
    }

}
