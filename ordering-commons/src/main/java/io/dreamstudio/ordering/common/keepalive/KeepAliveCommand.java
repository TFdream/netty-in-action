package io.dreamstudio.ordering.common.keepalive;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;

/**
 * @author Ricky Fung
 */
public class KeepAliveCommand extends Command {
    private long timestamp;

    public KeepAliveCommand() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public CommandResult execute() {
        KeepAliveCommandResult result = new KeepAliveCommandResult();
        result.setTimestamp(timestamp);
        return result;
    }
}
