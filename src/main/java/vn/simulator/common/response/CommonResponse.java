package vn.simulator.common.response;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import vn.simulator.exception.CustomException;

/**
 * Design pattern for builder
 *
 * @author chau
 */

public class CommonResponse {

    private Integer code;
    private JsonObject object;
    private String message;

    private CommonResponse(CommRequestBuilder builder) {
        this.object = builder.object;
        this.code = builder.code;
    }

    public Integer getCode() {
        return code;
    }

    public JsonObject getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }

    /**
     * This builder build common request to send by mq in micro services;
     */
    public static class CommRequestBuilder {
        private Integer code;
        private JsonObject object;

        /**
         * @param code   Event Id of request
         * @param object POJO object will be convert to json
         */
        public CommRequestBuilder(Integer code, Object object) {
            try {
                this.code = code;
                this.object = new JsonObject(Json.encodePrettily(object));
            } catch (DecodeException | EncodeException e) {
                throw new CustomException.JsonException("This is not a json object");
            }
        }

        public CommRequestBuilder() {
            this.code = CodeResponse.AuthenticationCode.INVALID_INPUT.getCode();
            this.object = new JsonObject();
        }

        public CommRequestBuilder setCode(Integer code) {
            this.code = code;
            return this;
        }

        public CommRequestBuilder setObject(JsonObject object) {
            try {
                this.object = new JsonObject(Json.encodePrettily(object));
            } catch (DecodeException | EncodeException e) {
                throw new CustomException.JsonException("This is not a json object");
            }
            return this;
        }

        public CommonResponse build() {
            return new CommonResponse(this);
        }
    }
}
