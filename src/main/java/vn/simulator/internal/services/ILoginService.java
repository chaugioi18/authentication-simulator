package vn.simulator.internal.services;

import vn.simulator.common.request.LoginRequest;
import vn.simulator.common.response.CommonResponse;

public interface ILoginService {

    CommonResponse login(LoginRequest request);

}
