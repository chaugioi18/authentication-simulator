package vn.simulator.internal.command.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import vn.simulator.common.request.LoginRequest;
import vn.simulator.common.response.CommonResponse;
import vn.simulator.internal.command.CommandImpl;
import vn.simulator.internal.command.ICommand;

public class LoginCommand extends CommandImpl implements ICommand {

    @Override
    public CommonResponse execute(Buffer payload) {
        try {
            LoginRequest loginRequest = Json.decodeValue(payload, LoginRequest.class);
            return loginService.login(loginRequest);
        } catch (Exception e) {
            return new CommonResponse.CommRequestBuilder().build();
        }
    }
}
