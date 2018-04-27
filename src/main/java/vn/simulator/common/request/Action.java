package vn.simulator.common.request;

import vn.simulator.internal.command.ICommand;
import vn.simulator.internal.command.impl.LoginCommand;

public enum Action {

    LOGIN(new LoginCommand());

    private ICommand command;

    Action(ICommand command) {
        this.command = command;
    }

    public ICommand getCommand() {
        return command;
    }
}
