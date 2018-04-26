package vn.simulator.start;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.AbstractVerticle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.consumer.GatewayImpl;
import vn.simulator.consumer.IGateway;

public class Start extends AbstractVerticle {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Override
    public void start() throws Exception {

        //Run setup
        LOGGER.info("Begin setup service.......");
        Injector injector = Guice.createInjector(new SetupModule(context));
        IGateway gateway = injector.getInstance(GatewayImpl.class);
        gateway
                .useRestful()
                .start();

    }
}
