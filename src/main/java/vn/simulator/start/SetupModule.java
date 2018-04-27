package vn.simulator.start;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.Json;
import vn.simulator.common.helper.IValidationTool;
import vn.simulator.common.helper.ValidationTool;
import vn.simulator.common.request.Action;
import vn.simulator.consumer.GatewayImpl;
import vn.simulator.consumer.IGateway;
import vn.simulator.consumer.IRestfulConsumer;
import vn.simulator.consumer.RestfulConsumer;
import vn.simulator.consumer.handler.IAuthenticationHandler;
import vn.simulator.consumer.handler.impl.AuthenticationHandler;
import vn.simulator.database.DataSourceFactory;
import vn.simulator.database.SqlConnector;
import vn.simulator.internal.IInternalEventHandler;
import vn.simulator.internal.InternalEventHandler;
import vn.simulator.internal.command.ICommand;
import vn.simulator.internal.services.ILoginService;
import vn.simulator.internal.services.impl.LoginService;
import vn.simulator.start.config.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SetupModule extends AbstractModule {

    private Context context;
    private ModuleConfig config;
    private DataSourceFactory dataSourceFactory;

    SetupModule(Context context) {
        this.context = context;
        dataSourceFactory = new DataSourceFactory();
    }

    @Provides
    @Singleton
    public ModuleConfig getModuleConfig() {
        this.config = Configuration.loadConfig(this.context);
        return config;
    }

    @Provides
    @Singleton
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @Provides
    @Singleton
    public Map<Action, ICommand> getActionMap() {
        return Arrays.stream(Action.values())
                .collect(Collectors.toMap(e -> e, Action::getCommand));
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

        bind(IInternalEventHandler.class).to(InternalEventHandler.class).in(Scopes.SINGLETON);
        bind(ILoginService.class).to(LoginService.class).in(Scopes.SINGLETON);
        bind(IRestfulConsumer.class).to(RestfulConsumer.class).in(Scopes.SINGLETON);
        bind(IAuthenticationHandler.class).to(AuthenticationHandler.class).in(Scopes.SINGLETON);
        bind(IGateway.class).to(GatewayImpl.class).in(Scopes.SINGLETON);
        bind(IValidationTool.class).to(ValidationTool.class).in(Scopes.SINGLETON);


        install(new SqlConnector(getModuleConfig().getFirstDatabase(), "write", dataSourceFactory));
        install(new SqlConnector(getModuleConfig().getSecondDatabase(), "read", dataSourceFactory));
    }

}
