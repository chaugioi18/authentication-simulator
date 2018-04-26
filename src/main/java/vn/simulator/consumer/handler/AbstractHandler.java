package vn.simulator.consumer.handler;

import com.google.inject.Inject;
import vn.simulator.internal.IInternalEventHandler;

public abstract class AbstractHandler {

    protected IInternalEventHandler internalEventHandler;

    @Inject
    public AbstractHandler setInternalEventHandler(IInternalEventHandler internalEventHandler) {
        this.internalEventHandler = internalEventHandler;
        return this;
    }
}