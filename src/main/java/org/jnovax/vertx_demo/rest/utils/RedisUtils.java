package org.jnovax.vertx_demo.rest.utils;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisConnection;

public class RedisUtils {

    private static final String HOST_CONFIG = "redis";
    private static final String PORT_CONFIG = "6379";
    private static final String PASSWORD_CONFIG = "mypassword";

    private RedisUtils() {

    }

    /**
     * Build DB client that is used to manage a pool of connections
     *
     * @param vertx Vertx context
     * @return PostgreSQL pool
     */
    public static RedisConnection buildClient(Vertx vertx) {
        final Future<RedisConnection> redisClient = Redis.createClient(
                vertx,
                String.format("redis://:%s@%s:%s/1", PASSWORD_CONFIG, HOST_CONFIG, PORT_CONFIG))
                .connect();

        return redisClient.result();
    }

}