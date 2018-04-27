package vn.simulator.internal.services.impl;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.common.helper.IValidationTool;
import vn.simulator.common.helper.JsonConverter;
import vn.simulator.common.request.LoginRequest;
import vn.simulator.common.response.CodeResponse;
import vn.simulator.common.response.CommonResponse;
import vn.simulator.common.response.LoginResponse;
import vn.simulator.exception.CustomException;
import vn.simulator.internal.services.ILoginService;

public class LoginService implements ILoginService {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private IValidationTool validationTool;

    @Inject
    LoginService(IValidationTool validationTool) {
        this.validationTool = validationTool;
    }

    @Override
    public CommonResponse login(LoginRequest request) {
        try {
            validationTool.checkCanNullWithAnnotation(request).checkHMAC(request, request.getHmac()).isValidPassword(request.getPassword());
            LoginResponse response = new LoginResponse();
            return new CommonResponse
                    .CommRequestBuilder()
                    .setCode(CodeResponse.AuthenticationCode.SUCCESS.getCode())
                    .setObject(JsonConverter.toJson(response))
                    .build();
        } catch (CustomException.ValidationError e) {
            LOGGER.error(e.getMessage());
            return new CommonResponse
                    .CommRequestBuilder()
                    .setCode(CodeResponse.AuthenticationCode.INVALID_INPUT.getCode())
                    .build();
        }
    }

}
