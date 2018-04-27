package vn.simulator.internal.command;

import io.vertx.core.buffer.Buffer;
import vn.simulator.common.response.CommonResponse;

public interface ICommand {

    CommonResponse execute(Buffer payload);

}
