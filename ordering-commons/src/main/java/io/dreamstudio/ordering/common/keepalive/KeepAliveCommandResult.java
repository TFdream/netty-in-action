package io.dreamstudio.ordering.common.keepalive;

import io.dreamstudio.ordering.common.CommandResult;
import lombok.Data;

/**
 * @author Ricky Fung
 */
@Data
public class KeepAliveCommandResult extends CommandResult {
    private long timestamp;
}
