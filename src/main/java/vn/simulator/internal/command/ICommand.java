package vn.simulator.internal.command;

import vn.simulator.common.response.CommonResponse;

public interface ICommand {

    CommonResponse execute(Object data);

}
