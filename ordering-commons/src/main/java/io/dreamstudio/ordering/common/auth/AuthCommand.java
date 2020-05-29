package io.dreamstudio.ordering.common.auth;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;

/**
 * @author Ricky Fung
 */
public class AuthCommand extends Command {
    private String userName;
    private String password;

    @Override
    public CommandResult execute() {
        if ("admin".equalsIgnoreCase(userName)) {
            AuthCommandResult result = new AuthCommandResult();
            result.setPassAuth(true);
            return result;
        }
        AuthCommandResult result = new AuthCommandResult();
        result.setPassAuth(false);
        return result;
    }
}
