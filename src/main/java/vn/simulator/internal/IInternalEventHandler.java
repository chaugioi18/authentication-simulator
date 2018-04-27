package vn.simulator.internal;

import io.vertx.core.buffer.Buffer;
import vn.simulator.common.request.Action;

public interface IInternalEventHandler {

    void blockingStart(Action action, Buffer payload, InternalEventHandler.IResultHandler resultHandler);

    void nonBlockingStart();

}
