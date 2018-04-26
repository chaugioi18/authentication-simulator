package vn.simulator.start.config;

import io.vertx.core.Context;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.common.helper.FileHelper;
import vn.simulator.exception.CustomException;
import vn.simulator.start.ModuleConfig;

import java.util.List;

public class Configuration {

    private static final Logger LOGGER = LogManager.getLogger(Configuration.class);

    private static JsonObject rawConfig;


    /**
     * Read config file with list urls of config file
     * @param context vertx context in abstract vertical extended class
     * @return POJO config from JsonObject variable
     */
    public static ModuleConfig loadConfig(Context context) {
        try {
            LOGGER.info("loading properties");
            rawConfig = new JsonObject();
            List urls = context
                    .config()
                    .getJsonArray("url")
                    .getList();
            for (Object url: urls) {
                loadFile(String.valueOf(url));
            }
            return Json.decodeValue(rawConfig.encodePrettily(), ModuleConfig.class);
        } catch (Exception e) {
            LOGGER.error("Error on load config file cause by {}", e.getMessage());
            throw new CustomException.ConfigException("Error on load config file");
        }

    }

    /**
     * load single config file into static JsonObject variable
     * @param url of config file
     */
    private static void loadFile(String url) {
        String content = FileHelper.readFile(url);
        rawConfig.mergeIn(new JsonObject(content));
    }
}
