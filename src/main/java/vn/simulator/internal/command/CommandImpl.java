package vn.simulator.internal.command;

import com.google.inject.Inject;
import vn.simulator.internal.services.ILoginService;

public abstract class CommandImpl {

    protected ILoginService loginService;

    @Inject
    public CommandImpl setLoginService(ILoginService loginService) {
        this.loginService = loginService;
        return this;
    }
}
