package org.jnovax.vertx_demo.rest.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import org.jnovax.vertx_demo.rest.utils.LogUtils;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start() {
        final long start = System.currentTimeMillis();

        deployApiVerticle(vertx)
                .onSuccess(success -> LOGGER.info(LogUtils.RUN_APP_SUCCESSFULLY_MESSAGE.buildMessage(System.currentTimeMillis() - start)))
                .onFailure(throwable -> LOGGER.error(throwable.getMessage()));
    }

    private Future<String> deployApiVerticle(Vertx vertx) {
        return vertx.deployVerticle(ApiVerticle.class.getName());
    }

}