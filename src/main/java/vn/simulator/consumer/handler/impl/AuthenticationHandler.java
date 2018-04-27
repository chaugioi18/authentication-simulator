package vn.simulator.consumer.handler.impl;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import vn.simulator.common.request.Action;
import vn.simulator.common.response.CodeResponse;
import vn.simulator.consumer.handler.AbstractHandler;
import vn.simulator.consumer.handler.IAuthenticationHandler;

public class AuthenticationHandler extends AbstractHandler implements IAuthenticationHandler {

    @Override
    public void loginHandler(RoutingContext event) {
        internalEventHandler.blockingStart(Action.LOGIN, event.getBody(), result -> {
            event
                    .response()
                    .putHeader("Content-Type", "application/json")
                    .setStatusCode(CodeResponse.HttpStatusCode.OK.getCode())
                    .end(Json.encodePrettily(result));
        });

    }
}
