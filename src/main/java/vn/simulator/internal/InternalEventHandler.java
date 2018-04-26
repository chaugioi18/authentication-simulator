package vn.simulator.internal;

import io.vertx.core.json.JsonObject;
import vn.simulator.common.request.Action;

public class InternalEventHandler implements IInternalEventHandler {

    @Override
    public void blockingStart(Action action, JsonObject payload, IResultHandler resultHandler) {
        
    }

    @Override
    public void nonBlockingStart() {

    }

    @FunctionalInterface
    public interface IResultHandler {
        void handle(Object result);
    }
}
