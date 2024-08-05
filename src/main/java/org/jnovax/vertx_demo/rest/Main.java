package org.jnovax.vertx_demo.rest;

import org.jnovax.vertx_demo.rest.verticle.MainVerticle;

import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;

public class Main {
        public static void main(String[] args) {
                System.setProperty("vertx.logger-delegate-factory-class-name",
                                "io.vertx.core.logging.SLF4JLogDelegateFactory");

                PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
                new UptimeMetrics().bindTo(registry);

                final VertxPrometheusOptions prometheusOptions = new VertxPrometheusOptions().setEnabled(true)
                                .setStartEmbeddedServer(true)
                                .setEmbeddedServerOptions(new HttpServerOptions().setPort(10001));

                final Vertx vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(
                                new MicrometerMetricsOptions()
                                                .setPrometheusOptions(prometheusOptions)
                                                .setJvmMetricsEnabled(true)
                                                .setMicrometerRegistry(registry)
                                                .setEnabled(true)));

                vertx.deployVerticle(MainVerticle.class.getName())
                                .onFailure(throwable -> System.exit(-1));
        }

}
