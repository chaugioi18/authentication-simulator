package vn.simulator.consumer.handler;

import io.vertx.ext.web.RoutingContext;

public interface IAuthenticationHandler {

    void loginHandler(RoutingContext event);

}
