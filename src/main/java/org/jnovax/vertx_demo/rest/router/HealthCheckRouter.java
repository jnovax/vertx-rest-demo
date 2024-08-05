package org.jnovax.vertx_demo.rest.router;

import io.vertx.core.Vertx;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.web.Router;
import io.vertx.redis.client.Command;
import io.vertx.redis.client.RedisConnection;
import io.vertx.redis.client.Request;

public class HealthCheckRouter {

    private HealthCheckRouter() {

    }

    /**
     * Set health check routes
     *
     * @param vertx    Vertx context
     * @param router   Router
     * @param redis    RedisConnection
     */
    public static void setRouter(Vertx vertx,
            Router router,
            RedisConnection redis) {
        final HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);

        healthCheckHandler.register("redis",
                promise -> redis.send(Request.cmd(Command.PING))
                        .onSuccess(res -> {
                            promise.complete(Status.OK());
                        })
                        .onFailure(err -> {
                            promise.fail(err.getCause());
                        }));

        router.get("/health").handler(healthCheckHandler);
    }

}