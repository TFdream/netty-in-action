package io.dreamstudio.ordering.common.auth;

import io.dreamstudio.ordering.common.CommandResult;
import lombok.Data;

/**
 * @author Ricky Fung
 */
@Data
public class AuthCommandResult extends CommandResult {
    private boolean passAuth;
}
