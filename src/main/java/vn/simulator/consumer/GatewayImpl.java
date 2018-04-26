package vn.simulator.consumer;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.start.ModuleConfig;

public class GatewayImpl implements IGateway {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private Vertx vertx;
    private ModuleConfig config;
    private IRestfulConsumer restfulConsumer;

    @Inject
    GatewayImpl(Vertx vertx,
                ModuleConfig config,
                IRestfulConsumer restfulConsumer) {
        this.vertx = vertx;
        this.config = config;
        this.restfulConsumer = restfulConsumer;
    }

    @Override
    public IGateway useRestful() {
        LOGGER.info("Begin setup restful router");
        HttpServer server = vertx.createHttpServer(new HttpServerOptions()
                .setHost(config.getRestfulConfig().getHost())
                .setPort(config.getRestfulConfig().getPort()));
        Router router = Router.router(vertx);

        //handle consumer
        restfulConsumer.initConsumer(router);

        server.requestHandler(router::accept).listen();

        if (server != null) {
            LOGGER.info("Start restful gateway router");
            server.listen(event -> {
                if (event.succeeded()) {
                    LOGGER.info("Http server listen on port {}", config.getRestfulConfig().getPort());
                } else {
                    LOGGER.info("Listen failed on port {} cause by {}", config.getRestfulConfig().getPort(), event.cause().getMessage());
                }
            });
        }

        return this;
    }

    @Override
    public IGateway useMq() {
        return this;
    }

    @Override
    public void start() {

        LOGGER.info("Start service successful...");
    }
}
