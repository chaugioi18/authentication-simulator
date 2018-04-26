package vn.simulator.start;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import vn.simulator.start.config.Configuration;

public class SetupModule extends AbstractModule {

    private Context context;
    private ModuleConfig config;

    SetupModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public ModuleConfig getModuleConfig() {
        this.config = Configuration.loadConfig(this.context);
        return config;
    }

    @Provides
    @Singleton
    public Vertx getVertx() {
        return Vertx.vertx(new VertxOptions()
                .setWorkerPoolSize(config.getCommonConfig().getWorkerPoolSize())
                .setMaxWorkerExecuteTime(config.getCommonConfig().getWorkerMaxExecuteTime())
                .setBlockedThreadCheckInterval(config.getCommonConfig().getBlockThreadCheckInterval()));
    }

    @Override
    protected void configure() {
        Json.prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Json.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
