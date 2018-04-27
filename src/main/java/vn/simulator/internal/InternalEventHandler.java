package vn.simulator.internal;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import vn.simulator.common.helper.States;
import vn.simulator.common.request.Action;
import vn.simulator.common.response.CodeResponse;
import vn.simulator.common.response.CommonResponse;
import vn.simulator.internal.command.ICommand;

import java.util.Map;

public class InternalEventHandler implements IInternalEventHandler {

    private Map<Action, ICommand> actionMap;
    private Vertx vertx;

    @Inject
    InternalEventHandler(Vertx vertx,
                         Map<Action, ICommand> actionMap) {
        this.vertx = vertx;
        this.actionMap = actionMap;
    }

    /**
     * Open execute block use worker vertx
     * Handle command with action input and return common response
     * If not found command in map return error from server
     *
     * @param action
     * @param payload
     * @param resultHandler
     */
    @Override
    public void blockingStart(Action action, Buffer payload, IResultHandler resultHandler) {
        vertx.executeBlocking(handler -> {
            ICommand command = actionMap.get(action);
            CommonResponse response = States.isNull(command) ? new CommonResponse.CommRequestBuilder()
                    .setCode(CodeResponse.ServerErrorCode.SERVICE_UNAVAILABLE.getCode())
                    .build() : command.execute(payload);
            handler.complete(response);
        }, res -> {
            resultHandler.handle(res.result());
        });
    }

    @Override
    public void nonBlockingStart() {

    }

    @FunctionalInterface
    public interface IResultHandler {
        void handle(Object result);
    }
}
