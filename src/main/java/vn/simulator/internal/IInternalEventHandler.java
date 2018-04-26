package vn.simulator.internal;

import io.vertx.core.json.JsonObject;
import vn.simulator.common.request.Action;

public interface IInternalEventHandler {

    void blockingStart(Action action, JsonObject payload, InternalEventHandler.IResultHandler resultHandler);

    void nonBlockingStart();

}
