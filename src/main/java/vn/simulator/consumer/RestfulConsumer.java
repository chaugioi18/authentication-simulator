package vn.simulator.consumer;

import com.google.inject.Inject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.consumer.handler.IAuthenticationHandler;

public class RestfulConsumer implements IRestfulConsumer {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private IAuthenticationHandler handler;

    @Inject
    RestfulConsumer() {

    }

    @Override
    public void initConsumer(Router router) {
        handleAuthenticationApi(router)
                .handleOtpApi(router)
                .handleUser(router);

    }

    private RestfulConsumer handleAuthenticationApi(Router router) {
        router.post("").handler(BodyHandler.create()).blockingHandler(handler::loginHandler);
        return this;
    }

    private RestfulConsumer handleOtpApi(Router router) {
        return this;
    }

    private RestfulConsumer handleUser(Router router) {
        return this;
    }

}
