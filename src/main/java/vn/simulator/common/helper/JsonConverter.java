package vn.simulator.common.helper;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class JsonConverter {

    public static JsonObject toJson(Object object) {
        return new JsonObject(Json.encodePrettily(object));
    }

}
