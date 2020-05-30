package io.dreamstudio.ordering.common.auth;

import io.dreamstudio.ordering.common.Command;

/**
 * @author Ricky Fung
 */
public class AuthCommand extends Command {
    private String userName;
    private String password;

    public AuthCommand(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public AuthCommandResult execute() {
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
